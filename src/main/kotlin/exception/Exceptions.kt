package kebab.exception

import kebab.core.PageContentTemplate


/**
 * Created by yy_yank on 2015/12/23.
 */

class InvalidKebabConfiguration(message: String?) : RuntimeException(message) {

}

class RequiredPageValueNotPresent(pageContentTemplate: PageContentTemplate, args: Array<Any>) : Throwable() {

}