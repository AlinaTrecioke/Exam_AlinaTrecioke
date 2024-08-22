import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "a")
    private WebElement linkCreateNewAccount;

    @FindBy(css = "input[name='username']")
    private WebElement inputUserName;

    @FindBy(css = "input[name='password']")
    private WebElement inputPassword;

    @FindBy(css = "button")
    private WebElement buttonLogin;

    @FindBy(css = "div > span:nth-of-type(2)")
    private WebElement loginErrorMessage;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateNewAccount() {
        linkCreateNewAccount.click();
    }

    public void enterUserName(String userName) {
        inputUserName.sendKeys(userName);
    }

    public void enterPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void clickSignIn() {
        buttonLogin.click();
    }

    public boolean loginErrorMessageIsDisplayed() {
        return loginErrorMessage.isDisplayed();
    }

    public String getTextOfLoginErrorMessage() {
        return loginErrorMessage.getText();
    }

    public boolean linkCreateNewAccountIsDisplayed() {
        return linkCreateNewAccount.isDisplayed();
    }

}
