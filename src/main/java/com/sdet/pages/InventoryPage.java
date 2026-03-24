package com.sdet.pages;

import com.sdet.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage {
    private final WebDriver driver;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = "[data-test='product_sort_container']")
    private WebElement sortDropdown;

    public InventoryPage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void addItemToCart(String itemName) {
        driver.findElements(By.className("inventory_item")).stream()
                .filter(item -> item.findElement(
                        By.className("inventory_item_name")).getText().equals(itemName))
                .findFirst()
                .ifPresent(item -> item.findElement(By.tagName("button")).click());
    }

    public void sortBy(String option) {
        new Select(sortDropdown).selectByValue(option);
    }

    public List<String> getProductNames() {
        return driver.findElements(By.className("inventory_item_name"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public int getProductCount() {
        return inventoryItems.size();
    }

    public String getCartBadgeCount() {
        return cartBadge.getText();
    }

    public void goToCart() {
        cartLink.click();
    }
}