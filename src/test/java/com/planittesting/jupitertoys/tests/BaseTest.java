package com.planittesting.jupitertoys.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.planittesting.jupitertoys.support.TestEnvironment;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver; //private and not static
    private static String URL = "https://jupiter.cloud.planittesting.com"; //should be in a .properties file
    private static Long TIMEOUT = Long.valueOf(10);
    protected TestEnvironment testEnvironment = new TestEnvironment();

    @BeforeClass
    public static void setup() {
        //init driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);

    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}
