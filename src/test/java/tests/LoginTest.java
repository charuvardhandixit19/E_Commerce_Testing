package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;
import testdata.LoginDataProvider;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void verifyValidLogin() {

        page.navigate(ConfigReader.getProperty("base.url"));

        LoginPage loginPage = new LoginPage(page);

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

        InventoryPage inventoryPage = new InventoryPage(page);

        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed());

    }

    @Test(
            priority = 2,
            dataProvider = "invalidLoginData",
            dataProviderClass = LoginDataProvider.class)

    public void verifyInvalidLogin(

            String username,
            String password,
            String expectedError) {

        page.navigate(ConfigReader.getProperty("base.url"));

        LoginPage loginPage = new LoginPage(page);

        loginPage.login(username, password);

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                expectedError);

    }

}