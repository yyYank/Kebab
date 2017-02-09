package kebab.support.alert

import js.JavascriptInterface
import kebab.configuration.Configuration
import org.openqa.selenium.support.ui.Wait

/**
 * Created by yy_yank on 2016/10/02.
 */
class DefaultAlertAndConfirmSupport(val javascriptInterfaceFactory: () -> JavascriptInterface, val config: Configuration) : AlertAndConfirmSupport {


    private val UNKNOWN = -1
    val installKebabStorageScript =
            """
            if (!window.kebab) {
                window.kebab = {};
            }
        """
    val installDialogStorageScript =
            """
            $installKebabStorageScript
            if (!window.kebab.dialogFunctions) {
                window.kebab.dialogFunctions = new Array();
            }
            if (!window.kebab.dialogMessages) {
                window.kebab.dialogMessages = new Array();
            }
        """


    val javascriptInterface = javascriptInterfaceFactory()

    private fun captureAlert(actions : ()-> Unit, waitParam: Array<Any>?) {
//        captureDialog(this.&installAlert, 'alert', actions, config.getWaitForParam(waitParam))
    }


    private fun installAlert(js : JavascriptInterface) {
//        js.exec ("""
//            $installDialogStorageScript
//            window.kebab.dialogFunctions.push(window.alert);
//            window.kebab.dialogMessages.push(null);
//            window.alert = function(msg) {
//                window.kebab.dialogMessages.pop();
//                window.kebab.dialogMessages.push(msg);
//                return true;
//            };
//        """)
    }

    private fun captureDialog(installer : (js : JavascriptInterface) -> Unit, function : String, actions : () -> Unit, wait : Wait<Any>?) {
        val js = javascriptInterface
        installer(js)
        var actionsError : Throwable? = null
        try {
            actions()
        } catch (e : Throwable) {
            actionsError = e
        }
//        val message = fun () : Wait<Any>?{
//            try {
//                return  wait ? wait.waitFor { popLastDialogMessage(js) } : popLastDialogMessage(js)
//            } finally {
//                // Need to do this even if actions raised exception
//                popLastDialogFunctionOnto(js, function)
//            }
//        }.invoke()
        if (actionsError != null) {
            throw actionsError
        } else {
//            return message
        }
    }
    override fun <K, V> withAlert(params: Map<K, V>, actions: () -> Unit) {
//        val message = captureAlert(actions, params.wait)
//        if (message == null) {
//            throw AssertionError("no browser alert() was raised")
//        } else if (message == UNKNOWN) {
//            true
//        } else {
//            message.toString()
//        }
    }

    override fun withNoAlert(actions: () -> Unit) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun withConfirm(ok: Boolean, actions: () -> Unit) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun withConfirm(actions: () -> Unit) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <K, V> withConfirm(params: Map<K, V>, actions: () -> Unit) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <K, V> withConfirm(params: Map<K, V>, ok: Boolean, actions: () -> Unit) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun withNoConfirm(actions: () -> Unit) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun withAlert(actions: () -> Unit) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}


