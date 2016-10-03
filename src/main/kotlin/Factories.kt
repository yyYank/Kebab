package kebab

import kebab.core.Browser
import kebab.core.PageContentTemplate
import kebab.element.*
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.util.*

/**
 * Created by yy_yank on 2015/12/19.
 */


interface NavigatorFactory {
    val innerNavigatorFactory: InnerNavigatorFactory

    fun getBase(): Navigator?
    fun getLocator(): Locator
    fun createFromWebElements(elements: Iterable<WebElement>): Navigator?
    fun createFromNavigators(navigators: Iterable<Navigate>): Navigator
    fun relativeTo(newBase: Navigator): NavigatorFactory
}

class BrowserBackedNavigatorFactory(browser: Browser, innerNavigatorFactory: InnerNavigatorFactory) : AbstractNavigatorFactory(browser, innerNavigatorFactory) {


    val locator = DefaultLocator(SearchContextBasedBasicLocator(browser.config.driver, this))
    val baseTagName = "html"

    open override fun createFromNavigators(navigators: Iterable<Navigate>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun getLocator(): Locator = locator
    fun createBase(): Navigator? =
            createFromWebElements(Collections.singletonList(browser.config.driver.findElement(By.tagName(baseTagName))))


    override fun getBase(): Navigator? {
        val baseNavigatorWaiting = browser.config.baseNavigatorWaiting
        if (baseNavigatorWaiting == null) {
            baseNavigatorWaiting.waitFor { createBase() }
        } else {
            return createBase()
        }
        return null
    }
}


open abstract class AbstractNavigatorFactory(val browser: Browser, override val innerNavigatorFactory: InnerNavigatorFactory) : NavigatorFactory {

    override fun createFromWebElements(elements: Iterable<WebElement>): Navigator? {
        val filtered = ArrayList<WebElement>()
        elements.asSequence().forEach {
            if (it != null) {
                filtered.add(it)
            }
        }
        return innerNavigatorFactory.createNavigator(browser, filtered)
    }

    override fun relativeTo(newBase: Navigator): NavigatorFactory = NavigatorBackedNavigatorFactory(newBase, innerNavigatorFactory)
}

class NavigatorBackedNavigatorFactory(newBase: Navigator, innerNavigatorFactory: InnerNavigatorFactory) : NavigatorFactory {

    // TODO UnsupportedOperationExceptionの山

    override val innerNavigatorFactory: InnerNavigatorFactory
        get() = throw UnsupportedOperationException()

    override fun getBase(): Navigator? {
        throw UnsupportedOperationException()
    }

    override fun getLocator(): Locator {
        throw UnsupportedOperationException()
    }

    override fun createFromWebElements(elements: Iterable<WebElement>): Navigator? {
        throw UnsupportedOperationException()
    }

    override fun createFromNavigators(navigators: Iterable<Navigate>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun relativeTo(newBase: Navigator): NavigatorFactory {
        throw UnsupportedOperationException()
    }

}

interface InnerNavigatorFactory {
    fun createNavigator(browser: Browser, filtered: ArrayList<WebElement>): Navigator?
}


class DefaultInnerNavigatorFactory : InnerNavigatorFactory {
    override fun createNavigator(browser: Browser, elements: ArrayList<WebElement>): Navigator? {
        return if (elements != null) {
            NonEmptyNavigator(browser, elements, EmptyLocator())
        } else {
            EmptyNavigator(browser, elements, EmptyLocator())
        }
    }
}

class PageContentTemplateFactoryDelegate(pageContentTemplate: PageContentTemplate, args: Array<Any>) {
}