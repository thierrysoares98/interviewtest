package interview.utils;

import groovy.json.JsonOutput;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebController {

    private static WebController instance = null;
    private WebDriver webDriver;

    private WebController(){
        webDriver = newDriver();
    }

    public static WebController getInstance(){
        if(instance==null)
            instance = new WebController();
        return instance;
    }
    public static void quitDriver(){
        instance.webDriver.quit();
        instance = null;
    }
    public WebDriver getDriver_(){
        return webDriver;
    }

    private WebDriver newDriver() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String chromePath = prop.getProperty("chrome.path");

        System.out.println(chromePath);
        ChromeOptions options = new ChromeOptions();

        options.setBinary(new File (chromePath));
        options.addArguments("disable-gpu", "disable-extensions");
        options.addArguments("--start-maximized");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setHeadless(false);
        //options.addArguments("google-chrome-stable --disable-web-security --user-data-dir");

        return new ChromeDriver(options);
    }
}
