package kebab

import kebab.BrowserBackedNavigatorFactory
import kebab.Locator
import kebab.Navigator
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import java.util.regex.Pattern
import kotlin.collections.asSequence
import kotlin.collections.forEach
import kotlin.collections.mapOf
import kotlin.collections.toLinkedMap
import kotlin.sequences.filter
import kotlin.text.split

/**
 * Created by yy_yank on 2015/12/30.
 */
class DefaultLocator(val locator: SearchContextBasedBasicLocator) : Locator {

    // TODO UnsupportedOperationExceptionの山

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

    override fun find(attributes: MutableMap<String, Any>, selector: String, index: Int) : Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, selector: String, range: ClosedRange<Int>) : Navigator{
        throw UnsupportedOperationException()
    }

}

interface Func<T>{
    abstract fun invoke() : T
    abstract fun invoke1(value : String) : T
}

class Function<T>(val f: () -> T) : Func<T>{
    override fun invoke1(value : String): T {
        throw UnsupportedOperationException()
    }

    override fun invoke() = f()
}
class Function1<T>(val f: (value : String) -> T) : Func<T>{
    override fun invoke(): T {
        throw UnsupportedOperationException()
    }

    override fun invoke1(value : String) : T = f(value)
}

class SearchContextBasedBasicLocator(val driver: WebDriver, val browserBackedNavigatorFactory: BrowserBackedNavigatorFactory) : BasicLocator {


    val BY_SELECTING_ATTRIBUTES = mapOf<String, Func<*>>(Pair("id", Function1<By>({id ->
         By.id(id)
    })), Pair("clazz", Function<java.lang.Class<By>>({
        By::class.java
    })),Pair("name", Function1<By>{ name ->
        By.name(name)
    }))

        override fun find(attributes: MutableMap<String, Any>, selector: String) : Navigator{
            val attributesCopy = attributes
            val selectedUsingBy = findUsingByIfPossible(attributesCopy, selector)
            if (selectedUsingBy != null) {
                return selectedUsingBy
            }
            val optimizedSelector = optimizeSelector(selector, attributesCopy)
            return if(optimizedSelector != null){ find(By.cssSelector(optimizedSelector)).filter(attributesCopy) } else { find(attributes)}
        }

        fun find(attributes: MutableMap<String, Any>) : Navigator {
            throw UnsupportedOperationException()
        }

        fun findUsingByIfPossible(attributes: MutableMap<String, Any>, selector: String) : Navigator? {
            if (attributes.size == 1 && selector == MATCH_ALL_SELECTOR) {
                // TODO findでごにょごにょ
                //BY_SELECTING_ATTRIBUTES.asSequence().filter {
                //    if (hasStringValueForKey(attributes, it.key)) {
                //        return find(it.value.call(attributes[it.key]))
                //    }
                //}
            }
            return null
        }

        /**
         * Optimizes the selector by translating attributes map into a css attribute selector if possible.
         * Note this method has a side-effect in that it _removes_ those keys from the predicates map.
         */
        fun optimizeSelector(selector: String, attributes: MutableMap<String, Any>) : String{
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

        override fun find(bySelector : By) : Navigator {
            val elements = driver.findElements(bySelector)
            return browserBackedNavigatorFactory.createFromWebElements(elements)!!
        }
}

