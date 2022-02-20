package interview.utils.functions;

import org.openqa.selenium.WebElement;
import static org.testng.AssertJUnit.assertFalse;

public class Select {

    public void selectByValue(WebElement webElement, String value){
        try{
            new Scroll().scrollToElement(webElement);
            new org.openqa.selenium.support.ui.Select(webElement).selectByValue(value);
        }catch (Exception e){
            assertFalse("Falha ao selecionar pelo valor "+ value +  "\n\n" + e.getStackTrace(),true);
        }
    }

    public void selectByIndex(WebElement webElement, int index){
        try{
            new Scroll().scrollToElement(webElement);
            new org.openqa.selenium.support.ui.Select(webElement).selectByIndex(index);
        }catch (Exception e){
            assertFalse("Falha ao selecionar pelo index "+ index +  "\n\n" + e.getStackTrace(),true);
        }
    }

    public void selectByVisibleText(WebElement webElement, String visibleText){
        try{
            new Scroll().scrollToElement(webElement);
            new org.openqa.selenium.support.ui.Select(webElement).selectByVisibleText(visibleText);
        }catch (Exception e){
            assertFalse("Falha ao selecionar pelo texto visivel "+ visibleText +  "\n\n" + e.getStackTrace(),true);
        }
    }
}
