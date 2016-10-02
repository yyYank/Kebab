package kebab.support.waiting

import kebab.core.Page

/**
 * Created by yy_yank on 2016/10/02.
 */

class UninitializedWaitingSupport(page: Page) : WaitingSupport {
    override fun <T> waitFor(f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(waitPreset: String, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, waitPreset: String, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(timeout: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, timeout: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(timeout: Double, interval: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }

    override fun <T> waitFor(params: Map<String, Any>, timeout: Double, interval: Double, f: () -> T): T {
        throw UnsupportedOperationException()
    }
}