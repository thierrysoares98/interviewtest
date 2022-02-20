package interview.utils.functions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.testng.AssertJUnit.assertFalse;

public class SendKeys {

    public void sendKeys(WebElement webElement, String text){
        try{
            new Wait().waitToBeInputable(webElement);
            webElement.clear();
            webElement.sendKeys(text);
        }catch (Exception e){
            assertFalse("Não foi Possivel escrever no elemento\n\n" + e.getStackTrace(),
                    true);
        }
    }

    public void sendKeys(WebElement webElement, Keys key, String text){
        try{
            new Wait().waitToBeInputable(webElement);
            webElement.clear();
            webElement.sendKeys(text, key);
        }catch (Exception e){
            assertFalse("Não foi Possivel escrever no elemento\n\n" + e.getStackTrace(),
                    true);
        }
    }

}
