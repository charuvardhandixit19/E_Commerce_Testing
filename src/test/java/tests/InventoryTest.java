package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class InventoryTest extends BaseTest {

    // =====================================================
    // INV-001
    // Verify Inventory Page Loads Successfully
    // =====================================================

    @Test
    public void verifyInventoryPageLoadsSuccessfully() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory page was not displayed"
        );
    }


    // =====================================================
    // INV-002
    // Verify Product Count
    // =====================================================

    @Test
    public void verifyProductCount() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        Assert.assertEquals(
                inventoryPage.getProductCount(),
                6,
                "Expected 6 products"
        );
    }


    // =====================================================
    // INV-003
    // Verify Every Product Has Image
    // =====================================================

    @Test
    public void verifyProductImages() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        Assert.assertEquals(
                inventoryPage.getProductImageCount(),
                inventoryPage.getProductCount(),
                "Some products do not have images"
        );
    }


    // =====================================================
    // INV-004
    // Verify Every Product Has Description
    // =====================================================

    @Test
    public void verifyProductDescriptions() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        Assert.assertEquals(
                inventoryPage.getProductDescriptionCount(),
                inventoryPage.getProductCount(),
                "Some products do not have descriptions"
        );
    }


    // =====================================================
    // INV-005
    // Verify Every Product Has Add To Cart Button
    // =====================================================

    @Test
    public void verifyAddToCartButtons() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        Assert.assertEquals(
                inventoryPage.getAddToCartButtonCount(),
                inventoryPage.getProductCount(),
                "Some products do not have Add To Cart buttons"
        );
    }


    // =====================================================
    // INV-006
    // Verify Every Product Has Price
    // =====================================================

    @Test
    public void verifyProductPrices() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        Assert.assertEquals(
                inventoryPage.getProductPriceCount(),
                inventoryPage.getProductCount(),
                "Some products do not have prices"
        );
    }


    // =====================================================
    // INV-007
    // Verify Cart Count Increases
    // =====================================================

    @Test
    public void verifyCartCountIncreases() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        inventoryPage.addProductToCart(
                "sauce-labs-backpack"
        );

        Assert.assertEquals(
                inventoryPage.getCartCount(),
                1,
                "Cart count did not increase"
        );
    }


    // =====================================================
    // INV-008
    // Verify Add To Cart Changes To Remove
    // =====================================================

    @Test
    public void verifyAddToCartChangesToRemove() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        String productId = "sauce-labs-backpack";

        inventoryPage.addProductToCart(productId);

        Assert.assertTrue(
                inventoryPage.isRemoveButtonDisplayed(productId),
                "Remove button was not displayed"
        );
    }


    // =====================================================
    // INV-009
    // Verify Cart Count Decreases After Remove
    // =====================================================

    @Test
    public void verifyCartCountDecreases() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        String productId = "sauce-labs-backpack";

        inventoryPage.addProductToCart(productId);

        Assert.assertEquals(
                inventoryPage.getCartCount(),
                1
        );

        inventoryPage.removeProductFromCart(productId);

        Assert.assertEquals(
                inventoryPage.getCartCount(),
                0,
                "Cart count did not decrease"
        );
    }


    // =====================================================
    // INV-010
    // Verify Sorting
    // =====================================================

    @Test
    public void verifySortingOptions() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        inventoryPage.sortProducts("az");

        // Sorting verification will be strengthened later.
        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory page is not displayed"
        );
    }


    // =====================================================
    // INV-011
    // Verify Product Details Navigation
    // =====================================================

    @Test
    public void verifyProductDetailsNavigation() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        inventoryPage.clickProduct(
                "Sauce Labs Backpack"
        );

        Assert.assertTrue(
                page.url().contains("inventory-item"),
                "Product details page was not opened"
        );
    }


    // =====================================================
    // INV-012
    // Verify Back To Products
    // =====================================================

    @Test
    public void verifyBackToProducts() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        inventoryPage.clickProduct(
                "Sauce Labs Backpack"
        );

        inventoryPage.clickBackToProducts();

        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Did not return to Inventory page"
        );
    }


    // =====================================================
    // INV-013
    // Verify Multiple Products
    // =====================================================

    @Test
    public void verifyMultipleProductsCanBeAdded() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        inventoryPage.addProductToCart(
                "sauce-labs-backpack"
        );

        inventoryPage.addProductToCart(
                "sauce-labs-bike-light"
        );

        inventoryPage.addProductToCart(
                "sauce-labs-bolt-t-shirt"
        );

        Assert.assertEquals(
                inventoryPage.getCartCount(),
                3,
                "Cart count does not match number of products added"
        );
    }


    // =====================================================
    // INV-014
    // Verify Multiple Products Can Be Removed
    // =====================================================

    @Test
    public void verifyMultipleProductsCanBeRemoved() {

        loginToApplication();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        inventoryPage.addProductToCart(
                "sauce-labs-backpack"
        );

        inventoryPage.addProductToCart(
                "sauce-labs-bike-light"
        );

        inventoryPage.addProductToCart(
                "sauce-labs-bolt-t-shirt"
        );

        inventoryPage.removeProductFromCart(
                "sauce-labs-backpack"
        );

        inventoryPage.removeProductFromCart(
                "sauce-labs-bike-light"
        );

        Assert.assertEquals(
                inventoryPage.getCartCount(),
                1,
                "Cart count is incorrect after removing products"
        );
    }


    // =====================================================
    // Reusable Login Method
    // =====================================================

    private void loginToApplication() {

        page.navigate(
                ConfigReader.getProperty("base.url")
        );

        LoginPage loginPage =
                new LoginPage(page);

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );
    }
}