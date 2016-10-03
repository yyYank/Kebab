package kebab.navigator

import kebab.core.Browser
import kebab.core.Page
import kebab.locator.Locator
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Wait
import java.util.*

/**
 * Created by yy_yank on 2016/10/03.
 */
/**
 * 実装を持つNavigatorクラス.
 */
class NonEmptyNavigator(browser: Browser, val elements: ArrayList<WebElement>, locator: Locator) : AbstractNavigator(browser, locator) {

    /**
     * 現状実装だとfindしたもののうち表示されているものの一番最初のエレメントをにsendする
     */
    override fun value(s: String): Navigator = apply {
        elements.first().sendKeys(s)
        this
    }

    override fun getAt(range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun getAt(index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    // TODO UnsupportedOperationExceptionの山

    override fun getElement(index: Int) = elements.get(index)

    override fun has(selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun has(predicates: Map<String, Any>, selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun has(bySelector: By): Navigator {
        throw UnsupportedOperationException()
    }

    override fun has(predicates: Map<String, Any>, bySelector: By): Navigator {
        throw UnsupportedOperationException()
    }

    override fun filter(selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun filter(predicates: Map<String, Any>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun filter(predicates: Map<String, Any>, selector: String): Navigator {
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

    override fun getAttribute(name: String): String {
        throw UnsupportedOperationException()
    }

    override fun attr(name: String): String {
        throw UnsupportedOperationException()
    }

    /**
     * 現状実装だとfindしたもののうち表示されているものの一番最初のエレメントをクリックする
     */
    override fun click(): Navigator = apply {
        elements.filter { it.isDisplayed }.first().click()
        this
    }

    override fun click(pageClass: Class<out Page>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun click(pageInstance: Page) {
        throw UnsupportedOperationException()
    }

    override fun click(pageClass: Class<out Page>, wait: Wait<Any>) {
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

    override fun iterator(): Iterator<Navigator>  = this.iterator()

}