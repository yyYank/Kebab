package kebab.element

import kebab.core.Browser
import kebab.core.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Wait
import java.util.*

/**
 * Created by yy_yank on 2016/10/03.
 */
/**
 * 実装のないNavigator.呼び出すとUnsupportedOperationException
 */
class EmptyNavigator(browser: Browser, val elements: ArrayList<WebElement>, locator: Locator) : AbstractNavigator(browser, locator) {
    override fun value(s: String): Navigator {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    override fun getAt(index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun getAt(range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun attr(name: String): String {
        throw UnsupportedOperationException()
    }

    override fun click(): Navigator {
        throw UnsupportedOperationException()
    }

    override fun click(pageClass: Class<out Page>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun click(pageClass: Class<out Page>, wait: Wait<Any>) {
        throw UnsupportedOperationException()
    }

    override fun click(pageInstance: Page) {
        throw UnsupportedOperationException()
    }

    override fun click(pageInstance: Page, wait: Wait<Any>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun click(potentialPages: List<out Page>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun click(potentialPages: List<out Page>, wait: Wait<Any>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun filter(predicates: Map<String, Any>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun filter(predicates: Map<String, Any>, selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun filter(selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun getAttribute(name: String): String {
        throw UnsupportedOperationException()
    }

    override fun has(bySelector: By): Navigator {
        throw UnsupportedOperationException()
    }

    override fun has(predicates: Map<String, Any>, bySelector: By): Navigator {
        throw UnsupportedOperationException()
    }

    override fun has(predicates: Map<String, Any>, selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun has(selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun not(predicates: Map<String, Any>, selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun tag(): String {
        throw UnsupportedOperationException()
    }

    override fun text(): String {
        throw UnsupportedOperationException()
    }

    override fun iterator(): Iterator<Navigator> {
        throw UnsupportedOperationException()
    }

    override fun getElement(index: Int) = elements.get(index)

}