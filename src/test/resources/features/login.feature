Feature: Login functionality
  As a SauceDemo user
  I want to login with my credentials
  So that I can access the product inventory

  Background:
    Given I am on the SauceDemo login page

  Scenario: Successful login with valid credentials
    When I login with valid credentials
    Then I should be redirected to the inventory page

  Scenario: Login with locked out user
    When I login with locked out user credentials
    Then I should see error message containing "locked out"

  Scenario: Login with wrong credentials
    When I login with username "wrong_user" and password "wrong_pass"
    Then I should see error message containing "Username and password do not match"

  Scenario: Login with empty username
    When I login with username "" and password "secret_sauce"
    Then I should see error message containing "Username is required"

  Scenario Outline: Login with multiple invalid users from Excel
    When I login with username "<username>" and password "<password>"
    Then I should see error message containing "<error>"

    Examples:
      | username      | password    | error                                  |
      | invalid_user1 | wrong_pass  | Username and password do not match     |
      | invalid_user2 | wrong_pass  | Username and password do not match     |