package kebab.elment

import kebab.Browser
import kebab.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Wait
import java.util.*


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


/**
 * Navigator.エレメントの基本操作を行う。
 */
interface Navigator : Iterable<Navigator>, Locator {
    fun has(selector: String): Navigator
    fun has(predicates: Map<String, Any>, selector: String): Navigator
    fun has(bySelector: By): Navigator
    fun has(predicates: Map<String, Any>, bySelector: By): Navigator
    fun filter(selector: String): Navigator
    fun filter(predicates: Map<String, Any>): Navigator
    fun filter(predicates: Map<String, Any>, selector: String): Navigator
    fun not(predicates: Map<String, Any>, selector: String): Navigator
    /**
     * Returns the tag name of the first context element.
     * @return the tag name of the first context element
     */
    fun tag(): String

    /**
     * Returns the text content of the first context element.
     * @return the text content of the first context element
     */
    fun text(): String

    /**
     * Returns the value of the given attribute of the first context element.
     * @param name name of the attribute
     * @return the value of the given attribute of the first context element
     */
    fun getAttribute(name: String): String

    /**
     * Returns the value of the given attribute of the first context element.
     * @param name name of the attribute
     * @return the value of the given attribute of the first context element
     */
    fun attr(name: String): String

    /**
     * Clicks on the first context element.
     */
    fun click(): Navigator

    /**
     * Clicks on the first context element, verifies the at checker (if it is defined) of the class passed as the argument and sets it as the current page.
     *
     * @param pageClass page class to be used as the new current page after clicking the element
     * @return this
     */
    fun click(pageClass: Class<out Page>): Navigator

    /**
     * Clicks on the first context element, verifies the at checker (if it is defined) of the instance passed as the argument and sets it as the current page.
     *
     * @param pageInstance page instance to be used as the new current page after clicking the element
     * @return this
     */
    fun click(pageInstance: Page)

    /**
     * Clicks on the first context element, verifies the at checker (if it is defined) of the instance passed as the argument and sets it as the current page.
     *
     * @param pageClass page class to be used as the new current page after clicking the element
     * @param wait configuration to be used for waiting for the at checker to succeed
     * @return this
     */
    fun click(pageClass: Class<out Page>, wait: Wait<Any>)

    /**
     * Clicks on the first context element, verifies the at checker (if it is defined) of the instance passed as the argument and sets it as the current page.
     *
     * @param pageInstance page instance to be used as the new current page after clicking the element
     * @param wait configuration to be used for waiting for the at checker to succeed
     * @return this
     */
    fun click(pageInstance: Page, wait: Wait<Any>): Navigator

    /**
     * Clicks on the first context element, finds the first page from the list for which the at checker is defined and sets it as the current page.
     *
     * @param potentialPages a list of classes extending {@link geb.Page} or a list of instances of such classes
     * @return this
     */
    fun click(potentialPages: List<out Page>): Navigator

    /**
     * Clicks on the first context element, finds the first page from the list for which the at checker is defined and sets it as the current page.
     *
     * @param potentialPages a list of classes extending {@link geb.Page} or a list of instances of such classes
     * @param wait configuration to be used for waiting for the at checkers to succeed
     * @return this
     */
    fun click(potentialPages: List<out Page>, wait: Wait<Any>): Navigator

    fun getAt(index: Int): Navigator

    fun getAt(range: ClosedRange<Int>): Navigator

    fun  value(s: String) : Navigator

}

class Navigate {
}


/**
 * Navigatorのabstractクラス.
 */
abstract class AbstractNavigator(val browser: Browser, val locator: Locator) : Navigator {

    abstract fun getElement(index: Int): WebElement

    override fun find(selector: String) = locator.find(selector)

    override fun find(selector: String, index: Int) = locator.find(selector, index)

    override fun find(selector: String, range: ClosedRange<Int>) = locator.find(selector, range)

    override fun find(predicates: MutableMap<String, Any>) = locator.find(predicates)

    override fun find(predicates: MutableMap<String, Any>, selector: String) = locator.find(predicates, selector)

    override fun find(predicates: MutableMap<String, Any>, selector: String, index: Int) = locator.find(predicates, selector, index)

    override fun find(predicates: MutableMap<String, Any>, selector: String, range: ClosedRange<Int>) = locator.find(predicates, selector, range)

    override fun find(predicates: MutableMap<String, Any>, bySelector: By) = locator.find(predicates, bySelector)

    override fun find(predicates: MutableMap<String, Any>, bySelector: By, index: Int) = locator.find(predicates, bySelector, index)

    override fun find(predicates: MutableMap<String, Any>, bySelector: By, range: ClosedRange<Int>) = locator.find(predicates, bySelector, range)

    override fun find(predicates: MutableMap<String, Any>, index: Int) = locator.find(predicates, index)

    override fun find(predicates: MutableMap<String, Any>, range: ClosedRange<Int>) = locator.find(predicates, range)

    override fun find(bySelector: By): Navigator = locator.find(bySelector)

    override fun find(bySelector: By, index: Int): Navigator = locator.find(bySelector, index)

    override fun find(bySelector: By, range: ClosedRange<Int>) = locator.find(bySelector, range)

}


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