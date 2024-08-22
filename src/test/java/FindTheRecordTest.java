import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

public class FindTheRecordTest extends BaseTest {


    @ParameterizedTest
    @Tag("Positive")
    @CsvFileSource(files = "src/test/resources/loginCredentials.csv", numLinesToSkip = 1)
    void testFindTheLastRecord(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        int numberOne = 8;
        int numberTwo = 4;
        int result = 32;
        calculatorPage.enterNumberOne(numberOne);
        calculatorPage.enterNumberTwo(numberTwo);
        calculatorPage.selectFunction();
        calculatorPage.clickCalculate();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickButtonRecords();

        //Assertion
        RecordsPage recordsPage = new RecordsPage(driver);
        List<String> records = recordsPage.getListOfRecords();
        String actualLastRecord = records.getLast();
        String expectedLastRecord = numberOne + " * " + numberTwo + " " + result + " Keisti | Trinti | Rodyti";
        Assertions.assertEquals(expectedLastRecord, actualLastRecord, "The record does not match.");
    }

    @ParameterizedTest
    @Tag("Negative")
    @CsvFileSource(files = "src/test/resources/loginCredentials.csv", numLinesToSkip = 1)
    void testFindTheLastRecordNegative(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickSignIn();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        int numberOne = 8;
        int numberTwo = 4;
        int result = 32;
        calculatorPage.enterNumberOne(numberOne);
        calculatorPage.enterNumberTwo(numberTwo);
        calculatorPage.selectFunction();
        calculatorPage.clickCalculate();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickButtonRecords();

        //Assertion
        RecordsPage recordsPage = new RecordsPage(driver);
        List<String> records = recordsPage.getListOfRecords();
        String actualLastRecord = records.getLast();
        String expectedLastRecord = numberTwo + " * " + numberTwo + " " + result + " Keisti | Trinti | Rodyti";
        Assertions.assertNotEquals(expectedLastRecord, actualLastRecord, "The record matches.");

    }
}
