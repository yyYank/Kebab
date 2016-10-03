package kebab.locator

import kebab.BrowserBackedNavigatorFactory
import kebab.function.ByFunction
import kebab.navigator.Navigator
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver


val MATCH_ALL_SELECTOR = "*"

/**
 * Created by yy_yank on 2015/12/30.
 */
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



