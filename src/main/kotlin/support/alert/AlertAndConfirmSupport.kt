package kebab.support.alert

/**
 * Created by yy_yank on 2016/10/02.
 */
interface AlertAndConfirmSupport {

    // tag::alert[]
    fun withAlert(actions : () -> Unit)
    // end::alert[]

    // tag::alert[]
    fun <K,V> withAlert(params : Map<K ,V>, actions : () -> Unit)
    // end::alert[]

    // tag::alert[]
    fun withNoAlert(actions : () -> Unit) : Unit
    // end::alert[]

    // tag::confirm[]
    fun withConfirm(ok : Boolean, actions : () -> Unit)
    // end::confirm[]

    // tag::confirm[]
    fun withConfirm(actions : () -> Unit)
    // end::confirm[]

    // tag::confirm[]
    fun <K, V>withConfirm(params : Map<K, V>, actions : () -> Unit)
    // end::confirm[]

    // tag::confirm[]
    fun <K, V>withConfirm(params : Map<K, V>,  ok : Boolean, actions : () -> Unit)
    // end::confirm[]

    // tag::confirm[]
    fun withNoConfirm(actions : () -> Unit)
    // end::confirm[]
}