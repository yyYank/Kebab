package kebab.locator

import kebab.navigator.Navigator
import org.openqa.selenium.By


/**
 * Created by yy_yank on 2016/10/03.
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