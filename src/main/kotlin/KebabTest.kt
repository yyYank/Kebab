package kebab.junit4

import kotlin.properties.Delegates
import kebab.Browser
import kotlin.test.assertEquals


class KebabTest {
    val kebabConfEnv : String by Delegates.notNull()
    val kebabConfScript : String by Delegates.notNull()
    private val browser = Browser()


    fun test() {
        browser.drive("http://localhost:8080/Hello", {
            // TODO これがやりたいんだがなかなか大変
            // Hello画面が表示されていること
            // assertEquals(title == 'Hello')
            // テキストボックスに「JGGUG」と入力
            // Greetボタンを押下
            // $('form').username = 'JKUG'
            // def greetButton = $('input', name:'greet')
            // greetButton.click()
        }
        )

    }
}
