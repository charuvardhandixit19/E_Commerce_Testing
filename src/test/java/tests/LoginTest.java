package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {

        // Navigate to Application
        page.navigate(ConfigReader.getProperty("base.url"));

        // Create Page Object
        LoginPage loginPage = new LoginPage(page);

        // Perform Login
        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

        // Verify Login
        Assert.assertEquals(
                page.url(),
                "https://www.saucedemo.com/inventory.html");

        System.out.println("Login Successful");
    }
}