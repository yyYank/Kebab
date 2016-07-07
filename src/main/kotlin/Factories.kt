package kebab

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.util.*

/**
 * Created by yy_yank on 2015/12/19.
 */


interface NavigatorFactory {
    val innerNavigatorFactory: InnerNavigatorFactory

    fun getBase(): Navigator?
    fun getLocator(): Locator
    fun createFromWebElements(elements: Iterable<WebElement>): Navigator?
    fun createFromNavigators(navigators: Iterable<Navigate>): Navigator
    fun relativeTo(newBase: Navigator): NavigatorFactory
}

class BrowserBackedNavigatorFactory(browser: Browser, innerNavigatorFactory: InnerNavigatorFactory) : AbstractNavigatorFactory(browser, innerNavigatorFactory) {


    val locator = DefaultLocator(SearchContextBasedBasicLocator(browser.config.driver, this))
    val baseTagName = "html"

    open override fun createFromNavigators(navigators: Iterable<Navigate>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun getLocator(): Locator = locator
    fun createBase(): Navigator? =
            createFromWebElements(Collections.singletonList(browser.config.driver.findElement(By.tagName(baseTagName))))


    override fun getBase(): Navigator? {
        val baseNavigatorWaiting = browser.config.baseNavigatorWaiting
        if (baseNavigatorWaiting == null) {
            baseNavigatorWaiting.waitFor { createBase() }
        } else {
            return createBase()
        }
        return null
    }
}


open abstract class AbstractNavigatorFactory(val browser: Browser, override val innerNavigatorFactory: InnerNavigatorFactory) : NavigatorFactory {

    override fun createFromWebElements(elements: Iterable<WebElement>): Navigator? {
        val filtered = ArrayList<WebElement>()
        elements.asSequence().forEach {
            if (it != null) {
                filtered.add(it)
            }
        }
        return innerNavigatorFactory.createNavigator(browser, filtered)
    }

    override fun relativeTo(newBase: Navigator): NavigatorFactory = NavigatorBackedNavigatorFactory(newBase, innerNavigatorFactory)
}

class NavigatorBackedNavigatorFactory(newBase: Navigator, innerNavigatorFactory: InnerNavigatorFactory) : NavigatorFactory {

    // TODO UnsupportedOperationExceptionの山

    override val innerNavigatorFactory: InnerNavigatorFactory
        get() = throw UnsupportedOperationException()

    override fun getBase(): Navigator? {
        throw UnsupportedOperationException()
    }

    override fun getLocator(): Locator {
        throw UnsupportedOperationException()
    }

    override fun createFromWebElements(elements: Iterable<WebElement>): Navigator? {
        throw UnsupportedOperationException()
    }

    override fun createFromNavigators(navigators: Iterable<Navigate>): Navigator {
        throw UnsupportedOperationException()
    }

