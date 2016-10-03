package kebab.support.navigate

import kebab.NavigatorFactory
import kebab.navigator.Navigable
import kebab.navigator.Navigator
import org.openqa.selenium.By

/**
 * Created by yy_yank on 2016/10/02.
 */
class NavigableSupport(val navigatorFactory: NavigatorFactory) : Navigable {
    override fun find(attributes: MutableMap<String, Any>, selector: String, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(range: ClosedRange<Int>): Navigator {
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

    override fun find(attributes: MutableMap<String, Any>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, selector: String): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(attributes: MutableMap<String, Any>, selector: String, index: Int): Navigator {
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

    override fun find(bySelector: By) = navigatorFactory.getLocator().find(bySelector)


    override fun find(bySelector: By, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(bySelector: By, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(): Navigator {
        throw UnsupportedOperationException()
    }

}