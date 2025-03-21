import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class TestLogout {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Set up ChromeDriver using WebDriverManager
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // Maximize the browser window
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testLoginAndLogout() {
        try {
            // Log in to the website
            WebElement usernameField = driver.findElement(By.id("user-name"));
            usernameField.sendKeys("standard_user");

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("secret_sauce");

            WebElement loginButton = driver.findElement(By.id("login-button"));
            loginButton.click();

            // Wait for  (3000 milliseconds)
            Thread.sleep(3000);

            // Verify login by checking the URL
            String expectedUrl = "https://www.saucedemo.com/inventory.html";
            assertEquals(expectedUrl, driver.getCurrentUrl());

            // Open the menu
            WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
            menuButton.click();

            // Wait for the menu to load
            Thread.sleep(3000);

            // Click the logout link
            WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));
            logoutLink.click();

            // Wait for the login page to load
            Thread.sleep(3000);

            // Verify the logout by checking the URL or an element on the login page
            String expectedLogoutUrl = "https://www.saucedemo.com/";
            assertEquals(expectedLogoutUrl, driver.getCurrentUrl());

            System.out.println("Test Passed: Successfully logged out.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
