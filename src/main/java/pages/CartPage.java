package pages;

import com.microsoft.playwright.Page;

public class CartPage {

    private Page page;

    // =========================
    // Locators
    // =========================

    private String pageTitle = "[data-test='title']";

    private String cartItems = ".cart_item";

    private String cartItemNames = ".inventory_item_name";

    private String cartItemDescriptions = ".inventory_item_desc";

    private String cartItemPrices = ".inventory_item_price";

    private String cartItemImages = ".inventory_item_img img";

    private String removeButtons = "button[id^='remove-']";

    private String continueShoppingButton =
            "[data-test='continue-shopping']";

    private String checkoutButton =
            "[data-test='checkout']";


    // =========================
    // Constructor
    // =========================

    public CartPage(Page page) {
        this.page = page;
    }


    // =========================
    // CART-001
    // Verify Cart Page
    // =========================

    public boolean isCartPageDisplayed() {

        return page.locator(pageTitle)
                .textContent()
                .equals("Your Cart");
    }


    // =========================
    // CART-002
    // Get Cart Product Count
    // =========================

    public int getCartProductCount() {

        return page.locator(cartItems).count();
    }


    // =========================
    // CART-003
    // Remove Product
    // =========================

    public void removeProduct(String productId) {

        page.locator("#remove-" + productId).click();
    }


    // =========================
    // CART-005
    // Product Names
    // =========================

    public int getProductNameCount() {

        return page.locator(cartItemNames).count();
    }


    // =========================
    // CART-005
    // Product Descriptions
    // =========================

    public int getProductDescriptionCount() {

        return page.locator(cartItemDescriptions).count();
    }


    // =========================
    // CART-005
    // Product Prices
    // =========================

    public int getProductPriceCount() {

        return page.locator(cartItemPrices).count();
    }


    // =========================
    // CART-005
    // Product Images
    // =========================

    public int getProductImageCount() {

        return page.locator(cartItemImages).count();
    }


    // =========================
    // CART-006 / CART-007
    // Open Product Details
    // =========================

    public void clickProduct(String productName) {

        page.getByText(
                productName,
                new Page.GetByTextOptions().setExact(true)
        ).click();
    }


    // =========================
    // CART-008
    // Continue Shopping
    // =========================

    public void clickContinueShopping() {

        page.locator(continueShoppingButton).click();
    }


    // =========================
    // CART-012
    // Checkout Button
    // =========================

    public boolean isCheckoutButtonDisplayed() {

        return page.locator(checkoutButton).isVisible();
    }


    // =========================
    // CART-013
    // Checkout
    // =========================

    public void clickCheckout() {

        page.locator(checkoutButton).click();
    }


    // =========================
    // Helper
    // =========================

    public boolean isProductDisplayed(String productName) {

        return page.getByText(
                productName,
                new Page.GetByTextOptions().setExact(true)
        ).isVisible();
    }
}