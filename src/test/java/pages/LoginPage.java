package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private static By USER_NAME_TEXTBOX = By.cssSelector("#loginUserName");
    private static By PASSWORD_TEXTBOX = By.cssSelector("#loginPassword");
    private static By POPUP = By.cssSelector(".popup");
    private static By LOGIN_BUTTON = By.cssSelector(".btn-primary");
    private static By CANCEL_BUTTON = By.cssSelector(".btn-cancel");
    private static By LOGIN_PAGE_TITLE = By.cssSelector(".modal-header");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        driver.findElement(USER_NAME_TEXTBOX).sendKeys(username);
        driver.findElement(PASSWORD_TEXTBOX).sendKeys(password);
        click(LOGIN_BUTTON);
    }
}
