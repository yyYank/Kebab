package kebab.junit4

import kebab.Browser
import kebab.Configuration
import kebab.configuration
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
            TimeUnit.SECONDS.sleep(10)
        })
    }

    @Ignore
    @Test
    fun Googleで検索してみたあとリンクをクリックするよ() {
        Assert.fail("未実装")
    }

    @Ignore
    @Test
    fun 何かボタンをクリックするよ() {
        Assert.fail("未実装")
    }

    @Test
    fun 画面のレンダリングをウエイトするよ() {
        Assert.fail("未実装")
    }
}
