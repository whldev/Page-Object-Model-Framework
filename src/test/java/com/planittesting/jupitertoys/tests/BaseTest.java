package com.planittesting.jupitertoys.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.planittesting.jupitertoys.support.ConfigFileReader;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        //init driver
        ConfigFileReader configFileReader = new ConfigFileReader();
        String browser = configFileReader.getBrowser();
        switch (browser) {
            case "chrome" :
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
        driver.navigate().to(configFileReader.getUrl());
        driver.manage().timeouts().implicitlyWait(configFileReader.getImplicitlyWait(), TimeUnit.SECONDS);

    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}

//add property file, make it mulitibrowser
//report
