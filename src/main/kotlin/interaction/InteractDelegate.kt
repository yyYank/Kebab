package interaction

import kebab.navigator.Navigator
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

/**
 * Created by yy_yank on 2017/02/06.
 */
class InteractDelegate(driver : WebDriver){

    private val actions : Actions
    init {
        actions = Actions(driver)
    }



    /**
     * @see Actions#keyDown(org.openqa.selenium.Keys)
     */
    fun keyDown(theKey : Keys) : InteractDelegate {
        actions.keyDown(theKey)
        return this
    }

    /**
     * @see Actions#keyDown(org.openqa.selenium.WebElement, org.openqa.selenium.Keys)
     */
    fun keyDown(navigator : Navigator, theKey : Keys) :InteractDelegate {
        actions.keyDown(navigator.singleElement(), theKey)
        return this
    }

    /**
     * @see Actions#keyUp(org.openqa.selenium.Keys)
     */
    fun keyUp(theKey : Keys) : InteractDelegate {
        actions.keyUp(theKey)
        return this
    }

    /**
     * @see Actions#keyUp(org.openqa.selenium.WebElement, org.openqa.selenium.Keys)
     */
    fun keyUp(navigator : Navigator, theKey : Keys) : InteractDelegate{
        actions.keyUp(navigator.singleElement(), theKey)
        return this
    }

    /**
     * @see Actions#sendKeys(java.lang.CharSequence ...)
     */
    fun sendKeys(vararg keysToSend : CharSequence?)  : InteractDelegate {
        actions.sendKeys(*keysToSend)
        return this
    }

    /**
     * @see Actions#sendKeys(org.openqa.selenium.WebElement, java.lang.CharSequence ...)
     */
     fun sendKeys(navigator : Navigator, vararg keysToSend : CharSequence) : InteractDelegate {
        actions.sendKeys(navigator.singleElement(), *keysToSend)
        return this
    }

    /**
     * @see Actions#clickAndHold(org.openqa.selenium.WebElement)
     */
    fun clickAndHold(navigator : Navigator) : InteractDelegate {
        actions.clickAndHold(navigator.singleElement())
        return this
    }

    /**
     * @see Actions#clickAndHold()
     */
    fun clickAndHold() : InteractDelegate {
        actions.clickAndHold()
        return this
    }

    /**
     * @see Actions#release(org.openqa.selenium.WebElement)
     */
    fun release(navigator : Navigator) :InteractDelegate{
        actions.release(navigator.singleElement())
        return this
    }

    /**
     * @see Actions#release()
     */
    fun release() : InteractDelegate{
        actions.release()
        return this
    }

    /**
     * @see Actions#click(org.openqa.selenium.WebElement)
     */
    fun click(navigator : Navigator) : InteractDelegate{
        // TOOD singleElement
//        actions.click(navigator.singleElement())
        return this
    }

    /**
     * @see Actions#click()
     */
    fun click() : InteractDelegate{
        actions.click()
        return this
    }

    /**
     * @see Actions#doubleClick(org.openqa.selenium.WebElement)
     */
    fun doubleClick(navigator : Navigator) : InteractDelegate{
        actions.doubleClick(navigator.singleElement())
        return this
    }

    /**
     * @see Actions#doubleClick()
     */
    fun doubleClick() : InteractDelegate {
        actions.doubleClick()
        return this
    }

    /**
     * @see Actions#moveToElement(org.openqa.selenium.WebElement)
     */
    fun moveToElement(navigator : Navigator) : InteractDelegate{
        actions.moveToElement(navigator.singleElement())
        return this
    }

    /**
     * @see Actions#moveToElement(org.openqa.selenium.WebElement, int, int)
     */
    fun movetoelement(navigator : Navigator, xOffset : Int, yOffset : Int) :InteractDelegate {
        actions.moveToElement(navigator.singleElement(), xOffset, yOffset)
        return this
    }

    /**
     * @see Actions#moveByOffset(int, int)
     */
    fun moveByOffset(xOffset : Int, yOffset : Int) : InteractDelegate {
        actions.moveByOffset(xOffset, yOffset)
        return this
    }

    /**
     * @see Actions#contextClick(org.openqa.selenium.WebElement)
     */
    fun contextClick(navigator : Navigator) : InteractDelegate {
        actions.contextClick(navigator.singleElement())
        return this
    }

    /**
     * @see Actions#contextClick()
     */
    fun contextClick() : InteractDelegate {
        actions.contextClick()
        return this
    }

    /**
     * @see Actions#dragAndDrop(org.openqa.selenium.WebElement, org.openqa.selenium.WebElement)
     */
    fun dragAndDrop(source : Navigator, target : Navigator) : InteractDelegate {
        actions.dragAndDrop(source.singleElement(), target.singleElement())
        return this
    }

    /**
     * @see Actions#dragAndDropBy(org.openqa.selenium.WebElement, int, int)
     */
    fun dragAndDropBy(source : Navigator, xOffset: Int, yOffset : Int): InteractDelegate {
        actions.dragAndDropBy(source.singleElement(), xOffset, yOffset)
        return this
    }

    fun perform()  : Unit{
        actions.perform()
    }

}