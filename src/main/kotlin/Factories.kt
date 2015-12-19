package kebab

import kebab.Navigate
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.xml.sax.Locator

/**
 * Created by yy_yank on 2015/12/19.
 */
interface  Navigator : Iterable<Navigator>, Locator {
    fun has(selector : String) : Navigator
    fun has(predicates : Map<String, Any>, selector : String) : Navigator
    fun has(bySelector : By) : Navigator
    fun has(predicates: Map<String, Any>, bySelector: By) : Navigator
    fun filter(selector: String) : Navigator
    fun filter(predicates: Map<String, Any>) : Navigator
    fun filter(predicates: Map<String, Any>, selector: String) : Navigator
    fun not(predicates: Map<String, Any>, selector: String) : Navigator
}


interface NavigatorFactory{
    fun getBase() : Navigator
    fun getLocator() : Locator
    fun createFromWebElements(elements : Iterable<WebElement> ) : Navigator
    fun createFromNavigators(navigators : Iterable<Navigate> ) : Navigator
    fun relativeTo(newBase : Navigator) : NavigatorFactory
}