package kebab.function

import org.openqa.selenium.By

/**
 * Created by yy_yank on 2016/10/03.
 */
class ByFunction(val f: (value: String) -> By) {
    fun invoke(value: String): By = f(value)
}