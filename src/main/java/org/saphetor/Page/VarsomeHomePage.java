package org.saphetor.Page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.saphetor.Base.BasePage;

import java.util.List;

public class VarsomeHomePage extends BasePage {

    public VarsomeHomePage(WebDriver driver, boolean navigateToPage) {
        super(driver);
        if (navigateToPage) {
            driver.navigate().to("https://varsome.com/");
            clickCookiesPopupAcceptButton();
        }
    }

    @FindBy(id = "onetrust-banner-sdk")
    private WebElement cookiesPopup;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement cookiesPopupAcceptButton;

    @FindBy(xpath = "//div[@role='button']")
    private WebElement releaseNotesModal;

    @FindBy(xpath = "//div[@title='alternatively press the ESC key or click outside the modal window to close it']")
    private WebElement closeModalButton;

    @FindBy(xpath = "//label[contains(text(),'Do not show this again')]")
    private WebElement doNotShowAgainModalButton;

    @FindBy(id = "search")
    private WebElement searchBar;

    @FindBy(id = "varsome-search-btn")
    private WebElement searchButton;

    @FindBy(xpath = "//button[(text())='Search']")
    private WebElement modalSearchButton;

    @FindBy(name = "genome")
    private WebElement genomeSearchDropdown; // element not interactable

    @FindBy(xpath = "//div[@class='select-selected']")
    private WebElement genomeSearchDropdownSelectedValue;

    @FindBy(xpath = "//div[(text())='Germline']")
    private WebElement germlineVariantButton;

    @FindBy(xpath = "//div[(text())='Germline']")
    private WebElement somaticVariantButton;

    @FindBy(xpath = "//label[@for= 'all_radioButton']")
    private WebElement allRadioButton;

    @FindBy(xpath = "//label[@for= 'omim_radioButton']")
    private WebElement omimRadioButton;

    @FindBy(xpath = "//div[@id='germline-modal-phenotypes']//input[@autocapitalize='none']")
    private WebElement phenotypesField;

    @FindBy(xpath = "//div[contains(@id, 'react-select')]")
    private List<WebElement> phenotypesDropdownValues;

    @FindBy(xpath = "//div[@id= 'germline-modal-onset-age']/input")
    private WebElement ageAtOnsetField;

    @FindBy(id = "proceedBtn")
    private WebElement proceedButton;

    @FindBy(id = "menu-login-link")
    private WebElement signInButton;

    private WebElement genomeSearchDropdownValue(String text) {
        return getElementByXpath("//div[(text())= '" + text + "']");
    }

    public boolean verifyReferenceGenomeDropdownSelectedValue(String text) {
        return genomeSearchDropdownSelectedValue.getText().equals(text);
    }

    public void closeModal() {
        clickElement(doNotShowAgainModalButton);
        sendKeysWindow(Keys.ESCAPE);
        waitForInvisibilityOf(releaseNotesModal);
    }

    public void clickSignInButton() {
        clickElement(signInButton);
    }

    public void clickProceedButton() {
        clickElement(proceedButton);
    }

    public void clickCookiesPopupAcceptButton() {
        clickElement(cookiesPopupAcceptButton);
    }

    public void clickSearchButton() {
        clickElement(searchButton);
    }

    public void clickModalSearchButton() {
        clickElement(modalSearchButton);
    }

    public void clickAllRadioButton() {
        clickElement(allRadioButton);
    }

    public void clickOmimRadioButton() {
        clickElement(omimRadioButton);
    }

    public void fillSearchBar(String text) {
        searchBar.sendKeys(text);
    }

    public void fillPhenotypesField(String text) {
        phenotypesField.sendKeys(text);
        clickElement(phenotypesDropdownValues.get(0));
    }

    public void fillAgeAtOnsetField(String text) {
        ageAtOnsetField.sendKeys(text);
    }

    public void selectGenomeDropdown(String value) {
        clickElement(genomeSearchDropdownSelectedValue);
        clickElement(genomeSearchDropdownValue(value));
    }
}
