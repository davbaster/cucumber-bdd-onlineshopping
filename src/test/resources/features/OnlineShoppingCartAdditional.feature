Feature: Online Shopping Cart Additional Scenarios

  As a customer
  I want to add, view, and remove items from my shopping cart
  So that I can manage my purchases efficiently.

  Scenario: Adding multiple quantities of the same item to the shopping cart
    Given I am a logged-in customer
    And I have an empty shopping cart
    When I add "Blue T-shirt" to the cart 3 times
    Then the cart should contain 3 items
    And the cart should display "Blue T-shirt" with quantity "3"

  Scenario: Verifying total price of items in the cart
    Given I am a logged-in customer
    And I have an empty shopping cart
    When I add "Red Hoodie" priced at "$40" to the cart
    And I add "Green Cap" priced at "$15" to the cart
    Then the total price of the cart should be "$55"
