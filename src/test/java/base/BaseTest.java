package base;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import utils.ApplicationProperties;

import java.io.File;
import java.io.IOException;


public class BaseTest {

    protected WebDriver driver;
    private String baseUrl = ApplicationProperties.INSTANCE.getBaseUrl();
    Logger logger = Logger.getLogger(BaseTest.class);


    @BeforeMethod
    public void beforeTest() {
        logger.info("*********************************************************************************");
        logger.info("...........................Booking order Started ..............................");
        logger.info("*********************************************************************************");

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        System.out.println("before test");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

    }

    @AfterMethod
    public void afterTest() {
        logger.info("*********************************************************************************");
        logger.info("...........................Booking order Completed ..............................");
        logger.info("...........................Thank you please visit again ..............................");
        logger.info("*********************************************************************************");

        driver.quit();
    }

    public void takeScreenSHots(String screenShotFileName) {

        logger.info("----------Take Screenshot of Page-------------------");

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("screenshots/" + screenShotFileName + ".png"));
        } catch (IOException exception) {
            logger.info("...............Not able to take Screenshot...........");
            exception.printStackTrace();
        }
    }

    public void waitForPageLoad() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String result = js.executeScript("return document.readyState").toString();
        if (!result.equals("complete")) {
            Thread.sleep(10000);
        }
    }
}
