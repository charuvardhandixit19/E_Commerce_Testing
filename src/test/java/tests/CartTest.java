package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

public class CartTest extends BaseTest {


    // =====================================================
    // Reusable Login + Add Product
    // =====================================================

    private InventoryPage loginAndOpenInventory() {

        page.navigate(
                ConfigReader.getProperty("base.url")
        );

        LoginPage loginPage =
                new LoginPage(page);

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        return new InventoryPage(page);
    }


    private CartPage addProductAndOpenCart(String productId) {

        InventoryPage inventoryPage =
                loginAndOpenInventory();

        inventoryPage.addProductToCart(productId);

        inventoryPage.clickCart();

        return new CartPage(page);
    }


    // =====================================================
    // CART-001
    // Verify Cart Page Opens
    // =====================================================

    @Test
    public void verifyCartPageOpens() {

        CartPage cartPage =
                addProductAndOpenCart(
                        "sauce-labs-backpack"
                );

        Assert.assertTrue(
                cartPage.isCartPageDisplayed(),
                "Cart page was not displayed"
        );
    }


    // =====================================================
    // CART-002
    // Verify Number Of Products
    // =====================================================

    @Test
    public void verifyCartProductCount() {

        InventoryPage inventoryPage =
                loginAndOpenInventory();

        inventoryPage.addProductToCart(
                "sauce-labs-backpack"
        );

        inventoryPage.addProductToCart(
                "sauce-labs-bike-light"
        );

        inventoryPage.clickCart();

        CartPage cartPage =
                new CartPage(page);

        Assert.assertEquals(
                cartPage.getCartProductCount(),
                2,
                "Cart product count is incorrect"
        );
    }


    // =====================================================
    // CART-003
    // Verify Product Can Be Removed
    // =====================================================

    @Test
    public void verifyProductCanBeRemoved() {

        CartPage cartPage =
                addProductAndOpenCart(
                        "sauce-labs-backpack"
                );

        Assert.assertEquals(
                cartPage.getCartProductCount(),
                1
        );

        cartPage.removeProduct(
                "sauce-labs-backpack"
        );

        Assert.assertEquals(
                cartPage.getCartProductCount(),
                0,
                "Product was not removed"
        );
    }


    // =====================================================
    // CART-004
    // Verify Cart Count Decreases
    // =====================================================

    @Test
    public void verifyCartCountAfterRemove() {

        InventoryPage inventoryPage =
                loginAndOpenInventory();

        inventoryPage.addProductToCart(
                "sauce-labs-backpack"
        );

        inventoryPage.addProductToCart(
                "sauce-labs-bike-light"
        );

        inventoryPage.clickCart();

        CartPage cartPage =
                new CartPage(page);

        Assert.assertEquals(
                cartPage.getCartProductCount(),
                2
        );

        cartPage.removeProduct(
                "sauce-labs-backpack"
        );

        Assert.assertEquals(
                cartPage.getCartProductCount(),
                1,
                "Cart count did not decrease"
        );
    }


    // =====================================================
    // CART-005
    // Verify Product Details
    // =====================================================

  @Test
public void verifyProductDetails() {

    CartPage cartPage =
            addProductAndOpenCart(
                    "sauce-labs-backpack"
            );

    Assert.assertEquals(
            cartPage.getProductNameCount(),
            1,
            "Product name is not displayed"
    );

    Assert.assertEquals(
            cartPage.getProductDescriptionCount(),
            1,
            "Product description is not displayed"
    );

    Assert.assertEquals(
            cartPage.getProductPriceCount(),
            1,
            "Product price is not displayed"
    );
}

    // =====================================================
    // CART-006
    // Verify Product Image / Product Opens Details
    // =====================================================

    @Test
    public void verifyProductDetailsNavigation() {

        CartPage cartPage =
                addProductAndOpenCart(
                        "sauce-labs-backpack"
                );

        cartPage.clickProduct(
                "Sauce Labs Backpack"
        );

        Assert.assertTrue(
                page.url().contains("inventory-item"),
                "Product details page was not opened"
        );
    }


    // =====================================================
    // CART-007
    // Verify Product Name Opens Details
    // =====================================================

