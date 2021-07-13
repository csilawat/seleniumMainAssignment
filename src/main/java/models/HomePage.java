package models;

import basePage.BasePage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
@Setter
@ToString
public class HomePage extends BasePage {

    @FindBy(id = "temperature")
    private WebElement temperature;

    @FindBy(xpath = "//span[@data-toggle='popover']")
    private WebElement buyProductInstruction;

    @FindBy(xpath = "//button[contains(text(),'Buy moisturizers')]")
    private WebElement moisturizersButton;

    @FindBy(xpath = "//button[contains(text(),'Buy sunscreens')]")
    private WebElement sunscreensButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getWebDriver(), this);
    }

}
