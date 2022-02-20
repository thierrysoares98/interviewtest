package interview.utils.functions;

import interview.utils.WebController;
import interview.utils.base.AbstractPageObject;
import interview.utils.exceptions.GenericException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.AssertJUnit.assertFalse;

public class Wait {

    private WebDriverWait wait=null;
    private int SECONDS = 30;

    public Wait() throws GenericException {
        this.wait = new WebDriverWait(WebController.getInstance().getDriver_(), SECONDS);
    }

    public Wait(int SECONDS) throws GenericException {
        this.wait = new WebDriverWait(WebController.getInstance().getDriver_(), SECONDS);
        this.SECONDS = SECONDS;
    }

    public void waitToBeClickable(WebElement webElement){
        try{
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        }catch (Exception e){
            assertFalse("Tempo Excedido ao esperar o objeto ser clicavel após esperar:" + SECONDS +
                    "\n\n" + e.getStackTrace(),
                    true);
        }
    }

    public void waitToBeDisplayed(WebElement webElement){
        boolean isDisplayed = true;
        long start = System.currentTimeMillis();
        try{
            do{
                isDisplayed = !webElement.isDisplayed();
            }
            while (isDisplayed && (System.currentTimeMillis()-start)<(SECONDS*1000));
        }catch (Exception e){
            assertFalse("Erro ao aguardar o objeto ficar displayed.\n\n"+ e.getStackTrace(), true);
        }
    }

    public void waitToBeVisible(WebElement webElement){
        try{
            wait.until(ExpectedConditions.visibilityOf(webElement));
        }catch (Exception e){
            assertFalse("Tempo excedido ao esperar o elemento ficar visivel:" + SECONDS +
                    "\n\n" + e.getStackTrace(),
                    true);
        }
    }

    public void waitToBeVisibleWithoutBreak(WebElement webElement){
        try{
            this.wait.until(ExpectedConditions.visibilityOf(webElement));
        }catch (Exception ignored){}
    }

    public void waitToDisappear(WebElement webElement){
        try{
            wait.until(ExpectedConditions.invisibilityOf(webElement));
        }catch (Exception e){
            assertFalse("Tempo excedido ao esperar o elemento desaparecer:" + SECONDS +
                            "\n\n" + e.getStackTrace(),
                    true);
        }
    }

    public void waitToDisappearWithoutBreak(WebElement webElement){
        try{
            wait.until(ExpectedConditions.invisibilityOf(webElement));
        }catch (Exception ignored){}
    }

    public void waitToBeInputable(WebElement webElement){
        try{
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        }catch (Exception e){
            assertFalse("Tempo excedido ao esperar o elemento ser editavel:" + SECONDS +
                            "\n\n" + e.getStackTrace(),
                    true);
        }
    }

    public void waitToBeLocated(By by){
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception e){
            assertFalse("Tempo Excedido ao localizar o objeto:" + SECONDS + "\n\n" + e.getStackTrace(),true);
        }
    }

    public void  waitToBeLocatedWithoutBreak(By by){
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception ignored){}
    }

    public void waitToNumberOfWindowsEquals(int number){
        try{
            wait.until(ExpectedConditions.numberOfWindowsToBe(number));
        }catch (Exception e){
            assertFalse("Tempo Excedido ao esperar o numero de janelas ser " + number + ".\n\n" + e.getStackTrace(),true);
        }
    }

    public Alert getAlert() throws GenericException {
        try{
            wait.until(ExpectedConditions.alertIsPresent());
        }catch (Exception e){
            assertFalse("Tempo Excedido esperando o Alert aparecer:" + SECONDS + "\n\n" + e.getStackTrace(),true);
        }
        return WebController.getInstance().getDriver_().switchTo().alert();
    }
}
