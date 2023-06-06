package org.saphetor.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.saphetor.Base.BasePage;

public class VarsomeLoginPage extends BasePage {

    public VarsomeLoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type= 'submit']")
    private WebElement loginButton;

    public void fillUsernameField(String text) {
        usernameField.sendKeys(text);
    }

    public void fillPasswordField(String text) {
        passwordField.sendKeys(text);
    }

    public void clickLoginButton() {
        clickElement(loginButton);
    }

}
