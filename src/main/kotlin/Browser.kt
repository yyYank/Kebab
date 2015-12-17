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
    val page : Page by Delegates.notNull<Page>()
    // ページの変化通知リスナ
    val pageChangeListeners = LinkedHashSet<String>();
    // レポートを書き込むディレクトリパス
    var reportGroup : String? = null
    // ナビゲータのファクトリ。ナビゲータはページのナビゲートをするんだろな
    var navigatorFactory : NavigatorFactory by Delegates.notNull<NavigatorFactory>()

    /**
     * If the driver is remote, this object allows access to its capabilities (users of Kebab should not access this object, it is used internally).
     * リモートドライバの場合はコンフィグからオペレーションを介してドライバの設定をするっぽい
     */
    // @Lazy
    val augmentedDriver : WebDriver = RemoteDriverOperation(this.javaClass.classLoader).getAugmentedDriver(getDriver())


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
     * The driver implementation used to automate the actual browser.
     * <p>
     * The driver implementation to use is determined by the configuration.
     *
     * @see kebab.Configuration#getDriver()
     */
     fun getDriver() : WebDriver  = config.driver

    /**
     * Creates a new browser object via the default constructor and executes the closure
     * with the browser instance as the closure's delegate.
     *
     * @return the created browser
     */
    fun drive(url : String, script : () -> Any) = drive(Browser(), url, script)


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
        val currentUrl = getDriver().currentUrl
        if (currentUrl == newUrl) {
            getDriver().navigate().refresh()
        } else {
            getDriver().get(newUrl)
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


}

class Page : Navigatable, PageContainer, Initializable, WatingSupport{
    var at = null
    var url = ""
    var atCheckWaiting = null
    private var browser : Browser? = null

    // @Delegate
    var pageContentSupport : PageContentSupport? = UninitializedPageContentSupport(this)
    // @Delegate
    var downloadSupport : DownloadSupport? = UninitializedDownloadSupport(this)

    var waitingSupport : WaitingSupport = UninitializedWaitingSupport(this)

    var textMatchingSupport : TextMatchingSupport = TextMatchingSupport()

    var alertAndConfirmSupport : AlertAndConfirmSupport = UninitializedAlertAndConfirmSupport(this)

    var navigableSupport : Navigable =  UninitializedNavigableSupport(this)

    // @Delegate
    var  frameSupport : FrameSupport = UninitializedFrameSupport(this)
//    @Delegate
    var interactionsSupport : InteractionsSupport = UninitializedInteractionSupport(this)

    /**
     * Initialises this page instance, connecting it to the browser.
     * <p>
     * <b>This method is called internally, and should not be called by users of Kebab.</b>
     */
    fun init (browser : Browser ) : Page {
        this.browser = browser

//        val contentTemplates = PageContentTemplateBuilder.build(
//                browser,
//                this as PageContentContainer,
//                browser.navigatorFactory,
//                "content",
//                this.javaClass
//        )
//        pageContentSupport = DefaultPageContentSupport(this, contentTemplates, browser.navigatorFactory)
        navigableSupport = NavigableSupport(browser.navigatorFactory)
        downloadSupport = DefaultDownloadSupport(browser)
        waitingSupport = DefaultWaitingSupport(browser.config)
        frameSupport = DefaultFrameSupport(browser)
        interactionsSupport = DefaultInteractionsSupport(browser)
        alertAndConfirmSupport = DefaultAlertAndConfirmSupport({ this.getJs() }, browser.config)
        return this
    }

    fun getJs() = Any()
}

class DefaultAlertAndConfirmSupport(function: () -> Any, config: Configuration) : AlertAndConfirmSupport {

}

class DefaultInteractionsSupport(browser: Browser) : InteractionsSupport {

}

class UninitializedInteractionSupport(page: Page) : InteractionsSupport {

}

interface InteractionsSupport {

}

class DefaultFrameSupport(browser: Browser) : FrameSupport {

}

class DefaultDownloadSupport(browser: Browser) : DownloadSupport {

}

class UninitializedFrameSupport(page: Page) : FrameSupport {

}

interface FrameSupport {

}

class NavigableSupport(navigatorFactory: NavigatorFactory) : Navigable {

}

class DefaultWaitingSupport(config: Configuration) : WaitingSupport {

}

class DefaultPageContentSupport(page: Page, contentTemplates: Any, navigatorFactory: NavigatorFactory) : PageContentSupport {

}

class PageContentTemplateBuilder(val browser : Browser, val container : PageContentContainer, val navigatorFactory : NavigatorFactory) {
    val templates = HashMap<String, PageContentTemplate>()
    companion object {

        fun build(browser : Browser,
                  container : PageContentContainer,
                  navigatorFactory : NavigatorFactory,
                  property : String, startAt : Class<*>,
                  stopAt : Class<*>  =  Any::class.java) : HashMap<String, PageContentTemplate> {

            if (!stopAt.isAssignableFrom(startAt)) {
                throw IllegalArgumentException("$startAt is not a subclass of $stopAt")
            }

            val templatesDefinitions = listOf("1")
            var clazz = startAt

            while (clazz != stopAt) {
                //                        var templatesDefinition =
                //noinspection GroovyUnusedCatchParameter
                //                                try {
                //                                    clazz[property]
                //                                } catch (MissingPropertyException e) {
                //                                    // swallow
                //                                }
                //
                //                        if (templatesDefinition) {
                //                            if (!(templatesDefinition is Closure)) {
                //                                throw IllegalArgumentException("'$property' static property of class $clazz should be a Closure")
                //                            }
                //                            templatesDefinitions << templatesDefinition.clone()
                //                        }
                //
                clazz = clazz.superclass
            }
            return build(browser, container, navigatorFactory, templatesDefinitions)
        }

        fun build(browser : Browser,
                   container : PageContentContainer,
                   navigatorFactory : NavigatorFactory,
                   templatesDefinitions : List<Any>)  : HashMap<String, PageContentTemplate> {
            val builder = PageContentTemplateBuilder(browser, container, navigatorFactory)
            //        for (templatesDefinition in templatesDefinitions) {
            //            templatesDefinition.delegate = builder
            //            templatesDefinition()
            //        }
            return builder.templates
        }
    }
}

class PageContentTemplate {
    // TODO 定義
//    val browser : Browser
//    val owner : PageContentContainer
//    val name : String
//    val params : String
//    val factory : Closure
//    val navigatorFactory : NavigatorFactory
}

interface Navigable {

}

class UninitializedNavigableSupport(page: Page) : Navigable {

}

interface AlertAndConfirmSupport {

}

class UninitializedAlertAndConfirmSupport(page : Page) : AlertAndConfirmSupport {

}

class TextMatchingSupport {

}

class UninitializedWaitingSupport(page : Page) : WaitingSupport
class UninitializedDownloadSupport(page : Page) : DownloadSupport
class UninitializedPageContentSupport(page : Page) : PageContentSupport

interface WaitingSupport {

}

interface DownloadSupport {

}

interface PageContentSupport{

}

interface WatingSupport {

}

interface PageContainer {

}

interface Initializable {

}

interface Navigatable {

}

interface PageContentContainer {
}

class ConfigurationLoader {
    val conf : Configuration by Delegates.notNull<Configuration>()
}

class Configuration {
    var baseUrl = ""
    val driver : WebDriver by Delegates.notNull<WebDriver>()
}


