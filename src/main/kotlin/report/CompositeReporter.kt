package kebab.report

/**
 * Created by yy_yank on 2016/10/06.
 */
class CompositeReporter(val reporters: List<Reporter>) : Reporter {
    override fun addListener(listener: ReportingListener) {
        for (reporter in reporters) {
            reporter.addListener(listener)
        }
    }

    //val pageReporter : PageSourceReporter, val screenshotReporter: ScreenshotReporter){
    override fun writeReport(reportState: ReportState) {
        for (reporter in reporters) {
            reporter.writeReport(reportState)
        }
    }
}