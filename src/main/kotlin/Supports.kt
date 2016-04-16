/**
 * Created by yy_yank on 2015/12/19.
 */
package kebab

import org.openqa.selenium.By

class UninitializedNavigableSupport(page: Page) : Navigable {
    override fun find(attributes: MutableMap<String, Any>, selector: String, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(): Navigator {
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

    override fun find(bySelector: By): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(bySelector: By, index: Int): Navigator {
        throw UnsupportedOperationException()
    }

    override fun find(bySelector: By, range: ClosedRange<Int>): Navigator {
        throw UnsupportedOperationException()
    }

}

interface AlertAndConfirmSupport {

}

class UninitializedAlertAndConfirmSupport(page: Page) : AlertAndConfirmSupport {

}

class TextMatchingSupport {

}

class UninitializedWaitingSupport(page: Page) : WaitingSupport {
    override fun <T> waitFor(f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(waitPreset: String, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, waitPreset: String, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(timeout: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, timeout: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(timeout: Double, interval: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, timeout: Double, interval: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }
}

class UninitializedDownloadSupport(page: Page) : DownloadSupport
class UninitializedPageContentSupport(page: Page) : PageContentSupport

interface WaitingSupport {
    fun <T> waitFor(waitPreset : String, f : () -> T) : T
    fun <T> waitFor(params : Map<String,Any>, waitPreset : String, f : () -> T) : T
    fun <T> waitFor(f : () -> T) : T
    fun <T> waitFor(params : Map<String,Any>, f : () -> T) : T
    fun <T> waitFor(timeout : Double, f : () -> T) : T
    fun <T> waitFor(params : Map<String,Any>, timeout : Double,  f : () -> T) : T
    fun <T> waitFor(timeout : Double, interval : Double,  f : () -> T) : T
    fun <T> waitFor(params : Map<String,Any>, timeout : Double, interval : Double,  f : () -> T) : T
}

interface DownloadSupport {

}

interface PageContentSupport {

}

interface PageContainer {

}

interface Initializable {

}

interface Navigatable {

}


class DefaultAlertAndConfirmSupport(function: () -> Any, config: Configuration) : AlertAndConfirmSupport {

}

class DefaultInteractionsSupport(browser: Browser) : InteractionsSupport {

}

class UninitializedInteractionSupport(page: Page) : InteractionsSupport {

}

interface InteractionsSupport {

}

class DefaultFrameSupport(browser: Browser) : FrameSupport {

}

class DefaultDownloadSupport(browser: Browser) : DownloadSupport {

}

class UninitializedFrameSupport(page: Page) : FrameSupport {

}

interface FrameSupport {

}

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

class DefaultWaitingSupport(config: Configuration) : WaitingSupport {
    override fun <T> waitFor(params: Map<String, Any>, waitPreset: String, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(timeout: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, timeout: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(timeout: Double, interval: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, timeout: Double, interval: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(waitPreset: String, f: () -> T): T {
        throw UnsupportedOperationException()
    }

}

class DefaultPageContentSupport(page: Page, contentTemplates: Any, navigatorFactory: NavigatorFactory) : PageContentSupport {

}