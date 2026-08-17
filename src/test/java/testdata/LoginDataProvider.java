package testdata;

import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {

        return new Object[][]{

                {
                        "standard_user",
                        "wrong_password",
                        "Epic sadface: Username and password do not match any user in this service"
                },

                {
                        "wrong_user",
                        "secret_sauce",
                        "Epic sadface: Username and password do not match any user in this service"
                },

                {
                        "wrong_user",
                        "wrong_password",
                        "Epic sadface: Username and password do not match any user in this service"
                },

                {
                        "",
                        "secret_sauce",
                        "Epic sadface: Username is required"
                },

                {
                        "standard_user",
                        "",
                        "Epic sadface: Password is required"
                },

                {
                        "",
                        "",
                        "Epic sadface: Username is required"
                },

                {
                        "locked_out_user",
                        "secret_sauce",
                        "Epic sadface: Sorry, this user has been locked out."
                }

        };

    }

}