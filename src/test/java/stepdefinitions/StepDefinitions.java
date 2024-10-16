package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StepDefinitions {

    private List<String> shoppingCart = new ArrayList<>();
    private boolean isLoggedIn = false;
    private Map<String, Double> priceMap = new HashMap<>();

    // Set up prices for items in the cart
    public StepDefinitions() {
        priceMap.put("Red Hoodie", 40.0);
        priceMap.put("Green Cap", 15.0);
        priceMap.put("Blue T-shirt", 20.0);
        priceMap.put("Black Sneakers", 50.0);
    }

    @Given("I am a logged-in customer")
    public void i_am_a_logged_in_customer() {
        // Simulate the customer login process
        isLoggedIn = true;
    }

    @Given("I have an empty shopping cart")
    public void i_have_an_empty_shopping_cart() {
        shoppingCart.clear();
    }

    @Given("I have {string} in my shopping cart")
    public void i_have_item_in_my_shopping_cart(String item) {
        shoppingCart.clear(); // Clear previous items for simplicity
        shoppingCart.add(item);
    }

    @When("I add {string} to the cart")
    public void i_add_a_to_the_cart(String item) {
        shoppingCart.add(item);
    }

    @When("I remove the {string} from the cart")
    public void i_remove_the_from_the_cart(String item) {
        shoppingCart.remove(item);
    }

    @When("I add {string} to the cart {int} times")
    public void i_add_item_to_cart_multiple_times(String item, Integer times) {
        for (int i = 0; i < times; i++) {
            shoppingCart.add(item);
        }
    }

    @When("I add {string} priced at {string} to the cart")
    public void i_add_priced_at_to_the_cart(String item, String price) {
        shoppingCart.add(item);
        double itemPrice = Double.parseDouble(price.replace("$", ""));
        priceMap.put(item, itemPrice); // Update the price in the priceMap
    }

    @Then("the cart should contain {int} items")
    public void the_cart_should_contain_items(Integer itemCount) {
        if (shoppingCart.size() != itemCount) {
            throw new AssertionError("The number of items in the cart is incorrect. Expected: " + itemCount + " but got: " + shoppingCart.size());
        }
    }

    @Then("the cart should contain {int} item")
    public void the_cart_should_contain_item(Integer itemCount) {
        if (shoppingCart.size() != itemCount) {
            throw new AssertionError("The number of items in the cart is incorrect. Expected: " + itemCount + " but got: " + shoppingCart.size());
        }
    }

    @Then("the cart should display {string}")
    public void the_cart_should_display(String item) {
        if (!shoppingCart.contains(item)) {
            throw new AssertionError("The cart does not contain the expected item: " + item);
        }
    }

    @Then("the cart should display {string} and {string}")
    public void the_cart_should_display_and(String firstItem, String secondItem) {
        if (!(shoppingCart.contains(firstItem) && shoppingCart.contains(secondItem))) {
            throw new AssertionError("The cart does not contain the expected items: " + firstItem + " and " + secondItem);
        }
    }

    @Then("the total price of the cart should be {string}")
    public void the_total_price_of_the_cart_should_be(String expectedPrice) {
        double totalPrice = calculateTotalPrice(shoppingCart);
        double expectedPriceAsDouble = Double.parseDouble(expectedPrice.replace("$", ""));
        if (Math.abs(totalPrice - expectedPriceAsDouble) > 0.01) {
            throw new AssertionError("Total price is incorrect. Expected: $" + expectedPriceAsDouble + ", but got: $" + totalPrice);
        }
    }

    @Then("the cart should display {string} with quantity {int}")
    public void the_cart_should_display_with_quantity(String item, Integer quantity) {
        long count = shoppingCart.stream().filter(i -> i.equals(item)).count();
        if (count != quantity) {
            throw new AssertionError("The quantity of the item in the cart is incorrect. Expected: " + quantity + " but got: " + count);
        }
    }

    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        if (!shoppingCart.isEmpty()) {
            throw new AssertionError("The cart is not empty as expected.");
        }
    }

    // Helper method to calculate total price of items in the cart
    private double calculateTotalPrice(List<String> shoppingCart) {
        double totalPrice = 0.0;
        for (String item : shoppingCart) {
            totalPrice += priceMap.getOrDefault(item, 0.0);
        }
        return totalPrice;
    }
}
