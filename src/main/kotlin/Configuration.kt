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

    lateinit var driver: WebDriver

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
        return readValue("navigatorFactory", browser, null)?.let {
            val result = it.getBase()
            when (result) {
                is NavigatorFactory -> result as NavigatorFactory
                else -> throw InvalidGebConfiguration("navigatorFactory is '$it', it should be a Closure that returns a NavigatorFactory implementation")
            }
        } ?: BrowserBackedNavigatorFactory(browser, DefaultInnerNavigatorFactory())
    }

    private fun readValue(key: String, browser: Browser, defaultValue: NavigatorFactory?): NavigatorFactory? =
            rawConfig.get(key) ?: defaultValue

}