package rahulshettyacademy.tests;

import org.testng.annotations.Test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest {
	
	@Test(groups="Error Handling",retryAnalyzer=Retry.class)
	public void LoginErrorValidation() {
		landingPage.loginApplication("chocoking@gmail.com", "@Bc2345");
		Assert.assertEquals("Incorrect email or pasword.",landingPage.getErrorMessage());
		
	}
	
	@Test
	public void productValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue catalogue = landingPage.loginApplication("chocoking@gmail.com", "@Bc23456");
		List<WebElement> products = catalogue.getAllProducts();
		catalogue.addProductToCart(productName);
		CartPage cartPage = catalogue.goToCartPage();
		Boolean match = cartPage.verify("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
