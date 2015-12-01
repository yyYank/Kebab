package kebab

import org.openqa.selenium.WebDriver
import java.util.*
import kotlin.properties.Delegates

class Browser {
    val UTF8 = "UTF-8"
    val config : Configuration by Delegates.notNull<Configuration>()
    val page : Page by Delegates.notNull<Page>()
    val pageChangeListeners = LinkedHashSet<String>();
    var reportGroup : String? = null
    var navigatorFactory : NavigatorFactory? = null
    val augmentedDriver : WebDriver = RemoteDriverOperation(this.javaClass.classLoader).getAugmentedDriver(getDriver())

    /**
     * The driver implementation used to automate the actual browser.
     * <p>
     * The driver implementation to use is determined by the configuration.
     *
     * @see geb.Configuration#getDriver()
     */
     fun getDriver() : WebDriver  = config.driver


}

class Page : Navigatable, PageContainer, Initializable, WatingSupport{
    companion object {
        var at = null
        var url : String = ""
        var atCheckWaiting = null
    }
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
//        val contentTemplates = PageContentTemplateBuilder.build(browser, this, browser.navigatorFactory, "content", (this.class, Page)
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

class NavigableSupport(navigatorFactory: NavigatorFactory?) : Navigable {

}

class DefaultWaitingSupport(config: Configuration) : WaitingSupport {

}

class DefaultPageContentSupport(page: Page, contentTemplates: Any, navigatorFactory: NavigatorFactory?) : PageContentSupport {

}

class PageContentTemplateBuilder {

    companion object {
        fun build(browser : Browser, page : Page, navigatorFactory: NavigatorFactory?, type : String, klass : Class<*>) = Any()
    }
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

class ConfigurationLoader {
    val conf = ""
}

class Configuration {
    val driver : WebDriver by Delegates.notNull<WebDriver>()
}


