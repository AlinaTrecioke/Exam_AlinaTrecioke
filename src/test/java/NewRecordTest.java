import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class NewRecordTest extends BaseTest {

    @ParameterizedTest
    @Tag("Positive")
    @CsvFileSource(files = "src/test/resources/loginCredentials.csv", numLinesToSkip = 1)
    void testCreateNewRecordWithValidNumbers(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        int numberOne = 5;
        int numberTwo = 4;
        calculatorPage.enterNumberOne(numberOne);
        calculatorPage.enterNumberTwo(numberTwo);
        calculatorPage.selectFunction();
        calculatorPage.clickCalculate();

        //Assertion
        ResultPage resultPage = new ResultPage(driver);
        int expectedResult = 20;
        String[] actualResultInParts = resultPage.getResultText().split("= ");
        int actualResult = Integer.parseInt(actualResultInParts[1]);
        Assertions.assertEquals(expectedResult, actualResult, "The actual result does not match expected result.");
    }

    @ParameterizedTest
    @Tag("Negative")
    @CsvFileSource(files = "src/test/resources/loginCredentials.csv", numLinesToSkip = 1)
    void testCreateNewRecordWithNegativeNumberOne(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        int numberOne = -5;
        int numberTwo = 4;
        calculatorPage.enterNumberOne(numberOne);
        calculatorPage.enterNumberTwo(numberTwo);
        calculatorPage.selectFunction();
        calculatorPage.clickCalculate();

        //First assertion
        Assertions.assertTrue(calculatorPage.errorMessageNumberOneIsDisplayed());

        //Second assertion
        String expectedErrorMessage = "Validacijos klaida: skaičius negali būti neigiamas";
        String actualErrorMessage = calculatorPage.getErrorMessageNumberOneText();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message text does not match.");
    }

    @ParameterizedTest
    @Tag("Negative")
    @CsvFileSource(files = "src/test/resources/loginCredentials.csv", numLinesToSkip = 1)
    void testCreateNewRecordWithNegativeNumberTwo(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        int numberOne = 5;
        int numberTwo = -4;
        calculatorPage.enterNumberOne(numberOne);
        calculatorPage.enterNumberTwo(numberTwo);
        calculatorPage.selectFunction();
        calculatorPage.clickCalculate();

        //First assertion
        Assertions.assertTrue(calculatorPage.errorMessageNumberTwoIsDisplayed());

        //Second assertion
        String expectedErrorMessage = "Validacijos klaida: skaičius negali būti neigiamas";
        String actualErrorMessage = calculatorPage.getErrorMessageNumberTwoText();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message text does not match.");
    }









}
