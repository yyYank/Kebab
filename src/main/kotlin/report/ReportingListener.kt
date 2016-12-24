package kebab.report

import java.io.File

/**
 * Created by yy_yank on 2016/10/05.
 */
interface ReportingListener {

    /**
     * Called when a report is taken.
     *
     * @param reporter The reporter instance that created the report.
     * @param reportState Information about what was reported on.
     * @param reportFiles The report files that the reporter created.
     */
    fun onReport(reporter: Reporter, reportState: ReportState, eportFiles: List<File>)
}
