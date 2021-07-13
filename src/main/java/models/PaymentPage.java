package models;

import basePage.BasePage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ApplicationProperties;

@Setter
@Getter
@ToString
public class PaymentPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(),'Pay with Card')]")
    private WebElement payWithCardButton;

    @FindBy(xpath = "//div/input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Card number']")
    private WebElement cardNumberField;

    @FindBy(xpath = "//input[@placeholder='MM / YY']")
    private WebElement cardMonthAndYearsField;

    @FindBy(xpath = "//input[@placeholder='CVC']")
    private WebElement cardCVCField;

    @FindBy(xpath = "//input[@placeholder='ZIP Code']")
    private WebElement zipCodeField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement payButton;


    @FindBy(xpath = "//h2[contains(text(),'PAYMENT SUCCESS')]")
    private WebElement paymentSuccess;

    @FindBy(xpath = " //p[@class='text-justify']")
    private WebElement paymentSuccessMessage;

    Logger log = Logger.getLogger(PaymentPage.class);

    private String customerEmail = ApplicationProperties.INSTANCE.getCustomerEmail();
    private String cardNo = ApplicationProperties.INSTANCE.getMasterCardNo();
    private String dateAndMonth = ApplicationProperties.INSTANCE.getDateAndMonth();
    private String cvv = ApplicationProperties.INSTANCE.getCVV();
    private String zipCode = ApplicationProperties.INSTANCE.getZipCode();

    public PaymentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void finalPayment() throws InterruptedException {

        log.info("---- Click on pay button and starting payment -------");

        elementWait(payWithCardButton);
        payWithCardButton.click();
        Thread.sleep(3000);
        driver.switchTo().frame("stripe_checkout_app");

        log.info("---- start filling payment details -------");

        emailField.click();
        emailField.sendKeys(customerEmail);
        Thread.sleep(2000);
        cardNumberField.sendKeys(cardNo);
        Thread.sleep(2000);
        cardMonthAndYearsField.sendKeys(dateAndMonth);
        Thread.sleep(2000);
        cardCVCField.sendKeys(cvv);
        Thread.sleep(2000);
        elementWait(zipCodeField);
        zipCodeField.sendKeys(zipCode);
        Thread.sleep(2000);
        elementWait(payButton);
        Thread.sleep(3000);
        elementWait(payButton);
        elementToBeClickable(payButton);
        payButton.click();
        Thread.sleep(5000);
        paymentSuccessInformation();

    }

    public void paymentSuccessInformation() {

        log.info("---- Payment success Information -------");

        elementWait(paymentSuccess);
        elementWait(paymentSuccessMessage);
        takeScreenSHots("paymentSuccess");

    }

}
