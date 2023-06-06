package VarsomeUITest;

import org.saphetor.Base.BaseUITest;
import org.saphetor.Page.VarsomeHomePage;
import org.saphetor.Page.VarsomeLoginPage;
import org.saphetor.Page.VarsomeVariantPage;
import org.saphetor.model.VarsomeTestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PhenotypeSearchAndSortTest extends BaseUITest {

    @Test
    public void login() {
        VarsomeHomePage varsomeHomePage = new VarsomeHomePage(getDriver());
        varsomeHomePage.clickCookiesPopupAcceptButton();
        varsomeHomePage.closeModal();
        varsomeHomePage.clickSignInButton();
        VarsomeLoginPage varsomeLoginPage = new VarsomeLoginPage(getDriver());
        varsomeLoginPage.fillUsernameField(getProperties(getEnv(), "username"));
        varsomeLoginPage.fillPasswordField(getProperties(getEnv(), "password"));
        varsomeLoginPage.clickLoginButton();
    }

    @Test(dependsOnMethods = {"login"}, dataProvider = "testData")
    public void fillSearchDataAndValidate(VarsomeTestData testData) {
        VarsomeHomePage varsomeHomePage = new VarsomeHomePage(getDriver());
        varsomeHomePage.closeModal();
        varsomeHomePage.fillSearchBar(testData.getVariant());
        varsomeHomePage.selectGenomeDropdown(testData.getGenome());
        assertTrue(varsomeHomePage.verifyReferenceGenomeDropdownSelectedValue(testData.getGenome()));
        varsomeHomePage.clickSearchButton();
        varsomeHomePage.clickOmimRadioButton();
        varsomeHomePage.fillPhenotypesField(testData.getPhenotype());
        varsomeHomePage.fillAgeAtOnsetField(testData.getAgeAtOnset());
        varsomeHomePage.clickModalSearchButton();
        assertEquals(getCurrentUrl(), getProperties(getEnv(), "url") + testData.getEndpoint());
    }

    @Test(dependsOnMethods = {"fillSearchDataAndValidate"}, dataProvider = "testData")
    public void verifyClassificationCards(VarsomeTestData testData) {
        VarsomeVariantPage varsomeVariantPage = new VarsomeVariantPage(getDriver());
        varsomeVariantPage.waitForPageToLoad();
        varsomeVariantPage.closeModal();
        varsomeVariantPage.clickGermlineClassificationCard();
        assertTrue(varsomeVariantPage.verifyCardDetailsVisible("Germline Variant Classification"));
        assertEquals(varsomeVariantPage.getPhenotypeValueField(), testData.getPhenotype());
        varsomeVariantPage.clickPs3RuleButton();
        assertFalse(varsomeVariantPage.getPs3ButtonStatus());
        varsomeVariantPage.clickPublicationsCard();
        assertTrue(varsomeVariantPage.verifyCardDetailsVisible("Publications"));
        varsomeVariantPage.setOrderByDropdown("publication date");
        varsomeVariantPage.verifyPublicationsOrder("publication date");
    }

    @DataProvider(name = "testData")
    public Object[] testData() {
        VarsomeTestData data = new VarsomeTestData(
                "BRAF:V600E",
                "hg19",
                "Long Qt Syndrome 1",
                "67",
                "variant/hg19/BRAF%3AV600E?annotation-mode=germline&age=67&patient-phenotypes=Long+Qt+Syndrome+1"

        );
        return new Object[]{data};
    }

}
