package com.sdet.stepdefs;

import com.sdet.pages.LoginPage;
import com.sdet.utils.ConfigReader;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class LoginSteps {
    private final LoginPage loginPage = new LoginPage();

    @Given("I am on the SauceDemo login page")
    public void iAmOnLoginPage() {
        loginPage.navigate(ConfigReader.get("base.url"));
    }

    @When("I login with valid credentials")
    public void iLoginWithValidCredentials() {
        // Secrets read from .env locally, GitHub Secrets in CI
        loginPage.login(
                ConfigReader.getSecret("APP_USERNAME"),
                ConfigReader.getSecret("APP_PASSWORD")
        );
    }

    @When("I login with locked out user credentials")
    public void iLoginWithLockedUser() {
        loginPage.login(
                ConfigReader.getSecret("LOCKED_USER"),
                ConfigReader.getSecret("APP_PASSWORD")
        );
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeOnInventoryPage() {
        Assert.assertTrue(
                loginPage.getCurrentUrl().contains("inventory"),
                "Expected inventory page URL"
        );
    }

    @Then("I should see error message containing {string}")
    public void iShouldSeeErrorMessage(String expectedError) {
        Assert.assertTrue(
                loginPage.getErrorMessage().contains(expectedError),
                "Error message mismatch"
        );
    }
}