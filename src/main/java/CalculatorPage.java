import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.tracing.SpanWrappedRoutable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class CalculatorPage extends BasePage {

    @FindBy(css = ".nav.navbar-nav.navbar-right > a")
    private WebElement username;

    @FindBy(css = ".navbar-brand")
    private WebElement calculator;

    @FindBy (css = "[name='sk1']")
    private WebElement inputNumberOne;

    @FindBy (css = "[name='sk2']")
    private WebElement inputNumberTwo;

    @FindBy (css = "[name='zenklas']")
    private WebElement functionSelection;

    @FindBy (css = "[type='submit']")
    private  WebElement buttonCalculate;

    @FindBy (css = "form#number > span:nth-of-type(1)")
    private WebElement errorMessageNumberOne;

    @FindBy (css = "#sk2\\.errors")
    private WebElement errorMessageNumberTwo;




    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    public String getUsername() {
        return username.getText();
    }

    public boolean calculatorIsDisplayed() {
        return calculator.isDisplayed();
    }

    public void clickLogOut() {
        username.click();
    }

    public void enterNumberOne(int number){
        inputNumberOne.clear();
        inputNumberOne.sendKeys(String.valueOf(number));
    }

    public void enterNumberTwo(int number){
        inputNumberTwo.clear();
        inputNumberTwo.sendKeys(String.valueOf(number));
    }

    public void selectFunction(){
        Select function = new Select(functionSelection);
        function.selectByVisibleText("Daugyba");
    }

    public void clickCalculate(){
        buttonCalculate.click();
    }


    public boolean errorMessageNumberOneIsDisplayed(){
        return errorMessageNumberOne.isDisplayed();
    }

    public boolean errorMessageNumberTwoIsDisplayed(){
        return errorMessageNumberTwo.isDisplayed();
    }

    public String getErrorMessageNumberOneText(){
        return errorMessageNumberOne.getText();
    }

    public String getErrorMessageNumberTwoText(){
        return errorMessageNumberTwo.getText();
    }



}
