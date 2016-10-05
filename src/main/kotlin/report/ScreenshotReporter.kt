package kebab.report

import kebab.core.Browser
import kebab.support.report.ReporterSupport
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriverException
import org.webbitserver.helpers.Base64
import java.io.File
import java.nio.file.Files

/**
 * Created by yy_yank on 2016/10/05.
 */
class ScreenshotReporter : ReporterSupport() {
    override fun writeReport(reportState: ReportState) {
        // note - this is not covered by tests unless using a driver that can take screenshots
        val screenshotDriver = determineScreenshotDriver(reportState.browser)
        var decoded: ByteArray? = null
        if (screenshotDriver != null) {

            try {
                val rawBase64 = screenshotDriver.getScreenshotAs(OutputType.BASE64)
                decoded = Base64.decode(rawBase64 as String)

                // WebDriver has a bug where sometimes the screenshot has been encoded twice
                if (!PngUtils.isPng(decoded)) {
                    decoded = Base64.decode(decoded.toString())
                }
            } catch (e: WebDriverException) {
                // TODO
                decoded = ExceptionToPngConverter(e).convert("An exception has been thrown while getting the screenshot:")
            }
        }

        val file = saveScreenshotPngBytes(reportState.outputDir, reportState.label, decoded)
        notifyListeners(reportState, listOf(file))
    }


    fun saveScreenshotPngBytes(outputDir: File, label: String, bytes: ByteArray?): File {
        val file = getFile(outputDir, label, "png")
        Files.write(file.toPath(), bytes)
        return file
    }


    fun determineScreenshotDriver(browser: Browser): TakesScreenshot? {
        return if (browser.config.driver is TakesScreenshot) {
            browser.config.driver as TakesScreenshot
//        } else if (browser.config.augmentedDriver is TakesScreenshot) {
//            browser.config.augmentedDriver as TakesScreenshot
        } else {
            null
        }
    }
}



