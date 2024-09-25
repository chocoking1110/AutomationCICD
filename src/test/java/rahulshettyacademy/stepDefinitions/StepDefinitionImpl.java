package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue catalogue;
	public ConfirmationPage conf;
	@Given("I am on landing page")
	public void i_am_on_landing_page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^logged in with email (.+) and password (.+)$")
	public void logged_in_with_email_and_password(String email, String password) {
		catalogue = landingPage.loginApplication(email, password);
	}
	/*
	@Given("^logged in with email (.+) and password (.+)$")
	public void logged_in_with_email_and_password(String email, String password) {
		catalogue = landingPage.loginApplication(email, password);
	}
	
	*/
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = catalogue.getAllProducts();
		catalogue.addProductToCart(productName);	
	}
	
	@When("^I checkout productName (.+) and submit order$")
	public void i_check_out_and_submit_order(String productName) {
		CartPage cartPage = catalogue.goToCartPage();
		Boolean match = cartPage.verify(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.checkOut();
		checkoutPage.fillShippingInfo("India");
		conf = checkoutPage.submitOrder();
	}
	
	@Then ("{string} message is displayed on Confirmation Page")
	public void message_displayed_on_confirmation_page(String string) {
		String confirmMessage  = conf.getSuccessMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void something_message_is_displayed(String arg) throws Throwable{
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		driver.close();
	}
}
