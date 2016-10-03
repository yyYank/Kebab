package kebab.navigator

import kebab.core.Browser
import kebab.locator.Locator
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

/**
 * Created by yy_yank on 2016/10/03.
 */
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