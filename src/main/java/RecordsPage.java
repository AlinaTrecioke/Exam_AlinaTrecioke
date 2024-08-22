import com.sun.source.tree.BreakTree;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class RecordsPage extends BasePage{

    @FindBy (css = "tr")
    private List<WebElement> rows;

    public RecordsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getListOfRecords(){
        List<String> records = new ArrayList<>();
        for(WebElement row : rows){
            records.add(row.getText());
        }
        return records;
    }
}
