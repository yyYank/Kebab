package kebab

import java.net.URI
import java.net.URL
import java.net.URLEncoder
import java.util.*

/**
 * The browser is the centre of Kebab. It encapsulates a {@link org.openqa.selenium.WebDriver} implementation and references
 * a {@link kebab.Page} object that provides access to the content.
 * <p>
 * Browser objects dynamically delegate all method calls and property read/writes that it doesn't implement to the current
 * page instance via {@code propertyMissing ( )} and {@code methodMissing ( )}.
 */
class Browser(val config: Configuration) {
    // UTF-8の定数
    val UTF8 = "UTF-8"
    // ページオブジェクト
    val page = Page()
    // ページの変化通知リスナ
    val pageChangeListeners = LinkedHashSet<String>()
    // レポートを書き込むディレクトリパス
    var reportGroup: String? = null
    // ナビゲータのファクトリ。ナビゲータはページのナビゲートをするんだろな
    val navigatorFactory: NavigatorFactory

    init {
        navigatorFactory = config.createNavigatorFactory(this)
    }

    constructor() : this(ConfigurationLoader().conf) {
    }

    /**
     * Creates a new browser object via the default constructor and executes the closure
     * with the browser instance as the closure's delegate.
     *
     * @return the created browser
     */
    fun drive(url: String, script: Page.() -> Unit) = drive(this, url, script)


    /**
     *  Executes the closure with browser as its delegate.
     *
     * @return browser
     */
    fun drive(browser: Browser, url: String, script: Page.() -> Unit): Browser {
        // ページオブジェクトの初期化
        browser.page.init(browser)
        browser.page.url = url
        // 画面遷移
        browser.go(url)
        browser.page.script()
        return browser
    }


    /**
     * Sends the browser to the configured {@link #getBaseUrl() base url}.
     */
    fun go() {
        go(emptyMap<String, Any>(), "")
    }

    /**
     * Sends the browser to the configured {@link #getBaseUrl() base url}, appending {@code params} as
     * query parameters.
     */
    fun go(params: Map<String, Any>) {
        go(params, "")
    }

    /**
     * Sends the browser to the given url. If it is relative it is resolved against the {@link #getBaseUrl() base url}.
     */
    fun go(url: String) {
        go(emptyMap<String, Any>(), url)
    }

    /**
     * Sends the browser to the given url. If it is relative it is resolved against the {@link #getBaseUrl() base url}.
     */
    fun go(params: Map<String, Any>, url: String) {
        val newUrl = calculateUri(url, params)
        val currentUrl = config.driver.currentUrl
        if (currentUrl == newUrl) {
            config.driver.navigate().refresh()
        } else {
            config.driver.get(newUrl)
        }
    }

    fun calculateUri(path: String, params: Map<String, Any>): String {
        var uri = URI(path)
        if (uri.isAbsolute) {
            uri = URI(config.baseUrl).resolve(uri)
        }
        val queryString = toQueryString(params)
        val joiner = if (uri.query != null) {
            '&'
        } else {
            '?'
        }
        return URL(uri.toString() + joiner + queryString).toString()
    }

    fun toQueryString(params: Map<String, Any>): String {
        // TODO 元の実装がcollectMany{name,values -> values.collect{v->....なので「key:value = 1:N」にしないとダメっぽい
        return params.asSequence().map { m -> URLEncoder.encode(m.key, UTF8) + "=" + URLEncoder.encode(m.value.toString(), UTF8) }.joinToString { "&" }
    }

    /**
     * Quits the driver.
     *
     * @see org.openqa.selenium.WebDriver#quit()
     */
    fun quit() {
        config.driver.quit()
    }
}





