package com.planittesting.jupitertoys.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static WebDriver driver;

    public static WebDriver getBrowserInstance() { return driver; }

    public static WebDriver launchBrowser() {
        switch (Settings.getBrowser()) {
            case "chrome" :
//                driver = new RemoteWebDriver(new URL("http://192.168.1.107:4444/wd/hub"), new ChromeOptions());
                driver = new ChromeDriver();
                break;
            case "firefox" :
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.navigate().to(Settings.getUrl());
        driver.manage().timeouts().implicitlyWait(Settings.getImplicitly_wait(), TimeUnit.SECONDS);
        return driver;
    }
}