    override fun relativeTo(newBase: Navigator): NavigatorFactory {
        throw UnsupportedOperationException()
    }

}

interface InnerNavigatorFactory {
    fun createNavigator(browser: Browser, filtered: ArrayList<WebElement>): Navigator?
}


class DefaultInnerNavigatorFactory : InnerNavigatorFactory {
    override fun createNavigator(browser: Browser, elements: ArrayList<WebElement>): Navigator? {
        return if (elements != null) {
            NonEmptyNavigator(browser, elements, EmptyLocator())
        } else {
            EmptyNavigator(browser, elements, EmptyLocator())
        }
    }
}

class PageContentTemplateFactoryDelegate(pageContentTemplate: PageContentTemplate, args: Array<Any>) {
    //    static final DISALLOWED_MODULE_PARAMS = ['navigator', '_args']
    //
    //    private PageContentTemplate template
    //    private Object[] args
    //
    //    @Delegate
    //    private final NavigableSupport navigableSupport
    //
    //    PageContentTemplateFactoryDelegate(PageContentTemplate template, Object[] args) {
    //        this.template = template
    //        this.navigableSupport = new NavigableSupport(template.navigatorFactory)
    //        this.args = args
    //    }
    //
    //    def methodMissing(String name, args) {
    //        template.owner."$name"(*args)
    //    }
    //
    //    def propertyMissing(String name) {
    //        template.owner."$name"
    //    }
    //
    //    def module(Map params, Class<? extends Module> moduleClass) {
    //        module(params, moduleClass, null)
    //    }
    //
    //    def module(Class<? extends Module> moduleClass, container) {
    //        module(null, moduleClass, container)
    //    }
    //
    //    def module(Map params, Class<? extends Module> moduleClass, Navigator base) {
    //        def moduleParams = params ?: [:]
    //
    //        if (!(moduleClass in Module)) {
    //            throw new InvalidPageContent("${moduleClass} should extend from ${Module} to be allowed to be a part of a module definition with name '${template.name}'")
    //        }
    //
    //        // Make sure they haven't used params that map to our internal ivars
    //        if (moduleParams.any { it.key in DISALLOWED_MODULE_PARAMS }) {
    //            def disallowed = DISALLOWED_MODULE_PARAMS.join(', ')
    //            throw new InvalidPageContent("Params for module $moduleClass with name ${template.name} contains one or more disallowed params (${disallowed})")
    //        }
    //
    //        def baseNavigatorFactory = base != null ? template.navigatorFactory.relativeTo(base) : template.navigatorFactory
    //
    //        def module = moduleClass.newInstance()
    //
    //        NavigatorFactory moduleBaseNavigatorFactory = ModuleBaseCalculator.calculate(module, baseNavigatorFactory, moduleParams)
    //
    //        module.init(template.browser, moduleBaseNavigatorFactory)
    //        moduleParams.each { name, value ->
    //            // TODO - catch NPE and provide better error message
    //            module."$name" = value
    //        }
    //
    //        module
    //    }
    //
    //    /**
    //     * Returns a list of module instances, where the nth instance will use the
    //     * nth navigator as its base.
    //     */
    //    def moduleList(Map params, Class moduleClass, Navigator navigator, index = null) {
    //        if (index != null) {
    //            def modules = index.collect { module params, moduleClass, navigator[it] }
    //            modules.size() > 1 ? modules : modules.first()
    //        } else {
    //            (0..<navigator.size()).collect { module params, moduleClass, navigator[it] }
    //        }
    //    }
    //
    //    def moduleList(Class moduleClass, Navigator navigator, index = null) {
    //        moduleList(null, moduleClass, navigator, index)
    //    }
    //
    //    Navigator $() {
    //        navigableSupport.$()
    //    }
    //
    //    Navigator $(int index) {
    //        navigableSupport.$(index)
    //    }
    //
    //    Navigator $(Range<Integer> range) {
    //        navigableSupport.$(range)
    //    }
    //
    //    Navigator $(String selector) {
    //        navigableSupport.$(selector)
    //    }
    //
    //    Navigator $(String selector, int index) {
    //        navigableSupport.$(selector, index)
    //    }
    //
    //    Navigator $(String selector, Range<Integer> range) {
    //        navigableSupport.$(selector, range)
    //    }
    //
    //    Navigator $(Map<String, Object> attributes) {
    //        navigableSupport.$(attributes)
    //    }
    //
    //    Navigator $(Map<String, Object> attributes, int index) {
    //        navigableSupport.$(attributes, index)
    //    }
    //
    //    Navigator $(Map<String, Object> attributes, Range<Integer> range) {
    //        navigableSupport.$(attributes, range)
    //    }
    //
    //    Navigator $(Map<String, Object> attributes, String selector) {
    //        navigableSupport.$(attributes, selector)
    //    }
    //
    //    Navigator $(Map<String, Object> attributes, String selector, int index) {
    //        navigableSupport.$(attributes, selector, index)
    //    }
    //
    //    Navigator $(Map<String, Object> attributes, String selector, Range<Integer> range) {
    //        navigableSupport.$(attributes, selector, range)
    //    }
    //
    //    Navigator $(Map<String, Object> attributes, By bySelector) {
    //        navigableSupport.find(attributes, bySelector)
    //    }
    //
    //    Navigator $(Map<String, Object> attributes, By bySelector, int index) {
    //        navigableSupport.find(attributes, bySelector, index)
    //    }
    //
    //    Navigator $(Map<String, Object> attributes, By bySelector, Range<Integer> range) {
    //        navigableSupport.find(attributes, bySelector, range)
    //    }
    //
    //    Navigator $(By bySelector) {
    //        navigableSupport.find(bySelector)
    //    }
    //
    //    Navigator $(By bySelector, int index) {
    //        navigableSupport.find(bySelector, index)
    //    }
    //
    //    Navigator $(By bySelector, Range<Integer> range) {
    //        navigableSupport.find(bySelector, range)
    //    }
    //
    //    Navigator $(Navigator[] navigators) {
    //        navigableSupport.$(navigators)
    //    }
    //
    //    Navigator $(WebElement[] elements) {
    //        navigableSupport.$(elements)
    //    }
}