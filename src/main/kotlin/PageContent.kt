/**
 * Created by yy_yank on 2015/12/19.
 */
package kebab

import java.util.*
import kotlin.collections.listOf


class PageContentTemplateBuilder(val browser : Browser, val container : PageContentContainer, val navigatorFactory : NavigatorFactory) {
    val templates = HashMap<String, PageContentTemplate>()
    companion object {

        fun build(browser : Browser,
                  container : PageContentContainer,
                  navigatorFactory : NavigatorFactory,
                  property : String, startAt : Class<*>,
                  stopAt : Class<*>  =  Any::class.java) : HashMap<String, PageContentTemplate> {

            if (!stopAt.isAssignableFrom(startAt)) {
                throw IllegalArgumentException("$startAt is not a subclass of $stopAt")
            }

            val templatesDefinitions = listOf("1")
            var clazz = startAt

            while (clazz != stopAt) {
                //                        var templatesDefinition =
                //noinspection GroovyUnusedCatchParameter
                //                                try {
                //                                    clazz[property]
                //                                } catch (MissingPropertyException e) {
                //                                    // swallow
                //                                }
                //
                //                        if (templatesDefinition) {
                //                            if (!(templatesDefinition is Closure)) {
                //                                throw IllegalArgumentException("'$property' static property of class $clazz should be a Closure")
                //                            }
                //                            templatesDefinitions << templatesDefinition.clone()
                //                        }
                //
                clazz = clazz.superclass
            }
            return build(browser, container, navigatorFactory, templatesDefinitions)
        }

        fun build(browser : Browser,
                  container : PageContentContainer,
                  navigatorFactory : NavigatorFactory,
                  templatesDefinitions : List<Any>)  : HashMap<String, PageContentTemplate> {
            val builder = PageContentTemplateBuilder(browser, container, navigatorFactory)
            //        for (templatesDefinition in templatesDefinitions) {
            //            templatesDefinition.delegate = builder
            //            templatesDefinition()
            //        }
            return builder.templates
        }
    }
}

class PageContentTemplate {
    // TODO 定義
    //    val browser : Browser
    //    val owner : PageContentContainer
    //    val name : String
    //    val params : String
    //    val factory : Closure
    //    val navigatorFactory : NavigatorFactory
}
