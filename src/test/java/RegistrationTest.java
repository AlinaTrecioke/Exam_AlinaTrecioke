import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RegistrationTest extends BaseTest {

    @Test
    @Tag("Positive")
    void testRegistrationWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateNewAccount();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterUserName(DataGenerator.getRandomUserName());
        registrationPage.enterPassword(DataGenerator.getRandomPassword());
        registrationPage.enterConfirmPassword(DataGenerator.getStoredPassword());
        registrationPage.clickButtonCreateAccount();
        registrationPage.writeUserDataToCsv();

        //First Assertion
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        String expectedUsername = DataGenerator.getStoredUserName();
        String actualUsername = calculatorPage.getUsername().substring(8);
        Assertions.assertEquals(expectedUsername, actualUsername, "Username does not match.");

        //Second Assertion
        String expectedURL = "http://localhost:8080/skaiciuotuvas";
        String actualURL = driver.getCurrentUrl();
        Assertions.assertEquals(expectedURL, actualURL, "URL does not match.");
    }

    @ParameterizedTest
    @Tag("Negative")
    @CsvFileSource(files = "src/test/resources/loginCredentials.csv", numLinesToSkip = 1)
    void testRegistrationWithExistingUserName(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateNewAccount();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterUserName(userName);
        registrationPage.enterPassword(DataGenerator.getRandomPassword());
        registrationPage.enterConfirmPassword(DataGenerator.getStoredPassword());
        registrationPage.clickButtonCreateAccount();

        //First Assertion
        Assertions.assertTrue(registrationPage.userNameErrorMessageIsDisplayed(), "Error message is not displayed.");

        //Second Assertion
        String expectedErrorMessage = "Toks vartotojo vardas jau egzistuoja";
        String actualErrorMessage = registrationPage.getTextOfUserNameErrorMessage();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message text does not match.");
    }

    @Test
    @Tag("Negative")
    void testRegistrationWithEmptyUserName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateNewAccount();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterUserName("");
        registrationPage.enterPassword(DataGenerator.getRandomPassword());
        registrationPage.enterConfirmPassword(DataGenerator.getStoredPassword());
        registrationPage.clickButtonCreateAccount();

        //First Assertion
        Assertions.assertTrue(registrationPage.userNameErrorMessageIsDisplayed(), "Error message is not displayed.");

        //Second Assertion
        String expectedErrorMessage = "Šį laukelį būtina užpildyti\nPrivaloma įvesti nuo 3 iki 32 simbolių";
        String actualErrorMessage = registrationPage.getTextOfUserNameErrorMessage();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message text does not match.");
    }

    @Test
    @Tag("Negative")
    void testRegistrationWithNameOfLessThan3Symbols() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateNewAccount();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterUserName(RandomStringUtils.randomAlphanumeric(2));
        registrationPage.enterPassword(DataGenerator.getRandomPassword());
        registrationPage.enterConfirmPassword(DataGenerator.getStoredPassword());
        registrationPage.clickButtonCreateAccount();

        //First Assertion
        Assertions.assertTrue(registrationPage.userNameErrorMessageIsDisplayed(), "Error message is not displayed.");

        //Second Assertion
        String expectedErrorMessage = "Privaloma įvesti nuo 3 iki 32 simbolių";
        String actualErrorMessage = registrationPage.getTextOfUserNameErrorMessage();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message text does not match.");
    }

    @Test
    @Tag("Negative")
    void testRegistrationWithNameOfMoreThan32Symbols() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateNewAccount();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterUserName(RandomStringUtils.randomAlphanumeric(33));
        registrationPage.enterPassword(DataGenerator.getRandomPassword());
        registrationPage.enterConfirmPassword(DataGenerator.getStoredPassword());
        registrationPage.clickButtonCreateAccount();

        //First Assertion
        Assertions.assertTrue(registrationPage.userNameErrorMessageIsDisplayed(), "Error message is not displayed.");

        //Second Assertion
        String expectedErrorMessage = "Privaloma įvesti nuo 3 iki 32 simbolių";
        String actualErrorMessage = registrationPage.getTextOfUserNameErrorMessage();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message text does not match.");
    }


    @Test
    @Tag("Negative")
    void testRegistrationWithPasswordOf2Symbols() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateNewAccount();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterUserName(DataGenerator.getRandomUserName());
        registrationPage.enterPassword(DataGenerator.getRandomPasswordOf2Symbols());
        registrationPage.enterConfirmPassword(DataGenerator.getStoredShortPassword());
        registrationPage.clickButtonCreateAccount();

        //First Assertion
        Assertions.assertTrue(registrationPage.passwordErrorMessageIsDisplayed(), "Error message is not displayed");

        //Second Assertion
        String expectedErrorMessage = "Privaloma įvesti bent 3 simbolius";
        String actualErrorMessage = registrationPage.getTextOfPasswordErrorMessage();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message does not match");
    }


//    @Test
//    @Tag("Negative")
//    void testRegistrationWithPassworOf33Symbols() {
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.clickCreateNewAccount();
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//        registrationPage.enterUserName(DataGenerator.getRandomUserName());
//        registrationPage.enterPassword(DataGenerator.getRandomPasswordOf33Symbols());
//        registrationPage.enterConfirmPassword(DataGenerator.getStoredLongPassword());
//        registrationPage.clickButtonCreateAccount();
//
//        //First Assertion
//        Assertions.assertTrue(registrationPage.passwordErrorMessageIsDisplayed(), "Error message is not displayed");
//
//        //Second Assertion (Application bug. Error message does not match the context)
//        String expectedErrorMessage = "Privaloma įvesti nuo 3 iki 32 simbolių";
//        String actualErrorMessage = registrationPage.getTextOfPasswordErrorMessage();
//        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message does not match.");
//    }

    @Test
    @Tag("Negative")
    void testRegistrationWithDifferentPasswords() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCreateNewAccount();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.enterUserName(DataGenerator.getRandomUserName());
        registrationPage.enterPassword(DataGenerator.getRandomPassword());
        registrationPage.enterConfirmPassword(DataGenerator.getRandomPassword());
        registrationPage.clickButtonCreateAccount();

        //First Assertion
        Assertions.assertTrue(registrationPage.passwordConfirmationErrorMessageIsDisplayed(), "Error message is not displayed");

        //Second Assertion (Application bug. Error message does not match the context)
        String expectedErrorMessage = "Įvesti slaptažodžiai nesutampa";
        String actualErrorMessage = registrationPage.getTextOfPasswordConfirmationErrorMessage();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message does not match");
    }
}
