package kebab

import org.openqa.selenium.WebDriver
import kotlin.properties.Delegates

class Browser {
    val UTF8 = "UTF-8"
    val config : Configuration by Delegates.notNull<Configuration>()
    val pageChangeListeners = java.util.LinkedHashSet<String>();
    var reportGroup : String? = null
    private var navigateFactory : NavigateFactory? = null

    // val augmentedDriver : WebDriver = RemoteDriverOperation(this.class.classloader).getAugmentedDriver(driver)


}

class ConfigurationLoader {
    val conf = ""
}

class Configuration {
}


