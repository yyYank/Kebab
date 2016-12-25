package kebab.support.alert

import js.JavascriptInterface
import kebab.configuration.Configuration

/**
 * Created by yy_yank on 2016/10/02.
 */
class DefaultAlertAndConfirmSupport(val javascriptInterfaceFactory: () -> JavascriptInterface, val config: Configuration) : AlertAndConfirmSupport {


    private val UNKNOWN = -1


    private fun getJavascriptInterface(): JavascriptInterface {
        val js = javascriptInterfaceFactory()
        return js
    }


    override fun <K, V> withAlert(params: Map<K, V>, actions: () -> Unit) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
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


