package com.planittesting.jupitertoys.tests;

import org.openqa.selenium.Platform;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.planittesting.jupitertoys.support.ConfigFileReader;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    private String browser;
    private String url;
    private Long implicitlyWait;
    protected String username;
    protected String password;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void getConfigFile() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        this.browser = configFileReader.getBrowser();
        this.username = configFileReader.getUsername();
        this.password = configFileReader.getPassword();
        this.url = configFileReader.getUrl();
        this.implicitlyWait = configFileReader.getImplicitlyWait();
    }
    @BeforeClass
    public void setup() throws MalformedURLException {
        switch (browser) {
            case "chrome" :
//                DesiredCapabilities capability = DesiredCapabilities.chrome();
//                capability.setBrowserName("chrome");
//                capability.setPlatform(Platform.WINDOWS);
//                driver = new RemoteWebDriver(new URL("https://192.168.1.107:4444/wd/hub"), capability);
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
        driver.navigate().to(url);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);

    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}

//add property file, make it mulitibrowser
//report
