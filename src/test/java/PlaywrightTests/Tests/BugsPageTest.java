package PlaywrightTests.Tests;

import PlaywrightTests.Pages.BugsPage;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;


@UsePlaywright
public class BugsPageTest {


//    TODO add these to the base test
//    @AfterAll
//    static void closeBrowser() {
//        playwright.close();
//    }
//
//    @BeforeEach
//    void createContextAndPage() {
//        context = browser.newContext();
//    }
//
//    @AfterEach
//    void closeContext() {
//        context.close();
//    }


    @Test
    public void completeFormWithValidValues() {
        BugsPage bugsPage = new BugsPage();

        // Given I navigate to the page
        try {
            bugsPage.navigateToBugsPage();
            bugsPage.verifyPageTitle();

            // When I enter expected values
            bugsPage.enterFirstName("Sally"); // TODO use a parameterised test to get and use test data from the resources folder
            bugsPage.enterLastName("Smith");
            bugsPage.enterPhoneNumber("123222");
            bugsPage.selectCountry("Argentina");
            bugsPage.enterEmailAddress("sally.smith@gmail.com");
            bugsPage.enterPassword("password");

            // And check the terms and conditions and then register
            // bugsPage.clickTermsAndConditionsChkBox(); not needed to register and causing timeout - removed from test for now.
            bugsPage.clickRegisterBtn();

            // Then the page displays that I have successfully registered
            //bugsPage.assertResultsSectionSuccessTextIsVisible();
            //bugsPage.assertFirstNameIsCorrect("Sally");

            // TODO take a screenshot
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
