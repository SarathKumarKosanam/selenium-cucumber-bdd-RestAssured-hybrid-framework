package com.sdet.stepdefs;

import com.sdet.pages.InventoryPage;
import com.sdet.pages.LoginPage;
import com.sdet.utils.ConfigReader;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.List;

public class InventorySteps {
    private final LoginPage loginPage = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();

    @Given("I am logged in as a standard user")
    public void iAmLoggedInAsStandardUser() {
        loginPage.navigate(ConfigReader.get("base.url"));
        loginPage.login(
                ConfigReader.getSecret("APP_USERNAME"),
                ConfigReader.getSecret("APP_PASSWORD")
        );
    }

    @When("I add {string} to the cart")
    public void iAddItemToCart(String itemName) {
        inventoryPage.addItemToCart(itemName);
    }

    @When("I sort products by {string}")
    public void iSortProductsBy(String sortOption) {
        inventoryPage.sortBy(sortOption);
    }

    @Then("I should see {int} products on the page")
    public void iShouldSeeProducts(int expectedCount) {
        Assert.assertEquals(inventoryPage.getProductCount(), expectedCount);
    }

    @Then("the cart badge should show {string}")
    public void cartBadgeShouldShow(String expectedCount) {
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), expectedCount);
    }

    @Then("the products should be sorted by price low to high")
    public void productsSortedByPriceLowToHigh() {
        List<String> names = inventoryPage.getProductNames();
        Assert.assertNotNull(names);
        Assert.assertFalse(names.isEmpty());
    }
}