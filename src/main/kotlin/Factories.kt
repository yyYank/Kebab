package kebab

import kebab.Navigate
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.util.*
import kotlin.collections.asSequence
import kotlin.collections.first
import kotlin.collections.firstOrNull
import kotlin.properties.Delegates
import kotlin.sequences.forEach

/**
 * Created by yy_yank on 2015/12/19.
 */



interface NavigatorFactory{
    val innerNavigatorFactory: InnerNavigatorFactory

    fun getBase() : Navigator?
    fun getLocator() : Locator
    fun createFromWebElements(elements : Iterable<WebElement> ) : Navigator?
    fun createFromNavigators(navigators : Iterable<Navigate> ) : Navigator
    fun relativeTo(newBase : Navigator) : NavigatorFactory
}

class BrowserBackedNavigatorFactory(browser: Browser, innerNavigatorFactory: InnerNavigatorFactory) : AbstractNavigatorFactory(browser, innerNavigatorFactory) {


    val locator = DefaultLocator(SearchContextBasedBasicLocator(browser.config.driver, this))
    val baseTagName = "html"

    open override fun createFromNavigators(navigators: Iterable<Navigate>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun getLocator(): Locator = locator
    fun createBase() : Navigator? =
        createFromWebElements(Collections.singletonList(browser.config.driver.findElement(By.tagName(baseTagName))))


    override fun getBase() : Navigator? {
        val baseNavigatorWaiting = browser.config.baseNavigatorWaiting
        if (baseNavigatorWaiting == null) {
            // TODO waitForが謎。というか、baseNavigatorWaitingの型が謎
            // baseNavigatorWaiting.waitFor { createBase() }
        } else {
            return createBase()
        }
        return null
    }
}

class DefaultLocator(val searchContextBasedBasicLocator: SearchContextBasedBasicLocator) : Locator {
    override fun find(bySelector: By): Navigator = searchContextBasedBasicLocator.find(bySelector)!!


    override fun find(attributes: MutableMap<String, Any>, selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(selector: String, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(selector: String, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, bySelector: By): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, bySelector: By, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, bySelector: By, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(bySelector: By, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(bySelector: By, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, range: ClosedRange<Int>) {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, selector: String, index: Int) {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, selector: String, range: ClosedRange<Int>) {
        throw UnsupportedOperationException()
    }

}

class SearchContextBasedBasicLocator(val driver: WebDriver, val browserBackedNavigatorFactory: BrowserBackedNavigatorFactory) {
    fun find(bySelector : By) : Navigator? {
        val elements = driver.findElements(bySelector)
        return browserBackedNavigatorFactory.createFromWebElements(elements)
    }

}


open abstract class AbstractNavigatorFactory (val browser : Browser, override val innerNavigatorFactory : InnerNavigatorFactory) : NavigatorFactory {

    override fun createFromWebElements(elements : Iterable<WebElement>)  : Navigator? {
        val filtered = ArrayList<WebElement>()
        elements.asSequence().forEach {
            if (it != null) {
                filtered.add(it)
            }
        }
        return innerNavigatorFactory.createNavigator(browser, filtered)
    }


//    fun createFromNavigators(navigators : Iterable<Navigator>) : Navigator{
//        val filtered = ArrayList<Navigator>()
//        navigators.asSequence().forEach {
//            if (it != null) {
//                filtered.add(it)
//            }
//        }
//        innerNavigatorFactory.createNavigator(browser, filtered)
//    }

    override fun relativeTo(newBase : Navigator) : NavigatorFactory = NavigatorBackedNavigatorFactory(newBase, innerNavigatorFactory)


}

class NavigatorBackedNavigatorFactory(newBase: Navigator, innerNavigatorFactory: InnerNavigatorFactory) : NavigatorFactory{
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
    fun createNavigator(browser: Browser, filtered: ArrayList<WebElement>) : Navigator?

}


class DefaultInnerNavigatorFactory : InnerNavigatorFactory{
    override fun createNavigator(browser: Browser, elements: ArrayList<WebElement>): Navigator? {
        // TODO Locator
        return if(elements != null) { NonEmptyNavigator(browser, elements, EmptyLocator())} else { EmptyNavigator(browser, elements, EmptyLocator()) }
    }

}