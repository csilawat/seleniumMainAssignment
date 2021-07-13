package models;

import basePage.BasePage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.*;

@Setter
@Getter
@ToString
public class MoisturizersPage extends BasePage {

    @FindBy(xpath = "//span[@data-toggle='popover']")
    private WebElement moisturizersInstructionButton;

    @FindBy(xpath = "//div[@class='popover-body']")
    private WebElement moisturizersInstruction;

    @FindBy(xpath = "//button[@onclick=\"addToCart('Vassily Aloe Attack',199)\"]")
    private WebElement leastExpensiveMoisturizersContainsAloe;

    @FindBy(xpath = "//button[@onclick=\"addToCart('Boris Almond and Honey',128)\"]")
    private WebElement leastExpensiveMoisturizersContainsAlmond;

    Logger log = Logger.getLogger(MoisturizersPage.class);
    private Map<Object, Object> addedProductInCart = new HashMap<Object, Object>();

    public MoisturizersPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(getWebDriver(), this);

    }

    public String getBuyMoisturizersInstruction() throws InterruptedException {

        log.info("---------------Get Moisturizers Instruction-------------------");

        elementWait(moisturizersInstructionButton);
        elementToBeClickable(moisturizersInstructionButton);
        Thread.sleep(3000);
        moisturizersInstructionButton.click();

        elementWait(moisturizersInstruction);
        return moisturizersInstruction.getText();

    }

    public void addProduct(String product) throws InterruptedException {

        log.info("---------------stating add product -------------------");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");

        Thread.sleep(3000);

        Map<Integer, String> productWithSameNameMap = new HashMap<Integer, String>();
        List<WebElement> webElementWithSameNameList = new ArrayList<WebElement>();
        try {
            webElementWithSameNameList = driver.findElements(By.xpath("//div/p[contains(text(),'" + product + "')]"));
        } catch (Exception exception) {
            takeScreenSHots("sunscreenPageProductNotVisible");
            System.out.println("Element " + product + "  not visible on webPage");

            exception.printStackTrace();
        }
        for (WebElement productElement : webElementWithSameNameList) {

            WebElement priceElement = driver.findElement(RelativeLocator.with(By.xpath("//p[contains(text(),'Price')]")).below(productElement));

            String price = priceElement.getText().replaceAll("[^0-9]", "");
            productWithSameNameMap.put(Integer.parseInt(price), productElement.getText());

        }

        Map<Integer, String> shortedAlmondValuesMap = new TreeMap(productWithSameNameMap);
        Object productValue = shortedAlmondValuesMap.keySet().toArray()[0];
        Object productName = shortedAlmondValuesMap.values().toArray()[0];

        addedProductInCart.put(productValue, productName);

        WebElement webElement = driver.findElement(By.xpath("//button[@onclick=\"addToCart('" + productName + "'," + productValue + ")\"]"));
        webElement.click();
        Thread.sleep(5000);

        log.info("-----added product is -" + productName + " and value is - " + productValue + "-----------");

    }

}
