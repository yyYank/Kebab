package kebab

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Wait
import java.util.*

fun configuration(init: Configuration.() -> Unit): Configuration {
    val configuration = Configuration()
    configuration.init()
    configuration.options.setup(configuration.driver)
    return configuration
}

class Configuration() {

    lateinit var baseUrl: String

    lateinit var driver:WebDriver

    lateinit var options: Options

    fun options(init: Options.() -> Unit) {
        val options = Options()
        options.init()
        this.options = options
    }

    val baseNavigatorWaiting: Wait<Any>? = null
    val rawConfig = HashMap<String, NavigatorFactory>()

    /**
     * Creates the navigator factory to be used.
     *
     * Returns {@link BrowserBackedNavigatorFactory} by default.
     * <p>
     * Override by setting the 'navigatorFactory' to a closure that takes a single {@link Browser} argument
     * and returns an instance of {@link NavigatorFactory}
     *
     * @param browser The browser to use as the basis of the navigatory factory.
     * @return
     */
    fun createNavigatorFactory(browser: Browser): NavigatorFactory {
        val navigatorFactory = readValue("navigatorFactory", null)
        if (navigatorFactory == null) {
            return BrowserBackedNavigatorFactory(browser, getInnerNavigatorFactory())
        } else {
            val result = navigatorFactory.getBase()
            if (result is NavigatorFactory) {
                return result
            }

            throw InvalidGebConfiguration("navigatorFactory is '${navigatorFactory}', it should be a Closure that returns a NavigatorFactory implementation")
        }
        return BrowserBackedNavigatorFactory(browser, getInnerNavigatorFactory())
    }

    private fun readValue(key: String, defaultValue : NavigatorFactory?) =
            if (rawConfig.containsKey(key)) {
                rawConfig.get(key)
            } else {
                defaultValue
            }
    /**
     * Returns the inner navigatory factory, that turns WebElements into Navigators.
     *
     * Returns {@link DefaultInnerNavigatorFactory} instances by default.
     * <p>
     * To override, set 'innerNavigatorFactory' to:
     * <ul>
     * <li>An instance of {@link InnerNavigatorFactory}
     * <li>A Closure, that has the signature ({@link Browser}, List<{@link org.openqa.selenium.WebElement}>)
     * </ul>
     *
     * @return The inner navigator factory.
     */
    fun getInnerNavigatorFactory() : InnerNavigatorFactory {
        val innerNavigatorFactory = readValue("innerNavigatorFactory", null)
        if (innerNavigatorFactory == null) {
            return DefaultInnerNavigatorFactory()
        } else if (innerNavigatorFactory is InnerNavigatorFactory) {
            return innerNavigatorFactory
            //        } else if (innerNavigatorFactory is Closure) {
            //            ClosureInnerNavigatorFactory(innerNavigatorFactory)
        } else {
            throw InvalidGebConfiguration("innerNavigatorFactory is '${innerNavigatorFactory}', it should be a Closure or InnerNavigatorFactory implementation")
        }
    }
}