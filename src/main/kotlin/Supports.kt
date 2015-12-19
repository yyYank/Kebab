/**
 * Created by yy_yank on 2015/12/19.
 */
package kebab

class UninitializedNavigableSupport(page: Page) : Navigable {

}

interface AlertAndConfirmSupport {

}

class UninitializedAlertAndConfirmSupport(page : Page) : AlertAndConfirmSupport {

}

class TextMatchingSupport {

}

class UninitializedWaitingSupport(page : Page) : WaitingSupport
class UninitializedDownloadSupport(page : Page) : DownloadSupport
class UninitializedPageContentSupport(page : Page) : PageContentSupport

interface WaitingSupport {

}

interface DownloadSupport {

}

interface PageContentSupport{

}

interface WatingSupport {

}

interface PageContainer {

}

interface Initializable {

}

interface Navigatable {

}


class DefaultAlertAndConfirmSupport(function: () -> Any, config: Configuration) : AlertAndConfirmSupport {

}

class DefaultInteractionsSupport(browser: Browser) : InteractionsSupport {

}

class UninitializedInteractionSupport(page: Page) : InteractionsSupport {

}

interface InteractionsSupport {

}

class DefaultFrameSupport(browser: Browser) : FrameSupport {

}

class DefaultDownloadSupport(browser: Browser) : DownloadSupport {

}

class UninitializedFrameSupport(page: Page) : FrameSupport {

}

interface FrameSupport {

}

class NavigableSupport(navigatorFactory: NavigatorFactory) : Navigable {

}

class DefaultWaitingSupport(config: Configuration) : WaitingSupport {

}

class DefaultPageContentSupport(page: Page, contentTemplates: Any, navigatorFactory: NavigatorFactory) : PageContentSupport {

}