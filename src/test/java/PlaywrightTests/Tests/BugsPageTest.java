package PlaywrightTests.Tests;

import PlaywrightTests.BaseTest.BaseTest;
import PlaywrightTests.Pages.BugsPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

@UsePlaywright
public class BugsPageTest extends BaseTest {

    @Test
    public void completeFormWithValidValues() {
        BugsPage bugsPage = new BugsPage(page);

        // Given I navigate to the page
        try {
            bugsPage.navigateToBugsForm();
            bugsPage.verifyPageTitleIsVisible();

            bugsPage.assertThatUrlContainsBugsPage("bugs-form");

            // When I enter expected values
            bugsPage.enterFirstName("Sally"); // TODO use a parameterised test to get and use test data from the resources folder
            bugsPage.enterLastName("Smith");
            bugsPage.enterPhoneNumber("0123456789");
            bugsPage.selectCountry("Argentina");
            bugsPage.enterEmailAddress("sally.smith@gmail.com");
            bugsPage.enterPassword("password");

            // And check the terms and conditions and then register
            bugsPage.clickRegisterBtn();

            // Then the page displays that I have successfully registered
            bugsPage.assertSuccessMessageIsCorrectInResults();
            bugsPage.assertFirstNameIsCorrectInResults();
            bugsPage.assertLastNameIsCorrectInResults();
            bugsPage.assertPhoneNumberIsCorrectInResults();
            bugsPage.assertCountryIsCorrectInResults();
            bugsPage.assertEmailAddressIsCorrectInResults();

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("bugsPageScreenshot.png")));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
