package PlaywrightTests.Pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BugsPage {

    // TODO Put this is the base test
    Playwright playwright = Playwright.create();
    Browser browser = playwright.chromium().launch();
    BrowserContext context = browser.newContext();
    Page page = browser.newPage();

    // Locators
    final Locator bugsPageHeading = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("CHALLENGE - Spot the BUGS!"));
    final Locator firstName = page.locator("#firstName");
    final Locator lastName = page.locator("#lastName");
    final Locator phoneNumber = page.getByPlaceholder("Enter phone number");
    final Locator country = page.locator("#countries_dropdown_menu");
    final Locator emailAddress = page.getByPlaceholder("Enter email");
    final Locator password = page.getByPlaceholder("Password");
    final Locator termsAndConditionsChkBox = page.getByPlaceholder("I agree with the terms and conditions");
    final Locator registerBtn = page.locator("#registerBtn");
    final Locator firstNameInResults = page.locator("#message");
    final Locator pageTitle = page.locator("#content > h2");

    // note to self, may have been better to use getByLabel for all, as this is best for filling in forms

    // Filling in the form
    public void navigateToBugsPage() {
        page.navigate("https://qa-practice.netlify.app/bugs-form");
    }

    public void enterFirstName(String text) {
        firstName.fill(text);
    }

    public void enterLastName(String text) {
        lastName.fill(text);
    }

    public  void enterPhoneNumber(String text) {
        phoneNumber.fill(text);
    }

    public void selectCountry(String text) {
        country.selectOption(text);
    }

    public void enterEmailAddress(String text) {
        emailAddress.fill(text);
    }

    public void enterPassword(String text) {
        password.fill(text);
    }


    // Actions
    public void verifyPageTitleIsVisible() {
        assertThat(bugsPageHeading).isVisible();
    }


    public void clickTermsAndConditionsChkBox() {
        termsAndConditionsChkBox.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        termsAndConditionsChkBox.click();
    }

    public void clickRegisterBtn() {
        registerBtn.click();  // make sure it clicks once as double-clicking removes the form and its data.
    }

    public void assertResultsSectionSuccessTextIsVisible() {
        page.getByRole(AriaRole.ALERT, new Page.GetByRoleOptions()
                .setName("Successfully registered the following information"))
                .waitFor(new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE));

        assertThat(page.getByRole(AriaRole.ALERT, new Page.GetByRoleOptions()
                .setName("Successfully registered the following information"))).isVisible();
    }

    public void assertFirstNameIsCorrect(String text) {
        assertThat(firstNameInResults).hasText(text);
    }

    public void assertLastNameIsCorrect(String text) {
        assertThat(page.locator("resultLn")).hasText("Last Name: " + text);
    }
}

