package kebab

import org.openqa.selenium.WebDriver
import java.net.URI
import java.net.URL
import java.util.*
import kotlin.collections.emptyMap
import kotlin.collections.listOf
import kotlin.properties.Delegates

/**
 * The browser is the centre of Kebab. It encapsulates a {@link org.openqa.selenium.WebDriver} implementation and references
 * a {@link kebab.Page} object that provides access to the content.
 * <p>
 * Browser objects dynamically delegate all method calls and property read/writes that it doesn't implement to the current
 * page instance via {@code propertyMissing ( )} and {@code methodMissing ( )}.
 */
class Browser {
    // UTF-8の定数
    val UTF8 = "UTF-8"
    // コンフィグ
    var config : Configuration by Delegates.notNull<Configuration>()
    // ページオブジェクト
    val page = Page()
    // ページの変化通知リスナ
    val pageChangeListeners = LinkedHashSet<String>();
    // レポートを書き込むディレクトリパス
    var reportGroup : String? = null
    // ナビゲータのファクトリ。ナビゲータはページのナビゲートをするんだろな
    var navigatorFactory by Delegates.notNull<NavigatorFactory>()

    /**
     * If the driver is remote, this object allows access to its capabilities (users of Kebab should not access this object, it is used internally).
     * リモートドライバの場合はコンフィグからオペレーションを介してドライバの設定をするっぽい
     */
    // @Lazy
    // val augmentedDriver : WebDriver = RemoteDriverOperation(this.javaClass.classLoader).getAugmentedDriver(config.driver)


    /**
     * Create a new browser with a default configuration loader, loading the default configuration file.
     *
     * @see kebab.ConfigurationLoader
     */
    constructor() {
        this(ConfigurationLoader().conf)
    }

    private operator fun invoke(conf: Configuration) {
        this.config = conf
    }

    /**
     * Create a new browser backed by the given configuration.
     *
     * @see kebab.Configuration
     */
    constructor(config : Configuration) {
        this.config = config
    }


    /**
     * Creates a new browser object via the default constructor and executes the closure
     * with the browser instance as the closure's delegate.
     *
     * @return the created browser
     */
    fun drive(url : String, script : () -> Any) = drive(this, url, script)


        /**
         * Creates a new browser object via the default constructor and executes the closure
         * with the browser instance as the closure's delegate.
         *
         * @return the created browser
         */
//        fun drive(script : () -> Any) = drive(Browser(), script)


        /**
         * Creates a new browser with the configuration and executes the closure
         * with the browser instance as the closure's delegate.
         *
         * @return the created browser
         */
//        fun drive(conf : Configuration, script : () -> Any)  = drive(Browser(conf), script)


        /**
         * Executes the closure with browser as its delegate.
         *
         * @return browser
         */
        fun drive(browser : Browser, url : String, script : () -> Any) : Browser {
            // ページオブジェクトの初期化
            browser.page.init(browser)
            browser.page.url = url
            // 画面遷移
            browser.go(url)
            // TODO scriptのdelegateをbrowserに。
            //  script.delegate = browser
            script()
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
    fun go(params : kotlin.Map<String, Any>) {
        go(params, "")
    }

    /**
     * Sends the browser to the given url. If it is relative it is resolved against the {@link #getBaseUrl() base url}.
     */
    fun go(url : String) {
        go(emptyMap<String, Any>(), url)
    }

    /**
     * Sends the browser to the given url. If it is relative it is resolved against the {@link #getBaseUrl() base url}.
     */
    fun go(params : kotlin.Map<String, Any>, url : String) {
        val newUrl = calculateUri(url, params)
        val currentUrl = config.driver.currentUrl
        if (currentUrl == newUrl) {
            config.driver.navigate().refresh()
        } else {
            config.driver.get(newUrl)
        }
    }

    fun calculateUri(path : String, params : kotlin.Map<String, Any>) : String {
        var uri = URI(path)
        if (uri.isAbsolute) {
            uri = URI(config.baseUrl).resolve(uri)
        }
        val queryString = toQueryString(params)
        val joiner = if(uri.query != null) { '&' }else{ '?' }
        return URL(uri.toString() + joiner + queryString).toString()
    }

    fun toQueryString(params : kotlin.Map<String, Any>) : String{
        // TODO requestパラメータの文字列生成
        // TODO UTF-8エンコードとか
        return ""
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


interface Navigable {

}


interface PageContentContainer {
}

class ConfigurationLoader {
    val conf : Configuration by Delegates.notNull<Configuration>()
}

class Configuration (val baseUrl : String , val driver : WebDriver)


