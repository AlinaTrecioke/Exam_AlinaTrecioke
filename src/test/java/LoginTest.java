import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class LoginTest extends BaseTest {

    @ParameterizedTest
    @Tag("Positive")
    @CsvFileSource(files = "src/test/resources/loginCredentials.csv", numLinesToSkip = 1)
    void testLoginWithValidCredentials(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();

        //First Assertion
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        String expectedUsername = userName;
        String actualUsername = calculatorPage.getUsername().substring(8);
        Assertions.assertEquals(expectedUsername, actualUsername, "Username does not match.");

        //Second Assertion
        Assertions.assertTrue(calculatorPage.calculatorIsDisplayed(), "Calculator is not displayed.");
    }

    @ParameterizedTest
    @Tag("Negative")
    @CsvFileSource(files = "src/test/resources/loginCredentials.csv", numLinesToSkip = 1)
    void testLoginWithDifferentUserName(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(DataGenerator.getRandomUserName());
        loginPage.enterPassword(password);
        loginPage.clickSignIn();

        //First Assertion
        Assertions.assertTrue(loginPage.loginErrorMessageIsDisplayed(), "Error message is not displayed.");

        //Second Assertion
        String expectedErrorMessageText = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        String actualErrorMessageText = loginPage.getTextOfLoginErrorMessage();
        System.out.println(actualErrorMessageText);
        Assertions.assertEquals(expectedErrorMessageText, actualErrorMessageText, "Error message does not match.");
    }

    @ParameterizedTest
    @Tag("Negative")
    @CsvFileSource(files = "src/test/resources/loginCredentials.csv", numLinesToSkip = 1)
    void testLoginWithDifferentPassword(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(DataGenerator.getRandomPassword());
        loginPage.clickSignIn();

        //First Assertion
        Assertions.assertTrue(loginPage.loginErrorMessageIsDisplayed(), "Error message is not displayed.");

        //Second Assertion
        String expectedErrorMessageText = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        String actualErrorMessageText = loginPage.getTextOfLoginErrorMessage();
        System.out.println(actualErrorMessageText);
        Assertions.assertEquals(expectedErrorMessageText, actualErrorMessageText, "Error message does not match.");
    }


    @ParameterizedTest
    @Tag("Positive")
    @CsvFileSource(files = "src/test/resources/loginCredentials.csv", numLinesToSkip = 1)
    void testLogOut(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        calculatorPage.clickLogOut();
        Assertions.assertTrue(loginPage.linkCreateNewAccountIsDisplayed(), "Link to create a new account is not displayed.");
    }

}
