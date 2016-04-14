package kebab

import kebab.Configuration

/**
 * Created by yy_yank on 2016/04/14.
 */
interface PageContentContainer {
}
// TODO ページコンテナ実装
class DefaultPageContentContainer : PageContentContainer {
}

class ConfigurationLoader {
    val conf : Configuration = Configuration()
}

