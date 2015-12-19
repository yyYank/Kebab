package kebab.junit4

import kotlin.properties.Delegates
import kebab.Browser
import kebab.Configuration
import org.junit.Test
import org.junit.runner.RunWith
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class KebabTest {
    val kebabConfEnv : String by Delegates.notNull()
    val kebabConfScript : String by Delegates.notNull()


    @Test
    fun test() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver")
        val config = Configuration("http://www.google.co.jp/", ChromeDriver())
        val browser = Browser(config)
        browser.drive("http://www.google.co.jp/", {
            // TODO これがやりたいんだがなかなか大変
            // 画面が表示されていること
            // assertEquals(title == 'Hello Kotlin')
            // テキストボックスに「JGGUG」と入力
            // Greetボタンを押下
            // $('form').username = 'JKUG'
            // val sendButton = $('input', name:'send')
            // sendButton.click()
        })
        browser.quit()
    }
}
