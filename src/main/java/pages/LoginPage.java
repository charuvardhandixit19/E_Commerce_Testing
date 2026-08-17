package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    // Locators
    private String usernameInput = "#user-name";
    private String passwordInput = "#password";
    private String loginButton = "#login-button";
    private String errorMessage = "[data-test='error']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String username, String password) {

        page.locator(usernameInput).fill(username);
        page.locator(passwordInput).fill(password);
        page.locator(loginButton).click();

    }

    public String getErrorMessage() {

        return page.locator(errorMessage).textContent();

    }

}