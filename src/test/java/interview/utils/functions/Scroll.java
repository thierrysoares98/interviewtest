package interview.utils.functions;

import interview.utils.WebController;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static org.testng.AssertJUnit.assertFalse;

public class Scroll {

    public Scroll(){}

    public void scrollToElement(WebElement webElement){
        try{
            ((JavascriptExecutor) WebController.getInstance().getDriver_()).executeScript("arguments[0].scrollIntoView(true);", webElement);
        }catch (Exception e){
            assertFalse("Erro ao dar scroll até o elemento.\n\n" + e.getStackTrace(), true);
        }
    }

    public void scrollToTop(){
        try{
            WebElement webElement = WebController.getInstance().getDriver_().findElement(By.tagName("header"));
            ((JavascriptExecutor) WebController.getInstance().getDriver_()).executeScript("arguments[0].scrollIntoView(true);", webElement);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
