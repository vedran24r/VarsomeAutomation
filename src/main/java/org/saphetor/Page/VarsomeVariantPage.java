package org.saphetor.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.saphetor.Base.BasePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VarsomeVariantPage extends BasePage {

    public VarsomeVariantPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "acmg")
    private WebElement germlineClassificationCard;

    @FindBy(id = "publications")
    private WebElement publicationsCard;

    @FindBy(xpath = "//*[@class='tw-font-semibold']")
    private WebElement cardDetails;

    @FindBy(xpath = "//span[(text())= 'Phenotypes']/parent::div/parent::td/following-sibling::td/span/span")
    private WebElement phenotypeValueField;

    @FindBy(xpath = "//a[(text())= 'PS3']/parent::div/following-sibling::div//span")
    private WebElement ps3RuleButton;

    @FindBy(xpath = "//a[(text())= 'PS3']/parent::div/following-sibling::div//input")
    private WebElement ps3RuleButtonStatus;

    @FindBy(xpath = "//div[(text())= 'ORDER BY:']/parent::div/following-sibling::div/div")
    private WebElement orderByDropdown;

    @FindBy(xpath = "//div[@class= 'tw-pt-2']/span[1]")
    private List<WebElement> unfilteredPublicationDates;

    @FindBy(xpath = "//div[contains(text(), 'Loading, please wait...')]")
    private WebElement loadingText;

    @FindBy(xpath = "//button[@title='Skip']")
    private WebElement alertDialog;

    private WebElement orderByDropdownOptions(String text) {
        return getElementByXpath("//div[(text())= '" + text + "']");
    }

    private List<WebElement> filteredPublicationDates() {
        List<WebElement> filteredPublicationDates = new ArrayList<>();
        for(WebElement webElement : unfilteredPublicationDates) {
            if(!webElement.getText().contains("Source:")) {
                filteredPublicationDates.add(webElement);
            }
        }
        return filteredPublicationDates;
    }

    public boolean verifyPublicationsOrder(String order) {
        switch (order) {
            case "citations": {

            }
            case "relevance": {

            }
            case "latest contribution": {

            }
            case "publication date": {
                List<WebElement> filteredPublicationDates = List.copyOf(filteredPublicationDates());
                List<LocalDate> dates = new ArrayList<>();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                for (WebElement element : filteredPublicationDates) {
                    LocalDate date = LocalDate.parse(element.getText(), formatter);
                    dates.add(date);
                }
                dates.sort(Comparator.reverseOrder());
                boolean isOrderedDescending = true;
                for (int i = 0; i < filteredPublicationDates.size(); i++) {
                    LocalDate originalDate = LocalDate.parse(filteredPublicationDates.get(i).getText(), formatter);
                    if (!originalDate.equals(dates.get(i))) {
                        isOrderedDescending = false;
                        break;
                    }
                }
                return isOrderedDescending;
            }
        }
        return false;
    }

    public boolean verifyCardDetailsVisible(String text) {
        scrollToElement(cardDetails);
        return cardDetails.getText().contains(text);
    }

    public boolean getPs3ButtonStatus() {
        return ps3RuleButtonStatus.isSelected();
    }

    public String getPhenotypeValueField() {
        return phenotypeValueField.getText();
    }

    public void waitForPageToLoad() {
        waitForInvisibilityOf(loadingText);
    }

    public void closeModal() {
        clickElement(alertDialog);
        waitForInvisibilityOf(alertDialog);
    }

    public void setOrderByDropdown(String text) {
        clickElement(orderByDropdown);
        clickElement(orderByDropdownOptions(text));
        freezePage();
        waitForPageToLoad();
    }

    public void clickGermlineClassificationCard() {
        clickElement(germlineClassificationCard);
    }

    public void clickPublicationsCard() {
        scrollToTop();
        clickElement(publicationsCard);
    }

    public void clickPs3RuleButton() {
        clickElement(ps3RuleButton);
    }
}
