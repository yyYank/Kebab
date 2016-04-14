/**
 * Created by yy_yank on 2016/02/22.
 */
package kebab

import org.openqa.selenium.WebDriver
import java.util.concurrent.TimeUnit

class Options {

    private var timeout:Timeout? = null

    fun timeout(init: Timeout.() -> Unit) {
        val t = Timeout()
        t.init()
        this.timeout = t
    }

    fun setup(driver: WebDriver) {
        val manage = driver.manage()
        timeout?.implicitlyWait?.let {
            val (timeout, timeUnit) = it
            manage.timeouts().implicitlyWait(timeout, timeUnit)
        }
    }
}

class Timeout {
    var implicitlyWait: Pair<Long, TimeUnit>? = null
}
