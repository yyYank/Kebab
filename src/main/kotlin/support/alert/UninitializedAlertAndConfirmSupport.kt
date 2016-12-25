package kebab.support.alert

import kebab.core.Page

/**
 * Created by yy_yank on 2016/10/02.
 */
class UninitializedAlertAndConfirmSupport(page: Page) : AlertAndConfirmSupport {
    override fun withAlert(actions: () -> Unit) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
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

}
