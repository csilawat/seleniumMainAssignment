package basePage;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class BasePage {

    public WebDriver driver;
    Logger logger = Logger.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public void elementWait(WebElement element) {

        Wait wait = new WebDriverWait(driver, 50);

        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception exception) {
            logger.info("Element not visible till waiting time ");
            takeScreenSHots("elementNotVisible");
            exception.printStackTrace();
        }
    }

    public void elementToBeClickable(WebElement element) {

        Wait wait = new WebDriverWait(driver, 50);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception exception) {
            logger.info("Element not Clickable till waiting time ");
            takeScreenSHots("elementNotClickable");
            exception.printStackTrace();
        }
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

}

