Feature: Inventory functionality
  As a logged-in user
  I want to interact with the product inventory
  So that I can manage my shopping

  Background:
    Given I am logged in as a standard user

  Scenario: Inventory page displays all products
    Then I should see 6 products on the page

  Scenario: Add a single item to cart
    When I add "Sauce Labs Backpack" to the cart
    Then the cart badge should show "1"

  Scenario: Add multiple items to cart
    When I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bike Light" to the cart
    Then the cart badge should show "2"

  Scenario: Sort products by price low to high
    When I sort products by "lohi"
    Then the products should be sorted by price low to high