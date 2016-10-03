package kebab.element

import kebab.BrowserBackedNavigatorFactory
import kebab.element.ByFunction
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

val MATCH_ALL_SELECTOR = "*"
/**
 * Created by yy_yank on 2015/12/30.
 */
class DefaultLocator(val locator: SearchContextBasedBasicLocator) : Locator {


    override fun find(bySelector: By): Navigator = locator.find(bySelector)

    override fun find(attributes: MutableMap<String, Any>, selector: String) = locator.find(attributes, selector)

    override fun find(selector: String) = find(By.cssSelector(selector))

    override fun find(attributes: MutableMap<String, Any>) = find(attributes, MATCH_ALL_SELECTOR)

    override fun find(selector: String, index: Int): Navigator = locator.find(By.cssSelector(selector)).getAt(index)

    override fun find(selector: String, range: ClosedRange<Int>) = locator.find(By.cssSelector(selector)).getAt(range)

    override fun find(attributes: MutableMap<String, Any>, bySelector: By) = find(bySelector).filter(attributes)

    override fun find(attributes: MutableMap<String, Any>, bySelector: By, index: Int) = locator.find(bySelector).filter(attributes).getAt(index)

    override fun find(attributes: MutableMap<String, Any>, bySelector: By, range: ClosedRange<Int>) = locator.find(bySelector).filter(attributes).getAt(range)

    override fun find(bySelector: By, index: Int) = locator.find(bySelector).getAt(index)

    override fun find(bySelector: By, range: ClosedRange<Int>) = locator.find(bySelector).getAt(range)


    override fun find(attributes: MutableMap<String, Any>, index: Int) = locator.find(attributes).getAt(index)

    override fun find(attributes: MutableMap<String, Any>, range: ClosedRange<Int>) = locator.find(attributes).getAt(range)

    // TODO UnsupportedOperationException
    override fun find(attributes: MutableMap<String, Any>, selector: String, index: Int): Navigator {
        throw UnsupportedOperationException()
    }
    override fun find(attributes: MutableMap<String, Any>, selector: String, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

}



class SearchContextBasedBasicLocator(val driver: WebDriver, val browserBackedNavigatorFactory: BrowserBackedNavigatorFactory) : BasicLocator {


    val BY_SELECTING_ATTRIBUTES = mapOf<String, ByFunction>(Pair("id", ByFunction({ id ->
        By.id(id)
    })), Pair("clazz", ByFunction({ name ->
        By.className(name)
    })), Pair("name", ByFunction { name ->
        By.name(name)
    }))

    override fun find(attributes: MutableMap<String, Any>, selector: String): Navigator {
        val attributesCopy = attributes
        val selectedUsingBy = findUsingByIfPossible(attributesCopy, selector)
        if (selectedUsingBy != null) {
            return selectedUsingBy
        }
        val optimizedSelector = optimizeSelector(selector, attributesCopy)
        return if (optimizedSelector != null) {
            find(By.cssSelector(optimizedSelector)).filter(attributesCopy)
        } else {
            find(attributes)
        }
    }

    fun find(attributes: MutableMap<String, Any>): Navigator {
        throw UnsupportedOperationException()
    }

    fun findUsingByIfPossible(attributes: MutableMap<String, Any>, selector: String): Navigator? {
        if (attributes.size == 1 && selector == MATCH_ALL_SELECTOR) {
            BY_SELECTING_ATTRIBUTES.asSequence().forEach {
                // TODO hasStringValueForKeyで型安全なんだが as 使うのやだな
                if (hasStringValueForKey(attributes, it.key)) {
                    return find(it.value.invoke(attributes[it.key] as String))
                }
            }
        }
        return null
    }

    fun hasStringValueForKey(attributes: MutableMap<String, Any>, key: String) = attributes.containsKey(key) && attributes[key] is String


    /**
     * Optimizes the selector by translating attributes map into a css attribute selector if possible.
     * Note this method has a side-effect in that it _removes_ those keys from the predicates map.
     */
    fun optimizeSelector(selector: String, attributes: MutableMap<String, Any>): String {
        if (selector == null) {
            return selector
        }

        val buffer = StringBuilder(selector)
        attributes.forEach {
            if (it.key != "text" && it.value is String) {
                if (it.key == "class") {
                    // TODO 正規表現でごにょごにょ
                    //                        it.value.toString().split(Pattern.compile("/\s+/"), 0).forEach { className ->
                    //                            CssSelector.escape(className)
                } else {
                    //                        buffer << """[${attribute.key}="${CssSelector.escape(attribute.value)}"]"""
                }
                attributes.remove(it.key)
            }
        }

        if (buffer.substring(0, 1) == MATCH_ALL_SELECTOR && buffer.length > 1) {
            buffer.deleteCharAt(0)
        }
        return buffer.toString()
    }

    override fun find(bySelector: By): Navigator {
        val elements = driver.findElements(bySelector)
        return browserBackedNavigatorFactory.createFromWebElements(elements)!!
    }
}

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

/**
 * Navigableインターフェース
 */
interface Navigable : Locator {
    fun find(): Navigator
    fun find(index: Int): Navigator
    fun find(range: ClosedRange<Int>): Navigator
}

/**
 * 実装のないLocator.関数を呼び出すとUnsupportedOperationExceptionを投げる.
 */
class EmptyLocator : Locator {

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

