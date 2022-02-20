package interview.utils;

import interview.utils.exceptions.AttributeNotFoundException;
import interview.utils.exceptions.VirtualElementNotFoundException;
import interview.utils.constants.ShouldTypes;
import interview.utils.exceptions.GenericException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import interview.utils.constants.JSAttribute;
import interview.utils.helpers.JSHelper;
import interview.utils.functions.*;


public class VirtualElement {

    private WebElement webElement;
    public WebElement getWebElement(){ return this.webElement; }
    private By by;
    public VirtualElement setBy(By by){ this.by = by; return this; }
    public VirtualElement(By by) { this.by = by; }
    public VirtualElement(WebElement webElement){ this.webElement=webElement; }

    //Usos da Classe Select
    public VirtualElement selectByValue(String value){
        if(elementNotNull())
            new Select().selectByValue(this.webElement, value);
        return this;
    }

    public VirtualElement selectByVisibleText(String visibleText){
        if(elementNotNull())
            new Select().selectByVisibleText(this.webElement, visibleText);
        return this;
    }

    public VirtualElement selectByIndex(int index){
        if(elementNotNull())
            new Select().selectByIndex(this.webElement, index);
        return this;
    }

    //Usos da Classe Should
    public VirtualElement should(String conditionValue, String text){
        if(elementNotNull())
            Should.should(this.webElement,conditionValue,text);
        return this;
    }

    public VirtualElement should(ShouldTypes shouldTypes, String text){
        if(elementNotNull())
            Should.should(this.webElement,shouldTypes,text);
        return this;
    }

    public VirtualElement should(String conditionValue, String text, boolean trim){
        if(elementNotNull())
            Should.should(this.webElement,conditionValue,text, trim);
        return this;
    }

    public VirtualElement should(ShouldTypes shouldTypes, String text, boolean trim){
        if(elementNotNull())
            Should.should(this.webElement,shouldTypes,text, trim);
        return this;
    }

    //Usos da Classe MoveMouse
    public VirtualElement moveMouseTo(){
        if(elementNotNull())
            new Mouse().moveMouseTo(this.webElement);
        return this;
    }

    public VirtualElement doubleClick(){
        if(elementNotNull())
            new Mouse().doubleClick(this.webElement);
        return this;
    }

    public VirtualElement rightClick(){
        if(elementNotNull())
            new Mouse().rightClick(this.webElement);
        return this;
    }

    //USOS da Classe Click
    public VirtualElement click() {
        if(elementNotNull())
            new Mouse().click(this.webElement);
        return this;
    }

    //USOS da Classe Wait
    public VirtualElement waitToBeVisible() throws GenericException {
        if(elementNotNull()) {
            new Wait().waitToBeVisible(this.webElement);
        }
        return this;
    }
    public VirtualElement waitToBeVisible(int TIMEOUT) throws GenericException {
        if(elementNotNull(TIMEOUT)) {
            new Wait(TIMEOUT).waitToBeVisible(this.webElement);
        }
        return this;
    }
    public VirtualElement waitToBeVisibleWithoutBreak(int TIMEOUT) throws GenericException {
        if(elementNotNullWithoutBreak(TIMEOUT)) {
            new Wait(TIMEOUT).waitToBeVisible(this.webElement);
        }
        return this;
    }

    public VirtualElement waitToBeDisplayed() throws GenericException {
        if(elementNotNull()) {
            new Wait().waitToBeDisplayed(webElement);
        }
        return this;
    }
    public VirtualElement waitToBeDisplayed(int TIMEOUT) throws GenericException {
        if (elementNotNull(TIMEOUT)) {
            new Wait(TIMEOUT).waitToBeDisplayed(webElement);
        }
        return this;
    }

    public VirtualElement waitToDisappear() throws GenericException {
        if(elementNotNull()) {
            new Wait().waitToDisappear(this.webElement);
        }
        return this;
    }
    public VirtualElement waitToDisappear(int TIMEOUT) throws GenericException {
        if(elementNotNull(TIMEOUT)) {
            new Wait(TIMEOUT).waitToDisappear(this.webElement);
        }
        return this;
    }

