import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileWriter;
import java.io.IOException;

public class RegistrationPage extends BasePage {

    @FindBy(css = "input#username")
    private WebElement inputUserName;

    @FindBy(css = "input#password")
    private WebElement inputPassword;

    @FindBy(css = "input#passwordConfirm")
    private WebElement inputConfirmPassword;

    @FindBy(css = "button")
    private WebElement buttonCreateAccount;

    @FindBy(css = "span#username\\.errors")
    private WebElement errorMessageUsername;

    @FindBy(css = "#password\\.errors")
    private WebElement errorMessagePassword;

    @FindBy(css = "#passwordConfirm\\.errors")
    private WebElement errorMessagePasswordConfirmation;


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void enterUserName(String userName) {
        inputUserName.sendKeys(userName);
    }

    public void enterPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String password) {
        inputConfirmPassword.sendKeys(password);
    }

    public void clickButtonCreateAccount() {
        buttonCreateAccount.click();
    }

    public void writeUserDataToCsv() {
        try (FileWriter writer = new FileWriter("src/test/resources/loginCredentials.csv")) {
            // Įrašome antraštes (pasirinktinai)
            writer.append("Username,Password\n");

            // Generuojame ir įrašome atsitiktinius vartotojų vardus ir slaptažodžius
            for (int i = 0; i < 1; i++) {
                String userName = DataGenerator.getStoredUserName();
                String password = DataGenerator.getStoredPassword();

                writer.append(userName)
                        .append(',')
                        .append(password)
                        .append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean userNameErrorMessageIsDisplayed() {
        return errorMessageUsername.isDisplayed();
    }

    public String getTextOfUserNameErrorMessage() {
        return errorMessageUsername.getText();
    }

    public boolean passwordErrorMessageIsDisplayed() {
        return errorMessagePassword.isDisplayed();
    }

    public String getTextOfPasswordErrorMessage() {
        return errorMessagePassword.getText();
    }

    public boolean passwordConfirmationErrorMessageIsDisplayed() {
        return errorMessagePasswordConfirmation.isDisplayed();
    }

    public String getTextOfPasswordConfirmationErrorMessage() {
        return errorMessagePasswordConfirmation.getText();
    }

}