    @Test
    public void verifyProductNameNavigation() {

        CartPage cartPage =
                addProductAndOpenCart(
                        "sauce-labs-backpack"
                );

        cartPage.clickProduct(
                "Sauce Labs Backpack"
        );

        Assert.assertTrue(
                page.url().contains("inventory-item"),
                "Product details page was not opened"
        );
    }


    // =====================================================
    // CART-008
    // Verify Continue Shopping
    // =====================================================

    @Test
    public void verifyContinueShopping() {

        CartPage cartPage =
                addProductAndOpenCart(
                        "sauce-labs-backpack"
                );

        cartPage.clickContinueShopping();

        InventoryPage inventoryPage =
                new InventoryPage(page);

        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory page was not displayed"
        );
    }


    // =====================================================
    // CART-009
    // Verify Multiple Products
    // =====================================================

    @Test
    public void verifyMultipleProductsInCart() {

        InventoryPage inventoryPage =
                loginAndOpenInventory();

        inventoryPage.addProductToCart(
                "sauce-labs-backpack"
        );

        inventoryPage.addProductToCart(
                "sauce-labs-bike-light"
        );

        inventoryPage.addProductToCart(
                "sauce-labs-bolt-t-shirt"
        );

        inventoryPage.clickCart();

        CartPage cartPage =
                new CartPage(page);

        Assert.assertEquals(
                cartPage.getCartProductCount(),
                3,
                "Multiple products were not added correctly"
        );
    }


    // =====================================================
    // CART-010
    // Verify Removing One Product
    // =====================================================

    @Test
    public void verifyRemovingOneProductKeepsOthers() {

        InventoryPage inventoryPage =
                loginAndOpenInventory();

        inventoryPage.addProductToCart(
                "sauce-labs-backpack"
        );

        inventoryPage.addProductToCart(
                "sauce-labs-bike-light"
        );

        inventoryPage.clickCart();

        CartPage cartPage =
                new CartPage(page);

        cartPage.removeProduct(
                "sauce-labs-backpack"
        );

        Assert.assertEquals(
                cartPage.getCartProductCount(),
                1
        );

        Assert.assertTrue(
                cartPage.isProductDisplayed(
                        "Sauce Labs Bike Light"
                ),
                "Remaining product was removed incorrectly"
        );
    }


    // =====================================================
    // CART-011
    // Verify Product Price Is Displayed
    // =====================================================

    @Test
    public void verifyProductPrice() {

        CartPage cartPage =
                addProductAndOpenCart(
                        "sauce-labs-backpack"
                );

        Assert.assertEquals(
                cartPage.getProductPriceCount(),
                1,
                "Product price is not displayed"
        );
    }


    // =====================================================
    // CART-012
    // Verify Checkout Button
    // =====================================================

    @Test
    public void verifyCheckoutButton() {

        CartPage cartPage =
                addProductAndOpenCart(
                        "sauce-labs-backpack"
                );

        Assert.assertTrue(
                cartPage.isCheckoutButtonDisplayed(),
                "Checkout button is not displayed"
        );
    }


    // =====================================================
    // CART-013
    // Verify Checkout Navigation
    // =====================================================

    @Test
    public void verifyCheckoutNavigation() {

        CartPage cartPage =
                addProductAndOpenCart(
                        "sauce-labs-backpack"
                );

        cartPage.clickCheckout();

        Assert.assertTrue(
                page.url().contains("checkout-step-one"),
                "Checkout Information page was not opened"
        );
    }


    // =====================================================
    // CART-014
    // Verify Added Product Appears In Cart
    // =====================================================

    @Test
    public void verifyAddedProductAppearsInCart() {

        InventoryPage inventoryPage =
                loginAndOpenInventory();

        inventoryPage.addProductToCart(
                "sauce-labs-backpack"
        );

        inventoryPage.clickCart();

        CartPage cartPage =
                new CartPage(page);

        Assert.assertTrue(
                cartPage.isProductDisplayed(
                        "Sauce Labs Backpack"
                ),
                "Added product is not displayed in Cart"
        );
    }


    // =====================================================
    // CART-015
    // Verify Empty Cart
    // =====================================================

    @Test
    public void verifyEmptyCart() {

        InventoryPage inventoryPage =
                loginAndOpenInventory();

        inventoryPage.clickCart();

        CartPage cartPage =
                new CartPage(page);

        Assert.assertEquals(
                cartPage.getCartProductCount(),
                0,
                "Cart is not empty"
        );
    }
}