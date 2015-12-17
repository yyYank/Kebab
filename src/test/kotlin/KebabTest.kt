package kebab.junit4

import kotlin.properties.Delegates
import kebab.Browser
import org.junit.Test
import org.junit.runner.RunWith

class KebabTest {
    val kebabConfEnv : String by Delegates.notNull()
    val kebabConfScript : String by Delegates.notNull()
    private val browser = Browser()


    @Test
    fun test() {
        browser.drive("http://localhost:8080/jkug", {
            // TODO これがやりたいんだがなかなか大変
            // 画面が表示されていること
            // assertEquals(title == 'Hello Kotlin')
            // テキストボックスに「JGGUG」と入力
            // Greetボタンを押下
            // $('form').username = 'JKUG'
            // val sendButton = $('input', name:'send')
            // sendButton.click()
        })
    }
}
