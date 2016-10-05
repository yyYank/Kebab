package kebab.report

/**
 * Created by yy_yank on 2016/10/05.
 */
interface Reporter {
    /**
     * Takes a snapshot of the given browser's state, using the given name
     * as the base name for anything (e.g. file) that is produced.
     */
    fun writeReport( reportState : ReportState)

    /**
     * Registers an object to be notified when a report is taken.
     *
     * Adding a listener that has previously been added (based on equals()) is a noop.
     *
     * @param listener
     */
    fun addListener( listener : ReportingListener)
}
