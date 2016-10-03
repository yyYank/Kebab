package kebab.locator

import kebab.navigator.Navigator
import org.openqa.selenium.By

/**
 * Created by yy_yank on 2016/10/03.
 */
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