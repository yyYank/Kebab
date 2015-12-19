package kebab

import kebab.*

/**
 * Created by yy_yank on 2015/12/19.
 */
class Page : Navigatable, PageContainer, Initializable, WatingSupport {
    var at = null
    var url = ""
    var atCheckWaiting = null
    private var browser : Browser? = null

    // @Delegate
    var pageContentSupport : PageContentSupport? = UninitializedPageContentSupport(this)
    // @Delegate
    var downloadSupport : DownloadSupport? = UninitializedDownloadSupport(this)

    var waitingSupport : WaitingSupport = UninitializedWaitingSupport(this)

    var textMatchingSupport : TextMatchingSupport = TextMatchingSupport()

    var alertAndConfirmSupport : AlertAndConfirmSupport = UninitializedAlertAndConfirmSupport(this)

    var navigableSupport : Navigable = UninitializedNavigableSupport(this)

    // @Delegate
    var  frameSupport : FrameSupport = UninitializedFrameSupport(this)
    //    @Delegate
    var interactionsSupport : InteractionsSupport = UninitializedInteractionSupport(this)

    /**
     * Initialises this page instance, connecting it to the browser.
     * <p>
     * <b>This method is called internally, and should not be called by users of Kebab.</b>
     */
    fun init (browser : Browser) : Page {
        this.browser = browser

        //        val contentTemplates = PageContentTemplateBuilder.build(
        //                browser,
        //                this as PageContentContainer,
        //                browser.navigatorFactory,
        //                "content",
        //                this.javaClass
        //        )
        //        pageContentSupport = DefaultPageContentSupport(this, contentTemplates, browser.navigatorFactory)
        //        navigableSupport = NavigableSupport(browser.navigatorFactory)
        downloadSupport = DefaultDownloadSupport(browser)
        waitingSupport = DefaultWaitingSupport(browser.config)
        frameSupport = DefaultFrameSupport(browser)
        interactionsSupport = DefaultInteractionsSupport(browser)
        alertAndConfirmSupport = DefaultAlertAndConfirmSupport({ this.getJs() }, browser.config)
        return this
    }

    fun getJs() = Any()
}