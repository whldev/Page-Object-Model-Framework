package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.support.Browser;
import com.planittesting.jupitertoys.support.Settings;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected WebDriver driver;

    public WebDriver getDriver() { return driver; }

    @BeforeSuite(alwaysRun = true)
    public void globalSetup() {
        Settings.readSettings();
    }
    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = Browser.launchBrowser();
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {
        driver.quit();
    }
}
