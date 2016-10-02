package kebab.core

import kebab.elment.*
import kebab.support.alert.AlertAndConfirmSupport
import kebab.support.alert.DefaultAlertAndConfirmSupport
import kebab.support.alert.UninitializedAlertAndConfirmSupport
import kebab.support.download.DefaultDownloadSupport
import kebab.support.download.DownloadSupport
import kebab.support.download.UninitializedDownloadSupport
import kebab.support.frame.DefaultFrameSupport
import kebab.support.frame.FrameSupport
import kebab.support.frame.UninitializedFrameSupport
import kebab.support.interaction.DefaultInteractionsSupport
import kebab.support.interaction.InteractionsSupport
import kebab.support.interaction.UninitializedInteractionSupport
import kebab.support.navigate.NavigableSupport
import kebab.support.navigate.Navigatable
import kebab.support.navigate.UninitializedNavigableSupport
import kebab.support.page.DefaultPageContentSupport
import kebab.support.page.PageContentSupport
import kebab.support.page.UninitializedPageContentSupport
import kebab.support.waiting.DefaultWaitingSupport
import kebab.support.waiting.UninitializedWaitingSupport
import kebab.support.waiting.WaitingSupport
import org.openqa.selenium.By
import support.text.TextMatchingSupport

/**
 * Created by yy_yank on 2015/12/19.
 */
class Page : Navigatable, PageContentContainer, Initializable, WaitingSupport {
    var at = null
    var url = ""
    var atCheckWaiting = null
    private var browser: Browser? = null
    var title: String? = null
        get () {
            return this.browser?.config?.driver?.title
        }

    // @Delegate
    var pageContentSupport: PageContentSupport? = UninitializedPageContentSupport(this)
    // @Delegate
    var downloadSupport: DownloadSupport? = UninitializedDownloadSupport(this)

    var waitingSupport: WaitingSupport = UninitializedWaitingSupport(this)

    var textMatchingSupport: TextMatchingSupport = TextMatchingSupport()

    var alertAndConfirmSupport: AlertAndConfirmSupport = UninitializedAlertAndConfirmSupport(this)

    var navigableSupport: Navigable = UninitializedNavigableSupport(this)

    // @Delegate
    var frameSupport: FrameSupport = UninitializedFrameSupport(this)
    //    @Delegate
    var interactionsSupport: InteractionsSupport = UninitializedInteractionSupport(this)




    /**
     * Initialises this page instance, connecting it to the browser.
     * <p>
     * <b>This method is called internally, and should not be called by users of Kebab.</b>
     */
    fun init(browser: Browser): Page {
        this.browser = browser
        title = browser.config.driver.title
        val contentTemplates = PageContentTemplateBuilder.build(browser, DefaultPageContentContainer(), browser.navigatorFactory, "content", this.javaClass)
        pageContentSupport = DefaultPageContentSupport(this, contentTemplates, browser.navigatorFactory)
        navigableSupport = NavigableSupport(browser.navigatorFactory!!)
        downloadSupport = DefaultDownloadSupport(browser)
        waitingSupport = DefaultWaitingSupport(browser.config)
        frameSupport = DefaultFrameSupport(browser)
        interactionsSupport = DefaultInteractionsSupport(browser)
        alertAndConfirmSupport = DefaultAlertAndConfirmSupport({ this.getJs() }, browser.config)
        return this
    }

    fun find() = navigableSupport.find()
    fun find(index : Int) = navigableSupport.find(index)
    fun find(range : ClosedRange<Int>) = navigableSupport.find(range)
    fun find(selector: String) = navigableSupport.find(selector)
    fun find(selector: String, index: Int) = navigableSupport.find(selector, index)
    fun find(selector: String, range: ClosedRange<Int>) = navigableSupport.find(selector, range)
    fun find(attributes: MutableMap<String, Any>) = navigableSupport.find(attributes)
    fun find(attributes: MutableMap<String, Any>, index: Int) = navigableSupport.find(attributes, index)
    fun find(attributes: MutableMap<String, Any>, range: ClosedRange<Int>) = navigableSupport.find(attributes, range)
    fun find(attributes: MutableMap<String, Any>, selector: String) = navigableSupport.find(attributes, selector)
    fun find(attributes: MutableMap<String, Any>, selector: String, index: Int) = navigableSupport.find(attributes, selector, index)
    fun find(attributes: MutableMap<String, Any>, selector: String, range: ClosedRange<Int>) = navigableSupport.find(attributes, selector, range)
    fun find(attributes: MutableMap<String, Any>, bySelector: By) = navigableSupport.find(attributes, bySelector)
    fun find(attributes: MutableMap<String, Any>, bySelector: By, index: Int) = navigableSupport.find(attributes, bySelector, index)
    fun find(attributes: MutableMap<String, Any>, bySelector: By, range: ClosedRange<Int>) = navigableSupport.find(attributes, bySelector, range)
    fun find(bySelector: By) = navigableSupport.find(bySelector)
    fun find(bySelector: By, index: Int) = navigableSupport.find(bySelector, index)
    fun find(bySelector: By, range: ClosedRange<Int>) = navigableSupport.find(bySelector, range)
    override fun <T> waitFor(waitPreset: String, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, waitPreset: String, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(timeout: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, timeout: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(timeout: Double, interval: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, timeout: Double, interval: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }
    fun getJs() = Any()
}

interface Initializable{}