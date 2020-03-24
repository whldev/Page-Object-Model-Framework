package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import support.TestEnvironment;

public class BaseTest {
    public static WebDriver driver;
    private static String URL = "https://jupiter.cloud.planittesting.com";
    protected TestEnvironment testEnvironment = new TestEnvironment();

    @BeforeClass
    public static void setup() {
        //init driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(URL);

    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}
