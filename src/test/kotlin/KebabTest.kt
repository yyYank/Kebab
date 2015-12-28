package kebab.junit4

import kotlin.properties.Delegates
import kebab.Browser
import kebab.Configuration
import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL
import java.util.concurrent.TimeUnit

class KebabTest {
    val kebabConfEnv : String by Delegates.notNull()
    val kebabConfScript : String by Delegates.notNull()
    var config : Configuration by Delegates.notNull<Configuration>()
    var browser : Browser by Delegates.notNull<Browser>()

    @Before
    fun setup(){
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver")
        config = Configuration("http://www.google.co.jp/", ChromeDriver())
        config.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        browser = Browser(config)
    }

    @After
    fun teardown() {
        config.driver.quit()
    }

    @Test
    fun test() {
        browser.drive("http://www.google.co.jp/", {
            // 画面が表示されていること
            assertEquals("Google", title)
            // TODO ここらへん出来てない
            // 検索ボタンを押下
            val searchButton = find(By.cssSelector(".jsb > center:nth-child(1) > input:nth-child(1)"))
            // 検索を実行する
            searchButton.click()
        })
        browser.quit()
    }
}
