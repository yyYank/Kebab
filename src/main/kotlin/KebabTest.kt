package kebab.junit4

import kotlin.properties.Delegates
import kebab.Browser


class KebabTest {
    val kebabConfEnv : String by Delegates.notNull()
    val kebabConfScript : String by Delegates.notNull()
    private val browser : Browser by Delegates.notNull()
}
