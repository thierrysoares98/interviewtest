package interview.utils.base;

import interview.utils.VirtualElement;
import interview.utils.VirtualElementList;
import interview.utils.WebController;
import interview.utils.constants.JSAttribute;
import interview.utils.functions.*;
import interview.utils.helpers.JSHelper;
import interview.utils.exceptions.GenericException;
import interview.utils.helpers.LoggerHelper;
import org.openqa.selenium.*;
import interview.utils.utilities.CpfCnpjUtils;
import interview.utils.utilities.CronometroUtils;
import interview.utils.utilities.CryptoUtils;

import java.util.concurrent.TimeUnit;

public abstract class AbstractPageObject {

    @SuppressWarnings("rawtypes")
    private static WebDriver webDriver = null;
    protected static LoggerHelper logger = new LoggerHelper(AbstractPageObject.class);
    private CronometroUtils cronometroUtils = null;
    public Keys Key;
    public interview.utils.constants.ShouldTypes ShouldTypes;


    public WebDriver getDriver() {
       return WebController.getInstance().getDriver_();
    }

    public void waitPageLoad() {
        getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    public void waitPageLoad(int seconds) {
        getDriver().manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
    }

    public void callJMeterFromJMX(String jmeterHome, String jmxFilePath, String resultPath) {
        new JMeter().callJMeterFromJMX(jmeterHome, jmxFilePath, resultPath);
    }

    public void print(String text) {
        System.out.println(text);
    }

    public VirtualElement getElementById(String id) {
        return new VirtualElement(By.id(id));
    }

    public VirtualElement getElementByXpath(String xpath) {
        return new VirtualElement(By.xpath(xpath));
    }

    public VirtualElement getElementByName(String name) {
        return new VirtualElement(By.name(name));
    }

    public VirtualElement getElementByClassName(String className) {
        return new VirtualElement(By.className(className));
    }

    public VirtualElementList getListElementsByXpath(String xpath) {
        return new VirtualElementList(By.xpath(xpath));
    }

    public VirtualElementList getListElementsById(String id) {
        return new VirtualElementList(By.id(id));
    }

    public VirtualElementList getListElementsByClassName(String className) {
        return new VirtualElementList(By.className(className));
    }

    public VirtualElementList getListElementsByName(String name) {
        return new VirtualElementList(By.name(name));
    }

    public void callAjaxToAlert(String method, String url, String contentType) {
        new Ajax().AjaxCallToAlert(method, url, contentType);
    }

    public void maximizeWindow() {
        getDriver().manage().window().maximize();
    }


    public void setWindowSize(int width, int height) {
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

    public void setWindowPosition(int x, int y) {
        getDriver().manage().window().setPosition(new Point(x, y));
    }

    public Alert getAlert() throws GenericException {
        return new Wait().getAlert();
    }

    public void excuteJS(JSAttribute jsAttribute, String value) {
        try {
            new JSHelper().prepareJSE(jsAttribute, value, null);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void excuteJS(JSAttribute jsAttribute) {
        try {
            new JSHelper().prepareJSE(jsAttribute, "", null);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void excuteJS(String command) {
        try {
            new JSHelper().execute(command);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public Alert getAlert(int timeout) {
        Alert alert = null;
        try {
            alert = new Wait(timeout).getAlert();
        } catch (GenericException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public void waitToNumberOfWindowsEquals(int number) {
        try {
            new Wait().waitToNumberOfWindowsEquals(number);
        } catch (GenericException e) {
            e.printStackTrace();
        }
    }

    public void waitToNumberOfWindowsEquals(int number, int timeout) {
        try {
            new Wait(timeout).waitToNumberOfWindowsEquals(number);
        } catch (GenericException e) {
            e.printStackTrace();
        }
    }

    public void switchToWindowNumber(int number) {
        new SwitchTo().switchToWindowNumber(number);
    }

    public void switchToWindowWithUrlContains(String urlPart) {
        new SwitchTo().switchToWindownWithUrlContains(urlPart);
    }

    public void switchToFrameWithId(String id) {
        new SwitchTo().switchToFrameWithId(id);
    }

    public void switchToParentFrame() {
        new SwitchTo().switchToParentFrame();
    }

    public void switchToFrameByIndex(int index) {
        new SwitchTo().switchToFrameByIndex(index);
    }

    public void scrollToTop() {
        new Scroll().scrollToTop();
    }

    public String generateCPF(boolean masked) {
        return CpfCnpjUtils.generateCPF(masked);
    }

    public String generateCNPJ(boolean masked) {
        return CpfCnpjUtils.generateCNPJ(masked);
    }

    public String generatePIS() {
        return CpfCnpjUtils.generatePIS();
    }

    public String generateRenavam(boolean masked) {
        return CpfCnpjUtils.generateRenavam(masked);
    }

    public String generateValidRenavam() {
        return CpfCnpjUtils.geraNumeroRenavamValido();
    }

    public void startCronometro() {
        cronometroUtils = new CronometroUtils();
    }

    public void startCronometro(String nome) {
        cronometroUtils = new CronometroUtils(nome);
    }

    public String finishCronometro() {
        return (cronometroUtils != null) ? cronometroUtils.finish() : "Cronometro não definido!";
    }

    public String encryptBase64(String text) {
        return CryptoUtils.encryptBase64(text);
    }

    public byte[] encrypt(String text) {
        return CryptoUtils.encrypt(text.getBytes());
    }

    public String decryptBase64(String encryptedText) {
        return CryptoUtils.decryptBase64(encryptedText);
    }

    public byte[] decrypt(String encryptedText) {
        return CryptoUtils.decrypt(encryptedText.getBytes());
    }
}