package js

import kebab.core.Browser
import kebab.exception.GebException
import org.openqa.selenium.JavascriptExecutor

/**
 * Created by yy_yank on 2016/12/25.
 */
class JavascriptInterface(val browser: Browser?) {

    private fun execjs(script: String, args: List<Any>?) {
        val driver = browser?.config?.driver


        if (!(driver is JavascriptExecutor)) {
            throw GebException("driver '$driver' can not execute javascript")
        }

        driver.executeScript(script, args)
    }


    fun propertyMissing(name: String) {
        execjs("return $name;", null)
    }

    fun methodMissing(name: String, args: List<String>) {
        execjs("return ${name}.apply(window, arguments)", args)
    }

    fun exec(args: Array<Any>) {
        if (args.size == 0) {
            throw IllegalArgumentException("there must be a least one argument")
        }

        val (script, jsArgs) = if (args.size == 1) {
            val jsArgs = arrayListOf<Any>()
            Pair(args[0], jsArgs)
        } else {
            val jsArgs = args.dropLast(args.size - 2)
            Pair(args.last(), jsArgs)
        }

        if (!(script is CharSequence)) {
            throw IllegalArgumentException("The last argument to the js function must be string-like")
        }

        execjs(script.toString(), jsArgs)
    }

}
