package js

import kebab.core.Browser
import kebab.exception.GebException
import org.openqa.selenium.JavascriptExecutor

/**
 * Created by yy_yank on 2016/12/25.
 */
class JavascriptInterface(val browser: Browser?) {

    private fun execjs(script: String, args: List<JavascriptExecutor>?) {
        val driver = browser?.config?.driver


        if (!(driver is JavascriptExecutor)) {
            throw GebException("driver '$driver' can not execute javascript")
        }

        driver.executeScript(script, args)
    }


    fun propertyMissing(name: String) {
        execjs("return $name;", null)
    }

    fun methodMissing(name: String, args: List<JavascriptExecutor>) {
        execjs("return ${name}.apply(window, arguments)", args)
    }

    fun exec(args: Pair<String, Array<JavascriptExecutor>>) {
        val (script, jsArgs) = if (args.second.size == 1) {
            val jsArgs = arrayListOf<JavascriptExecutor>()
            Pair(args.second[0], jsArgs)
        } else {
            val jsArgs = args.second.dropLast(args.second.size - 2)
            Pair(args.first, jsArgs)
        }

        if (!(script is CharSequence)) {
            throw IllegalArgumentException("The last argument to the js function must be string-like")
        }

        execjs(script.toString(), jsArgs)
    }

}
