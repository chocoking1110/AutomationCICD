package rahulshettyacademy.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartInfo;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	public CheckoutPage checkOut() {
		checkOutButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
	public Boolean verify(String productName) {
		Boolean match = cartInfo.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
