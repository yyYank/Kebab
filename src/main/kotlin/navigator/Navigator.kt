package kebab

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Wait
import java.util.*


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
}

class Navigate {

}

val MATCH_ALL_SELECTOR = "*"

interface Locator : BasicLocator {

    /**
     * Shorthand for <code>find(null, selector, null)</code>
     *
     * @param selector
     * @return new Navigator
     */
    fun find(selector: String): Navigator

    /**
     * Creates a new Navigator instance containing the elements whose attributes match the specified values or patterns.
     * The key 'text' can be used to match the text contained in elements. Regular expression Pattern objects may be
     * used as values.
     * <p>Examples:</p>
     * <dl>
     * <dt>find(name: "firstName")</dt>
     * <dd>selects all elements with the name "firstName"</dd>
     * <dt>find(name: "firstName", readonly: "readonly")</dt>
     * <dd>selects all elements with the name "firstName" that are read-only</dd>
     * <dt>find(text: "I can has cheezburger")</dt>
     * <dd>selects all elements containing the exact text</dd>
     * <dt>find(text: ~/I can has.+/)</dt>
     * <dd>selects all elements whose text matches a regular expression</dd>
     * </dl>
     * @param predicates a Map with keys representing attributes and values representing required values or patterns
     * @return a new Navigator instance containing the matched elements
     */
    fun find(attributes: MutableMap<String, Any>): Navigator

    /**
     * Shorthand for <code>find(selector)[indexOfElement]</code>.
     * @param selector a CSS selector
     * @param index index of the required element in the selection
     * @return new Navigator instance containing a single element
     */
    fun find(selector: String, index: Int): Navigator

    /**
     * Shorthand for <code>find(null, selector, range)</code>
     *
     * @param selector The css selector
     * @return new Navigator
     */
    fun find(selector: String, range: ClosedRange<Int>): Navigator

    /**
     * Selects elements by both <code>By</code> selector and attributes. For example <code>find(By.tagName("input"), name: "firstName")</code> will select
     * all input elements with the name "firstName".
     * @param bySelector a WebDriver By selector
     * @param predicates a Map with keys representing attributes and values representing required values or patterns
     * @return a new Navigator instance containing the matched elements
     */
    fun find(attributes: MutableMap<String, Any>, bySelector: By): Navigator

    /**
     * Shorthand for <code>find(predicates, bySelector, index..index)</code>
     *
     * @param bySelector a WebDriver By selector
     * @return new Navigator
     */
    fun find(attributes: MutableMap<String, Any>, bySelector: By, index: Int): Navigator

    /**
     * Creates a new Navigator instance containing the elements matching the given
     * <code>By</code> type selector. Any <code>By</code> type capabilities supported by the underlying WebDriver instance are supported.
     * @param bySelector a WebDriver By selector
     * @return new Navigator instance containing the matched elements
     */
    fun find(attributes: MutableMap<String, Any>, bySelector: By, range: ClosedRange<Int>): Navigator

    /**
     * Shorthand for <code>find(bySelector)[indexOfElement]</code>.
     * @param bySelector a WebDriver By selector
     * @param index index of the required element in the selection
     * @return new Navigator instance containing a single element
     */
    fun find(bySelector: By, index: Int): Navigator

    /**
     * Shorthand for <code>find(null, bySelector, range)</code>
     *
     * @param bySelector a WebDriver By selector
     * @return new Navigator
     */
    fun find(bySelector: By, range: ClosedRange<Int>): Navigator

    /**
     * Shorthand for <code>find(predicates, null, index..index)</code>
     *
     * @param selector
     * @return new Navigator
     */
    fun find(attributes: MutableMap<String, Any>, index: Int): Navigator

    /**
     * Shorthand for <code>find(predicates, null, range)</code>
     *
     * @param predicates attribute predicates
     * @param predicates range the range of matches to select
     * @return new Navigator
     */
    fun find(attributes: MutableMap<String, Any>, range: ClosedRange<Int>): Navigator

    /**
     * Shorthand for <code>find(predicates, selector, index..index)</code>
     *
     * @param selector
     * @return new Navigator
     */
    fun find(attributes: MutableMap<String, Any>, selector: String, index: Int): Navigator

    /**
     * Creates a new Navigator instance containing the elements matching the given
     * CSS selector. Any CSS capabilities supported by the underlying WebDriver instance are supported.
     * If the underlying WebDriver instance does not natively support finding elements by CSS selectors then tag, id
     * and class name selectors may be applied (in any combination).
     * <p>Examples:</p>
     * <dl>
     * <dt>h1</dt>
     * <dd>selects all 'h1' elements</dd>
     * <dt>.article</dt>
     * <dd>selects all elements with the class 'article'</dd>
     * <dt>#header</dt>
     * <dd>selects the element with the id 'header'</dd>
     * <dt>div.article p</dt>
     * <dd>selects all p elements that are descendants of a div with class 'article'</dd>
     * <dt>h1, h2</dt>
     * <dd>selects all h1 and h2 elements</dd>
     * <dt>li:odd</dt>
     * <dd>selects odd-numbered li elements (CSS3 selectors like this are only supported when supported by the
     * underlying WebDriver instance)</dd>
     * </dl>
     * @param selector a CSS selector
     * @return new Navigator instance containing the matched elements
     */
    fun find(attributes: MutableMap<String, Any>, selector: String, range: ClosedRange<Int>): Navigator


}

/**
 * Defines element search operations that are sufficient to be able to implement a {@link geb.navigator.Locator} on top of.
 */
interface BasicLocator {

    /**
     * Shorthand for <code>find(null, bySelector, null)</code>
     *
     * @param bySelector a WebDriver By selector
     * @return new Navigator
     */
    fun find(bySelector: By): Navigator

    /**
     * Selects elements by both CSS selector and attributes. For example find("input", name: "firstName") will select
     * all input elements with the name "firstName".
     * @param selector a CSS selector
     * @param predicates a Map with keys representing attributes and values representing required values or patterns
     * @return a new Navigator instance containing the matched elements
     */
    fun find(attributes: MutableMap<String, Any>, selector: String): Navigator
}

interface Navigable : Locator {

    fun find(): Navigator
    fun find(index: Int): Navigator
    fun find(range: ClosedRange<Int>): Navigator

}

class EmptyLocator : Locator {
    // TODO UnsupportedOperationExceptionの山

    override fun find(attributes: MutableMap<String, Any>): Navigator {
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

    override fun find(attributes: MutableMap<String, Any>, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, selector: String, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, selector: String, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(bySelector: By, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(bySelector: By, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(selector: String, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(selector: String, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(bySelector: By): Navigator {
        throw UnsupportedOperationException()
    }

}

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

class NonEmptyNavigator(browser: Browser, val elements: ArrayList<WebElement>, locator: Locator) : AbstractNavigator(browser, locator) {
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

    override fun click(): Navigator {
        elements.first { it != null }.click()
        return this
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

    override fun iterator(): Iterator<Navigator> {
        throw UnsupportedOperationException()
    }

}

class EmptyNavigator(browser: Browser, val elements: ArrayList<WebElement>, locator: Locator) : AbstractNavigator(browser, locator) {
    override fun getAt(index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun getAt(range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    // TODO UnsupportedOperationExceptionの山

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