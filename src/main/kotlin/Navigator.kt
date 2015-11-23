package kebab

import jdk.internal.org.xml.sax.Locator
import org.openqa.selenium.By
import org.openqa.selenium.WebElement


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


interface NavigateFactory{
    fun getBase() : Navigator
    fun getLocator() : Locator
    fun createFromWebElements(elements : Iterable<WebElement> ) : Navigator
    fun createFromNavigators(navigators : Iterable<Navigate> ) : Navigator
    fun relativeTo(newBase : Navigator) : NavigatorFactory
}

interface NavigatorFactory {

}

class Navigate {

}

