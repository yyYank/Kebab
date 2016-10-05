package kebab.support.report

import kebab.report.ReportState
import kebab.report.Reporter
import kebab.report.ReportingListener
import java.io.File

/**
 * Created by yy_yank on 2016/10/05.
 */
abstract class ReporterSupport : Reporter {

    private val  listeners : MutableList<ReportingListener> = mutableListOf()

    /**
     * Gets a file reference for the object with the given name and extension within the dir.
     */
    fun getFile(dir : File, name : String, extension : String) : File =
        File(dir, "${escapeFileName(name)}.${escapeFileName(extension)}")



    /**
     * Replaces all non word chars with underscores to avoid using reserved characters in file paths
     */
    fun escapeFileName(name : String) {
        name.replace(Regex("(?U)[^\\w\\s-]"), "_")
    }

    override fun addListener(listener : ReportingListener) {
        if (!listeners.contains(listener)) {
            listeners + listener
        }
    }

    fun notifyListeners(reportState : ReportState, reportFiles : List<File> ) {
        for (listener in listeners) {
            listener.onReport(this, reportState, reportFiles)
        }
    }

    companion object {
        fun toTestReportLabel(testCounter : Int, reportCounter : Int, methodName : String, label : String) : String {
            val numberFormat = "%03d"
            return "${String.format(numberFormat, testCounter)}-${String.format(numberFormat, reportCounter)}-$methodName-$label"
        }
    }
}