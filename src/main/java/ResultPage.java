import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage extends BasePage{

    @FindBy (css = ".container > h4")
    private WebElement result;

    @FindBy (css = "[href='\\/skaiciai']")
    private WebElement buttonRecords;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public String getResultText(){
        return result.getText();
    }

    public void clickButtonRecords(){
        buttonRecords.click();
    }


}
