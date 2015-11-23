package kebab

import org.openqa.selenium.WebDriver

class RemoteDriverOperation {
    var classLoader : ClassLoader? = null

    constructor(classLoader: ClassLoader) {
        this.classLoader = classLoader
    }

    fun getRemoteWebDriverClass() : Class<WebDriver>? = softLoadRemoteDriverClass(this.classLoader, "RemoteWebDriver")
    fun isRemoteDriverAvailable() = getRemoteWebDriverClass() != null
    /**
     * If the driver is a remote driver, a proxy will be returned that implements the feature
     * interfaces of the actual driver on the remote side. If it is not, the passed in driver
     * is returned.
     */
    fun getAugmentedDriver( driver : WebDriver) : WebDriver =
        if (isRemoteDriverAvailable()) {
            softLoadRemoteDriverClass(this.classLoader, "Augmenter")?.newInstance()!!
            //augment(driver)
        } else {
            driver
        }
    }

    fun softLoadRemoteDriverClass(classLoader: ClassLoader?, clasname : String) : Class<WebDriver>? {
        try {
            return classLoader?.loadClass("org.openqa.selenium.remote.$name") as Class<WebDriver>
        } catch (ClassNotFoundException e) {
            return null
        }
    }









}