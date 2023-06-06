package org.saphetor.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class BaseUITest extends BaseTest {
    private WebDriver driver;
    private String url, env;

    @BeforeSuite
    @Parameters({"env"})
    public void setup(String env) {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        option.addArguments("start-maximized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);
        this.env = env;
        url = getProperties(env, "url");
        driver.navigate().to(url);
    }

    @AfterSuite
    public void afterSuite() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getUrl() {
        return url;
    }

    public String getEnv() {
        return env;
    }
}
