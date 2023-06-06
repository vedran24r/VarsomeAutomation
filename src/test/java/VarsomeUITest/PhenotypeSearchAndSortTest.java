package VarsomeUITest;

import org.saphetor.Base.BaseUITest;
import org.saphetor.Page.VarsomeHomePage;
import org.saphetor.Page.VarsomeLoginPage;
import org.saphetor.Page.VarsomeVariantPage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PhenotypeSearchAndSortTest extends BaseUITest {

    @Test
    public void login() {
        VarsomeHomePage varsomeHomePage = new VarsomeHomePage(getDriver(), true);
        varsomeHomePage.closeModal();
        varsomeHomePage.clickSignInButton();
        VarsomeLoginPage varsomeLoginPage = new VarsomeLoginPage(getDriver());
        varsomeLoginPage.fillUsernameField("rvedran@live.com");
        varsomeLoginPage.fillPasswordField("23121997password");
        varsomeLoginPage.clickLoginButton();
    }

    @Test(dependsOnMethods = {"login"})
    public void fillSearchDataAndValidate() throws InterruptedException {
        Thread.sleep(5000);
        VarsomeHomePage varsomeHomePage = new VarsomeHomePage(getDriver(), false);
        varsomeHomePage.closeModal();
        varsomeHomePage.fillSearchBar("BRAF:V600E");
        varsomeHomePage.selectGenomeDropdown("hg19");
        assertTrue(varsomeHomePage.verifyReferenceGenomeDropdownSelectedValue("hg19"));
        varsomeHomePage.clickSearchButton();
        varsomeHomePage.clickOmimRadioButton();
        varsomeHomePage.fillPhenotypesField("Long Qt Syndrome 1");
        varsomeHomePage.fillAgeAtOnsetField("67");
        varsomeHomePage.clickModalSearchButton();
        // varsomeHomePage.clickProceedButton();
        Thread.sleep(1000);
        assertEquals(getUrl(), "https://varsome.com/variant/hg19/BRAF%3AV600E?annotation-mode=germline&age=67&patient-phenotypes=Long+Qt+Syndrome+1");
    }

    @Test(dependsOnMethods = {"fillSearchDataAndValidate"})
    public void verifyClassificationCards() {
        VarsomeVariantPage varsomeVariantPage = new VarsomeVariantPage(getDriver());
        varsomeVariantPage.waitForPageToLoad();
        varsomeVariantPage.closeModal();
        varsomeVariantPage.clickGermlineClassificationCard();
        assertTrue(varsomeVariantPage.verifyCardDetailsVisible("Germline Variant Classification"));
        assertEquals(varsomeVariantPage.getPhenotypeValueField(), "Long Qt Syndrome 1");
        varsomeVariantPage.clickPs3RuleButton();
        assertFalse(varsomeVariantPage.getPs3ButtonStatus());
        varsomeVariantPage.clickPublicationsCard();
        assertTrue(varsomeVariantPage.verifyCardDetailsVisible("Publications"));
        varsomeVariantPage.setOrderByDropdown("publication date");
        varsomeVariantPage.verifyPublicationsOrder("publication date");
    }
}
