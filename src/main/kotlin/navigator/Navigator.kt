package kebab.navigator

import kebab.core.Page
import kebab.locator.Locator
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.Wait

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
     * @param potentialPages a list of classes extending {@link kebab.Page} or a list of instances of such classes
     * @return this
     */
    fun click(potentialPages: List<out Page>): Navigator

    /**
     * Clicks on the first context element, finds the first page from the list for which the at checker is defined and sets it as the current page.
     *
     * @param potentialPages a list of classes extending {@link kebab.Page} or a list of instances of such classes
     * @param wait configuration to be used for waiting for the at checkers to succeed
     * @return this
     */
    fun click(potentialPages: List<out Page>, wait: Wait<Any>): Navigator

    fun getAt(index: Int): Navigator

    fun getAt(range: ClosedRange<Int>): Navigator

    fun value(s: String): Navigator

}