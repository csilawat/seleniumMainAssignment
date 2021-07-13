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
public class SunscreensPage extends BasePage {

    @FindBy(xpath = "//span[@data-toggle='popover']")
    private WebElement moisturizersInstructionButton;

    @FindBy(xpath = "//div[@class='popover-body']")
    private WebElement moisturizersInstruction;

    Logger log = Logger.getLogger(SunscreensPage.class);
    private Map<Object, Object> addedProductInCart = new HashMap<Object, Object>();


    public SunscreensPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getBuySunscreensInstruction() throws InterruptedException {

        log.info("---------------Get Sunscreens Instruction-------------------");

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

        Map<Integer, String> allAlmondProduct = new HashMap<>();
        List<WebElement> webElementWithSameNameList = new ArrayList<>();
        try {
            webElementWithSameNameList = driver.findElements(By.xpath("//div/p[contains(text(),'" + product + "')]"));
        } catch (Exception exception) {
            takeScreenSHots("sunscreenPageProductNotVisible");
            System.out.println("Element " + product + "  not visible on webPage");

            exception.printStackTrace();
        }

        for (WebElement element : webElementWithSameNameList) {

            WebElement each = driver.findElement(RelativeLocator.with(By.xpath("//p[contains(text(),'Price')]")).below(element));

            String numberOnly = each.getText().replaceAll("[^0-9]", "");
            allAlmondProduct.put(Integer.parseInt(numberOnly), element.getText());

        }

        Map<Integer, String> shortedAlmondValuesMap = new TreeMap(allAlmondProduct);
        Object productValue = shortedAlmondValuesMap.keySet().toArray()[0];
        Object productName = shortedAlmondValuesMap.values().toArray()[0];

        addedProductInCart.put(productValue, productName);

        WebElement webElement = driver.findElement(By.xpath("//button[@onclick=\"addToCart('" + productName + "'," + productValue + ")\"]"));
        webElement.click();
        Thread.sleep(5000);

        log.info("-----added product is -" + productName + " and value is - " + productValue + "-----------");

    }

}
