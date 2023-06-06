package org.saphetor.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.List;

public class BasePage {
    WebDriver driver;
    Actions action;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 2), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        action = new Actions(driver);
    }

    @BeforeSuite
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    protected WebElement getElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    protected List<WebElement> getElementsByXpath(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }

    protected static void repeat(int amount, Runnable action) {
        for (int i = 0; i < amount; i++) {
            action.run();
        }
    }

    protected Runnable clickElementRunnable(WebElement webElement) {
        return () -> {
            waitForVisibilityOf(webElement);
            scrollToElement(webElement);
            webElement.click();
        };
    }

    protected void clickElement(WebElement webElement) {
        clickElementRunnable(webElement).run();
    }

    protected void selectDropdownValue(String value, WebElement webElement) {
        waitForVisibilityOf(webElement);
        scrollToElement(webElement);
        Select select = new Select(webElement);
        select.selectByVisibleText(value);
    }

    protected void scrollToTop() {
        sendKeysWindow(Keys.HOME);
    }

    protected void scrollToElement(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", webElement);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // for debugging purposes
    protected void freezePage() {
        ((JavascriptExecutor) driver).executeScript("window.stop();");
    }

    protected void sendKeysWindow(Keys keysToSend) {
        action.sendKeys(keysToSend).perform();
    }

    protected void waitForVisibilityOf(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForPresenceOf(String xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    protected void waitForVisibilityOf(List<WebElement> webElements) {
        wait.until(webDriver -> !webElements.isEmpty());
    }

    protected void waitForInvisibilityOf(WebElement webElement) {
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }
}
