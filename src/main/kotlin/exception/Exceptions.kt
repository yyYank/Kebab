package kebab

/**
 * Created by yy_yank on 2015/12/23.
 */

class InvalidGebConfiguration(message: String?) : RuntimeException(message) {

}

class RequiredPageValueNotPresent(pageContentTemplate: PageContentTemplate, args: Array<Any>) : Throwable() {

}