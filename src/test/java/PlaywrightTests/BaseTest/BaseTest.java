package PlaywrightTests.BaseTest;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(false)   // set to false if you want to see the browser
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
    void takeScreenshotAndCloseContext(TestInfo testInfo) throws Exception {
        // Folder where screenshots will be saved
        Path screenshotDir = Paths.get("screenshots");
        Files.createDirectories(screenshotDir); // create folder if it doesn't exist

        // Generate a unique filename: methodName + timestamp
        String fileName = testInfo.getTestMethod()
                .orElseThrow(() -> new IllegalStateException("Test method info not available"))
                .getName()
                + "-" + System.currentTimeMillis() + ".png";
        Path screenshotPath = screenshotDir.resolve(fileName);

        // Take the screenshot
        page.screenshot(new Page.ScreenshotOptions()
                .setFullPage(true)
                .setPath(screenshotPath));

        context.close();
    }
}
