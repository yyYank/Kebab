package kebab

import org.openqa.selenium.WebDriver
import java.util.*
import kotlin.properties.Delegates

/**
 * The browser is the centre of Kebab. It encapsulates a {@link org.openqa.selenium.WebDriver} implementation and references
 * a {@link kebab.Page} object that provides access to the content.
 * <p>
 * Browser objects dynamically delegate all method calls and property read/writes that it doesn't implement to the current
 * page instance via {@code propertyMissing ( )} and {@code methodMissing ( )}.
 */
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

        val contentTemplates = PageContentTemplateBuilder.build(
                browser,
                this as PageContentContainer,
                browser.navigatorFactory,
                "content",
                this.javaClass
        )
        pageContentSupport = DefaultPageContentSupport(this, contentTemplates, browser.navigatorFactory)
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
        fun build(browser : Browser, container : PageContentContainer?, navigatorFactory : NavigatorFactory?, property : String, startAt : Class<*>, stopAt : Class<*>  =  Any::class.javaClass) {

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

            build(browser, container, navigatorFactory, templatesDefinitions.reversed().joinToString {it}, clazz)
        }
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

interface PageContentContainer {
}

class ConfigurationLoader {
    val conf = ""
}

class Configuration {
    val driver : WebDriver by Delegates.notNull<WebDriver>()
}


