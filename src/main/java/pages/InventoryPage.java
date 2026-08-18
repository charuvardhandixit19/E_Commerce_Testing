package pages;

import com.microsoft.playwright.Page;

public class InventoryPage {

    private Page page;

    // =========================
    // Locators
    // =========================

    private String pageTitle = "[data-test='title']";

    private String productItems = ".inventory_item";
    private String productNames = ".inventory_item_name";
    private String productPrices = ".inventory_item_price";
    private String productImages = ".inventory_item_img img";
    private String productDescriptions = ".inventory_item_desc";

    private String addToCartButtons = "button[id^='add-to-cart']";
    private String removeButtons = "button[id^='remove']";

    private String cartBadge = ".shopping_cart_badge";
    private String cartButton = ".shopping_cart_link";

    private String sortDropdown =
            "[data-test='product-sort-container']";

    private String productLinks =
            ".inventory_item_name";

    private String backToProductsButton =
            "[data-test='back-to-products']";


    // =========================
    // Constructor
    // =========================

    public InventoryPage(Page page) {
        this.page = page;
    }


    // =========================
    // INV-001
    // Verify Inventory Page
    // =========================

    public boolean isInventoryPageDisplayed() {

        return page.locator(pageTitle)
                .textContent()
                .equals("Products");
    }


    // =========================
    // INV-002
    // Product Count
    // =========================

    public int getProductCount() {

        return page.locator(productItems).count();
    }


    // =========================
    // INV-003
    // Product Images
    // =========================

    public int getProductImageCount() {

        return page.locator(productImages).count();
    }


    // =========================
    // INV-004
    // Product Descriptions
    // =========================

    public int getProductDescriptionCount() {

        return page.locator(productDescriptions).count();
    }


    // =========================
    // INV-005
    // Add To Cart Buttons
    // =========================

    public int getAddToCartButtonCount() {

        return page.locator(addToCartButtons).count();
    }


    // =========================
    // INV-006
    // Product Prices
    // =========================

    public int getProductPriceCount() {

        return page.locator(productPrices).count();
    }


    // =========================
    // INV-007
    // Add Product To Cart
    // =========================

    public void addProductToCart(String productId) {

        page.locator("#add-to-cart-" + productId).click();
    }


    // =========================
    // INV-008
    // Verify Remove Button
    // =========================

    public boolean isRemoveButtonDisplayed(String productId) {

        return page.locator("#remove-" + productId)
                .isVisible();
    }


    // =========================
    // INV-009
    // Remove Product
    // =========================

    public void removeProductFromCart(String productId) {

        page.locator("#remove-" + productId).click();
    }


    // =========================
    // Cart Count
    // =========================

    public int getCartCount() {

        if (page.locator(cartBadge).count() == 0) {
            return 0;
        }

        return Integer.parseInt(
                page.locator(cartBadge).textContent()
        );
    }


    // =========================
    // Cart Navigation
    // =========================

    public void clickCart() {

        page.locator(cartButton).click();
    }


    // =========================
    // Sorting
    // =========================

    public void sortProducts(String option) {

        page.locator(sortDropdown)
                .selectOption(option);
    }


    // =========================
    // Product Details
    // =========================

    public void clickProduct(String productName) {

        page.getByText(productName, new Page.GetByTextOptions()
                .setExact(true))
                .click();
    }


    // =========================
    // Back To Products
    // =========================

    public void clickBackToProducts() {

        page.locator(backToProductsButton).click();
    }
}