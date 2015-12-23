package kebab

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Wait
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
    var navigatorFactory : NavigatorFactory  by Delegates.notNull<NavigatorFactory>()

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
        this.navigatorFactory = config.createNavigatorFactory(this)
    }


    /**
     * Creates a new browser object via the default constructor and executes the closure
     * with the browser instance as the closure's delegate.
     *
     * @return the created browser
     */
    fun drive(url : String, script : Page.() -> Unit) = drive(this, url, script)


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
        fun drive(browser : Browser, url : String, script : Page.() -> Unit) : Browser {
            // ページオブジェクトの初期化
            browser.page.init(browser)
            browser.page.url = url
            // 画面遷移
            browser.go(url)
            // TODO scriptのdelegateをbrowserに。
            //  script.delegate = browser
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
    fun find() : Navigator
    fun find(index : Int) : Navigator
    fun find(range : ClosedRange<Int>) : Navigator
    fun find(selector : String): Navigator
    fun find(selector : String, index : Int): Navigator
    fun find(selector : String, range: ClosedRange<Int>): Navigator
    fun find(attributes : MutableMap<String, Any>): Navigator
    fun find(attributes : MutableMap<String, Any>, index: Int): Navigator
    fun find(attributes : MutableMap<String, Any>, range: ClosedRange<Int>): Navigator
    fun find(attributes : MutableMap<String, Any>, selector: String): Navigator
    fun find(attributes : MutableMap<String, Any>, selector: String, index: Int): Navigator
    fun find(attributes : MutableMap<String, Any>, selector: String, range: ClosedRange<Int>): Navigator
    fun find(attributes : MutableMap<String, Any>, bySelector : By): Navigator
    fun find(attributes : MutableMap<String, Any>, bySelector : By, index: Int): Navigator
    fun find(attributes : MutableMap<String, Any>, bySelector : By, range: ClosedRange<Int>): Navigator
    fun find(bySelector : By): Navigator
    fun find(bySelector : By, index: Int): Navigator
    fun find(bySelector : By, range: ClosedRange<Int>): Navigator
}


interface PageContentContainer {
}

class ConfigurationLoader {
    val conf : Configuration by Delegates.notNull<Configuration>()
}

class Configuration (val baseUrl : String , val driver : WebDriver) {
    val baseNavigatorWaiting: Wait<Any>? = null
    val rawConfig = HashMap<String, NavigatorFactory>()
    /**
     * Creates the navigator factory to be used.
     *
     * Returns {@link BrowserBackedNavigatorFactory} by default.
     * <p>
     * Override by setting the 'navigatorFactory' to a closure that takes a single {@link Browser} argument
     * and returns an instance of {@link NavigatorFactory}
     *
     * @param browser The browser to use as the basis of the navigatory factory.
     * @return
     */
    fun createNavigatorFactory(browser: Browser): NavigatorFactory {
        val navigatorFactory = readValue("navigatorFactory", browser, null)
        if (navigatorFactory == null) {
            return BrowserBackedNavigatorFactory(browser, InnerNavigatorFactory())
        } else {
            val result = navigatorFactory.getBase()
            if (result is NavigatorFactory) {
                return result
            }

            throw InvalidGebConfiguration("navigatorFactory is '${navigatorFactory}', it should be a Closure that returns a NavigatorFactory implementation")
        }
        return BrowserBackedNavigatorFactory(browser, InnerNavigatorFactory())
    }

    private fun readValue(key: String, browser : Browser, defaultValue : NavigatorFactory?) =
            if (rawConfig.containsKey(key)) {
                rawConfig.get(key)
            } else {
                defaultValue
            }
}



