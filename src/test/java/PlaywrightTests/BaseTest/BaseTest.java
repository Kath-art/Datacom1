package PlaywrightTests.BaseTest;

import com.microsoft.playwright.*;

import org.junit.jupiter.api.*;

public class BaseTest {
    // visible to classes in the same package and to its sub-classes.
    // more accessible than private, but not as much as public
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(true)   // set to false if you want to see the browser
        );
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}
