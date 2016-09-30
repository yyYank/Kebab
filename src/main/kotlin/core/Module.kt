package kebab

import kebab.elment.*
import org.openqa.selenium.support.ui.Wait
import kotlin.properties.Delegates

/**
 * Created by yy_yank on 2015/12/23.
 */
class Module {

    var navigator : Navigator by Delegates.notNull()

    fun init (browser : Browser, navigatorFactory : NavigatorFactory) {
        navigator = navigatorFactory.getBase()!!
        // val contentTemplates : MutableMap<String, PageContentTemplate> = PageContentTemplateBuilder.build(browser, this, navigatorFactory, "content", this.class, Module.java)
        // val pageContentSupport = DefaultPageContentSupport(this, contentTemplates, navigatorFactory, navigator)
        val downloadSupport = DefaultDownloadSupport(browser)
        val waitingSupport = DefaultWaitingSupport(browser.config)
        val frameSupport = DefaultFrameSupport(browser)
        // val js = browser.getJs()
        // val alertAndConfirmSupport = DefaultAlertAndConfirmSupport({ js }, browser.config)
        val interactionsSupport = DefaultInteractionsSupport(browser)
        initialized()
    }

    @SuppressWarnings("EmptyMethod")
    fun initialized() {
    }

    @JvmOverloads
    fun click(potentialPages : List<Page>, wait : Wait<Any>) = navigator.click(potentialPages, wait)

    @JvmOverloads
    fun click(pageInstance : Page, wait : Wait<Any>)  = navigator.click(pageInstance, wait)

    @JvmOverloads
    fun click(pageClass : Class<out Page>, wait : Wait<Any>) = navigator.click(pageClass, wait)

    @JvmOverloads
    fun click() : Navigator = navigator.click()

}
