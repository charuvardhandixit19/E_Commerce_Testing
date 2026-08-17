package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class InventoryTest extends BaseTest {

    @Test
    public void verifyInventoryPageLoadsSuccessfully() {

        page.navigate(ConfigReader.getProperty("base.url"));

        LoginPage loginPage = new LoginPage(page);

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        InventoryPage inventoryPage = new InventoryPage(page);

        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory page was not displayed"
        );
    }
}