    public VirtualElement waitToDisappearWithoutBreak(int TIMEOUT){
        if(elementNotNullWithoutBreak(TIMEOUT)) {
            try {
                new Wait(TIMEOUT).waitToDisappearWithoutBreak(this.webElement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    //USOS da Classe SendKeys
    public VirtualElement sendKeys(String text) {
        if(elementNotNull())
            new SendKeys().sendKeys(webElement, text);
        return this;
    }
    public VirtualElement sendKeys(String text, Keys key){
        if(elementNotNull())
            new SendKeys().sendKeys(webElement, key, text);
        return this;
    }

    //Locate WebElement
    public VirtualElement locateWebElement(){
        if(elementNotNull())
            return this;
        if(this.webElement==null) throw new VirtualElementNotFoundException("Erro ao capturar o webElement", new Exception());
        return this;
    }
    public VirtualElement locateWebElement(int TIMEOUT){
        if(elementNotNull(TIMEOUT))
            return this;
        if(this.webElement==null) throw new VirtualElementNotFoundException("Erro ao capturar o webElement", new Exception());
        return this;
    }
    public VirtualElement locateWebElementWithoutBreak(int TIMEOUT){
        try{
            elementNotNullWithoutBreak(TIMEOUT);
        }catch (Exception ignored){}
        return this;
    }

    //Metodos padrões do WebElemento do Selenium
    public String getTagName() {
        if(elementNotNull())
            return this.webElement.getTagName();
        return null;
    }

    public String getAttribute(String attribute) {
        if(elementNotNull())
            return Attribute.get(webElement, attribute);
        return null;
    }

    public String getAttribute(String attribute, boolean trim) {
        if(elementNotNull())
            return Attribute.get(webElement, attribute, trim);
        return null;
    }

    public boolean isSelected() {
        if(elementNotNull())
            return this.webElement.isSelected();
        return false;
    }

    public boolean isEnabled() {
        if(elementNotNull())
            return this.webElement.isEnabled();
        return false;
    }

    public String getText() {
        if(elementNotNull())
            return this.webElement.getText();
        return null;
    }

    public boolean isDisplayed() {
        if(elementNotNull())
            return this.webElement.isDisplayed();
        return false;
    }

    public String getCssValue(String value) {
        if(elementNotNull()) {
            try {
                String cssValue = this.webElement.getCssValue(value);
                return cssValue != "" ? cssValue : null;
            } catch (Exception e) {
                throw new AttributeNotFoundException("Erro ao pegar cssValue do webElement", new Exception());
            }
        }
        return null;
    }

    //Metodo de Verificação se o elemento esta nulo ou se precisa ser pego ainda.
    private boolean elementNotNull() {
        if (this.webElement == null) {
            try {
                new Wait().waitToBeLocated(this.by);
                this.webElement = WebController.getInstance().getDriver_().findElement(this.by);
            } catch (Exception e) {
                throw new VirtualElementNotFoundException("Erro ao capturar o webElement", e);
            }
        }
        return true;
    }
    private boolean elementNotNull(int timeout){
            if (this.webElement == null) {
                try {
                    new Wait(timeout).waitToBeLocated(this.by);
                    this.webElement = WebController.getInstance().getDriver_().findElement(this.by);
                } catch (Exception e) {
                    throw new VirtualElementNotFoundException("Erro ao capturar o webElement", e);
                }
        }
        return true;
    }
    private boolean elementNotNullWithoutBreak(int timeout){
        if(this.webElement==null) {
            try{
                new Wait(timeout).waitToBeLocatedWithoutBreak(this.by);
                this.webElement = WebController.getInstance().getDriver_().findElement(this.by);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return true;
    }
    public boolean elementExists(int timeout){
        String webElement = this.webElement.toString();
        boolean result = false;
        try{
            @SuppressWarnings("deprecation")
            WebDriverWait wait = new WebDriverWait(WebController.getInstance().getDriver_(), timeout, 0);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            result = true;
        }catch(Exception ignored) {
            result = false;
        }
        return result;
    }

    public VirtualElement setValue(String value) {
        if (elementNotNull()){
            try {
                new JSHelper().prepareJSE(JSAttribute.SET_VALUE, value, this.webElement);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return this;
    }
    public VirtualElement excuteJStoElement(JSAttribute jsAttribute, String value) {
        if (elementNotNull()){
            try {
                new JSHelper().prepareJSE(jsAttribute, value, this.webElement);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return this;
    }

    public VirtualElement excuteJStoElement(String command) {
        if (elementNotNull()) {
            try {
                new JSHelper().execute(command, this.webElement);
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return this;

    }


    public boolean elementExists(){
        String webElement = this.webElement.toString();
        boolean result = false;
        try{
            @SuppressWarnings("deprecation")
            WebDriverWait wait = new WebDriverWait(WebController.getInstance().getDriver_(), 1, 0);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            result = true;
        }catch(Exception ignored) {
            result = false;
        }
        return result;
    }
}