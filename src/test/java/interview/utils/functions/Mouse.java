package interview.utils.functions;

import interview.utils.WebController;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Mouse {

    public void moveMouseTo(WebElement webElement){
        try{
            new Actions(WebController.getInstance().getDriver_()).moveToElement(webElement).perform();
        }catch (Exception e){
            Assert.fail("Erro ao mover o mouse até o elemento.\n\n" + e.getStackTrace());
        }
    }

    public void click(WebElement webElement){
        try{
            new Wait().waitToBeClickable(webElement);
            new Scroll().scrollToElement(webElement);
            webElement.click();
        }catch (Exception e){
            Assert.fail("Erro a clicar no objeto\n\n" + e.getStackTrace());
        }
    }

    public void doubleClick(WebElement element){
        try{
            new Wait().waitToBeClickable(element);
            new Actions(WebController.getInstance().getDriver_()).doubleClick(element).perform();
        }catch (Exception e){
            Assert.fail("Erro ao dar double click no elemento\n\n" + e.getStackTrace());
        }
    }

    public void rightClick(WebElement element){
        try{
            new Wait().waitToBeClickable(element);
            new Actions(WebController.getInstance().getDriver_()).contextClick(element).perform();
        }catch (Exception e){
            Assert.fail("Erro ao clickar com o botão direito no elemento\n\n" + e.getStackTrace());
        }
    }
}
