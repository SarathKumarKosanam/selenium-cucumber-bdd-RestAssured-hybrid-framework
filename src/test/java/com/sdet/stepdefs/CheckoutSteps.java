package com.sdet.stepdefs;

import com.sdet.constants.AppConstants;
import com.sdet.pages.CartPage;
import com.sdet.pages.CheckoutPage;
import com.sdet.pages.InventoryPage;
import com.sdet.utils.ExcelDataReader;
import io.cucumber.java.en.*;
import org.testng.Assert;

import java.util.Map;

public class CheckoutSteps {
    private final InventoryPage inventoryPage = new InventoryPage();
    private final CartPage cartPage           = new CartPage();
    private final CheckoutPage checkoutPage   = new CheckoutPage();

    @Given("I have added {string} to the cart")
    public void iHaveAddedItemToCart(String itemName) {
        inventoryPage.addItemToCart(itemName);
        inventoryPage.goToCart();
    }

    @Given("I proceed to checkout")
    public void iProceedToCheckout() {
        cartPage.proceedToCheckout();
    }

    @When("I fill checkout details from Excel row {int}")
    public void iFillCheckoutDetailsFromExcel(int rowIndex) {
        Map<String, String> data = ExcelDataReader
                .getSheetData(AppConstants.CHECKOUT_SHEET)
                .get(rowIndex);
        checkoutPage.fillUserInfo(
                data.get("firstName"),
                data.get("lastName"),
                data.get("zip")
        );
        checkoutPage.placeOrder();
    }

    @When("I fill checkout info with firstName {string} lastName {string} zip {string}")
    public void iFillCheckoutInfo(String firstName, String lastName, String zip) {
        checkoutPage.fillUserInfo(firstName, lastName, zip);
    }

    @Then("the order should be placed successfully")
    public void orderShouldBePlacedSuccessfully() {
        Assert.assertTrue(
                checkoutPage.getSuccessMessage().contains(AppConstants.ORDER_SUCCESS_TEXT)
        );
    }

// There should some changes in the code
    
    @Then("I should see checkout error {string}")
    public void iShouldSeeCheckoutError(String expectedError) {
        Assert.assertTrue(
                checkoutPage.getErrorMessage().contains(expectedError)
        );
    }
}
