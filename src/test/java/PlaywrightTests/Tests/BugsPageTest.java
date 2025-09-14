package PlaywrightTests.Tests;

import PlaywrightTests.BaseTest.BaseTest;
import PlaywrightTests.Pages.BugsPage;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@UsePlaywright
public class BugsPageTest extends BaseTest {


    // todo - change approach so that each form component is tested.

    @ParameterizedTest(name = "Completing the form with valid values")
    @CsvFileSource(resources = "testData/validTestData.csv")
    public void completeFormWithValidValues(String firstName, String lastName, String phoneNumber, String country, String email, String password) {
        BugsPage bugsPage = new BugsPage(page);

        // Given I navigate to the page
        try {
            bugsPage.navigateToBugsForm();
            bugsPage.verifyPageTitleIsVisible();

            bugsPage.assertThatUrlContainsBugsPage("bugs-form");

            // When I enter expected values
            bugsPage.enterFirstName(firstName);
            bugsPage.enterLastName(lastName);
            bugsPage.enterPhoneNumber(phoneNumber);
            bugsPage.selectCountry(country);
            bugsPage.enterEmailAddress(email);
            bugsPage.enterPassword(password);

            // And check the terms and conditions and then register
            bugsPage.clickRegisterBtn();

            // Then the page displays that I have successfully registered
            bugsPage.assertSuccessMessageIsCorrectInResults();
            bugsPage.assertFirstNameIsCorrectInResults();
            bugsPage.assertLastNameIsCorrectInResults();
            bugsPage.assertPhoneNumberIsCorrectInResults();
            bugsPage.assertCountryIsCorrectInResults();
            bugsPage.assertEmailAddressIsCorrectInResults();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
