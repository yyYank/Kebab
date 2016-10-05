package kebab.configuration

import kebab.BrowserBackedNavigatorFactory
import kebab.DefaultInnerNavigatorFactory
import kebab.NavigatorFactory
import kebab.core.Browser
import kebab.exception.InvalidKebabConfiguration
import kebab.report.CompositeReporter
import kebab.report.PageSourceReporter
import kebab.report.ScreenshotReporter
import kebab.support.waiting.WaitingSupport
import org.openqa.selenium.WebDriver
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

    lateinit var options: ConfigOptions


    // TODO
    val  reporter = CompositeReporter(PageSourceReporter(), ScreenshotReporter())

    fun options(init: ConfigOptions.() -> Unit) {
        val options = ConfigOptions()
        options.init()
        this.options = options
    }

    lateinit var baseNavigatorWaiting: WaitingSupport
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
                else -> throw InvalidKebabConfiguration("navigatorFactory is '$it', it should be a Closure that returns a NavigatorFactory implementation")
            }
        } ?: BrowserBackedNavigatorFactory(browser, DefaultInnerNavigatorFactory())
    }

    private fun readValue(key: String, browser: Browser, defaultValue: NavigatorFactory?): NavigatorFactory? =
            rawConfig.get(key) ?: defaultValue



}