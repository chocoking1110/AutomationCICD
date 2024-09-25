package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//table/tbody/tr/th")
	List<WebElement> productIdList;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNameList;
	
	public Boolean verifyOrderDisplay(String name) {
		Boolean match = productNameList.stream().anyMatch(product->product.getText().equalsIgnoreCase(name));
		
		return match;
		
	}
}
