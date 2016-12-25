package kebab.navigator

import kebab.locator.Locator


/**
 * Created by yy_yank on 2016/10/03.
 */
/**
 * Navigableインターフェース
 */
interface Navigable : Locator {
    fun find(): Navigator
    fun find(index: Int): Navigator
    fun find(range: ClosedRange<Int>): Navigator
}