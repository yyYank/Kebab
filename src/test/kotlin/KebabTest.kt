package kebab.junit4

import kebab.core.Browser
import kebab.configuration.Configuration
import kebab.configuration.configuration
import org.junit.*
import org.junit.Assert.assertEquals
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class KebabTest {
    val kebabConfEnv: String by Delegates.notNull()
    val kebabConfScript: String by Delegates.notNull()
    var browser: Browser by Delegates.notNull<Browser>()
    val config: Configuration by lazy {
        configuration {
            baseUrl = "http://www.google.co.jp/"

            driver = ChromeDriver()

            options {
                timeout {
                    implicitlyWait = 10L to TimeUnit.SECONDS
                }
            }
        }
    }

    init {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver")
    }


    @Before
    fun setup() {
        browser = Browser(config)
    }

    @After
    fun teardown() {
        config.driver.quit()
    }

    @Ignore
    @Test
    fun Googleで検索してみるよ() {
        browser.drive("http://www.google.co.jp/", {
            // 画面が表示されていること
            assertEquals("Google", title)
            val element = find(By.name("q"));
            element.value("Kotlin\n"); // send also a "\n"
            // とりあえずのスリープ
            TimeUnit.SECONDS.sleep(2)
        })
    }

//    @Ignore
    @Test
    fun Googleで検索してみたあとリンクをクリックするよ() {
        browser.drive("http://www.google.co.jp/", {
            // 画面が表示されていること
            assertEquals("Google", title)
            val element = find(By.name("q"));
            element.value("Kotlin\n"); // send also a "\n"

            TimeUnit.SECONDS.sleep(2)
            //Googleのロゴのエレメント
            val logo = find(By.id("logo"))
            logo.click()

            // とりあえずのスリープ
            TimeUnit.SECONDS.sleep(2)
        })
    }

    @Ignore
    @Test
    fun 何かボタンをクリックするよ() {
        Assert.fail("未実装")
    }

    @Ignore
    @Test
    fun 画面のレンダリングをウエイトするよ() {
        Assert.fail("未実装")
    }


    @Ignore
    @Test
    fun 画面のスクリーンショットをとるよ() {
        browser.drive("http://www.google.co.jp/", {
            // 画面が表示されていること
            assertEquals("Google", title)
            val element = find(By.name("q"));
            element.value("Kotlin\n"); // send also a "\n"

            TimeUnit.SECONDS.sleep(2)
            //Googleのロゴのエレメント
            val logo = find(By.id("logo"))
            logo.click()

            // とりあえずのスリープ
            TimeUnit.SECONDS.sleep(2)
            browser.report("report")
        })

    }

    @Ignore
    @Test
    fun JavaScriptを使ってみるよ() {
        Assert.fail("未実装")
    }
}
