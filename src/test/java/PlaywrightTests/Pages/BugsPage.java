package PlaywrightTests.Pages;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BugsPage {

    private final Page page;

    // Locators defined once in constructor
    private final Locator bugsPageHeading;
    private final Locator firstName;
    private final Locator lastName;
    private final Locator phoneNumber;
    private final Locator country;
    private final Locator emailAddress;
    private final Locator password;
    // private final Locator termsAndConditionsChkBox;
    private final Locator registerBtn;
    private final Locator successMessage;
    private final Locator resultFn;
    private final Locator resultLn;
    private final Locator resultPhone;
    private final Locator resultCountry;
    private final Locator resultEmail;


    public BugsPage(Page page) {
        this.page = page;

        // Initialise locators
        this.bugsPageHeading = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("CHALLENGE - Spot the BUGS!")
        );
        this.firstName = page.locator("#firstName");
        this.lastName = page.locator("#lastName");
        this.phoneNumber = page.getByPlaceholder("Enter phone number");
        this.country = page.locator("#countries_dropdown_menu");
        this.emailAddress = page.getByPlaceholder("Enter email");
        this.password = page.getByPlaceholder("Password");
        // this.termsAndConditionsChkBox = page.getByPlaceholder("I agree with the terms and conditions");
        this.registerBtn = page.locator("#registerBtn");
        this.successMessage = page.locator("#message");
        this.resultFn = page.locator("#resultFn");
        this.resultLn = page.locator("#resultLn");
        this.resultPhone = page.locator("#resultPhone");
        this.resultCountry = page.locator("#country");
        this.resultEmail = page.locator("#resultEmail");
    }

    // Actions
    public void navigateToBugsForm() {
        page.navigate("https://qa-practice.netlify.app/bugs-form");
    }

    public void verifyPageTitleIsVisible() {
        assertThat(bugsPageHeading).isVisible();
    }

    public void enterFirstName(String text) {
        firstName.fill(text);
    }

    public void enterLastName(String text) {
        lastName.fill(text);
    }

    public void enterPhoneNumber(String text) {
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

    public void clickRegisterBtn() {
        registerBtn.click(); // make sure it clicks once
    }

    // Assertions
    public void assertSuccessMessageIsCorrectInResults() {
        assertThat(successMessage).hasText("Successfully registered the following information");
    }

    public void assertFirstNameIsCorrectInResults() {
        assertEquals("First Name: " + "Sally", resultFn.textContent().trim());
    }

    public void assertLastNameIsCorrectInResults() {
        assertEquals("Last Name: " + "Smit", resultLn.textContent().trim());
    }

    public void assertPhoneNumberIsCorrectInResults() {
        assertEquals("Phone Number: " + "01234567810", resultPhone.textContent().trim());
    }

    public void assertCountryIsCorrectInResults() {
        assertEquals("Country: " + "Argentina", resultCountry.textContent().trim());
    }

    public void assertEmailAddressIsCorrectInResults() {
        assertEquals("Email: " + "sally.smith@gmail.com", resultEmail.textContent().trim());
    }
}
