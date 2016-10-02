package kebab.support.waiting

/**
 * Created by yy_yank on 2016/10/02.
 */
interface WaitingSupport {
    fun <T> waitFor(waitPreset: String, f: () -> T): T
    fun <T> waitFor(params: Map<String, Any>, waitPreset: String, f: () -> T): T
    fun <T> waitFor(f: () -> T): T
    fun <T> waitFor(params: Map<String, Any>, f: () -> T): T
    fun <T> waitFor(timeout: Double, f: () -> T): T
    fun <T> waitFor(params: Map<String, Any>, timeout: Double, f: () -> T): T
    fun <T> waitFor(timeout: Double, interval: Double, f: () -> T): T
    fun <T> waitFor(params: Map<String, Any>, timeout: Double, interval: Double, f: () -> T): T
}