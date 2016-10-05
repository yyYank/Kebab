package kebab.report

/**
 * Created by yy_yank on 2016/10/06.
 */
class CompositeReporter(val pageReporter : PageSourceReporter, val screenshotReporter: ScreenshotReporter){
    fun  writeReport(reportState: ReportState) {
    }
}