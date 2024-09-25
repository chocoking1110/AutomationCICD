package rahulshettyacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.data.DataReader;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getDataFromJson", groups = "Purchase")
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// if use dataProvider simple data : public void SubmitOrder(String email, String password, String product)
		ProductCatalogue catalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = catalogue.getAllProducts();
		catalogue.addProductToCart(input.get("product"));
		CartPage cartPage = catalogue.goToCartPage();

		// IN CART, VALIDATE PRODUCT NAME THEN CLICK CHECKOUT BUTTON
		Boolean match = cartPage.verify(input.get("product"));
		Assert.assertTrue(match);

		// FILL SHIPPING INFO
		CheckoutPage checkoutPage = cartPage.checkOut();
		checkoutPage.fillShippingInfo("India");
		ConfirmationPage conf = checkoutPage.submitOrder();

		// SUCCESS PAGE
		String confirmMessage = conf.getSuccessMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	// (dependsOnMethods= {"SubmitOrder"})
	@Test
	public void OrderHistoryTest() throws IOException {
		ProductCatalogue catalogue = landingPage.loginApplication("chocoking@gmail.com", "@Bc23456");
		OrderPage orderPage = catalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getDataSimple() {
		return new Object[][] { {"chocoking@gmail.com","@Bc23456","ZARA COAT 3"}, {"anshika@gmail.com","Iamking@000","IPHONE 13 PRO"}};
	}
	
	@DataProvider
	public Object[][] getDataFromHashMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "chocoking@gmail.com");
		map.put("password", "@Bc23456");
		map.put("product", "ZARA COAT 3");

		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("product", "IPHONE 13 PRO");
		return new Object[][] { { map }, { map1 } };
	}
	
	@DataProvider
	public Object [][] getDataFromJson() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}
	

}
