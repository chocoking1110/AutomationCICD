package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[placeholder='Select Country']")
	WebElement countryDropDown;
	
	By searchResult =  By.cssSelector(".ta-results");;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement e;
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	public void fillShippingInfo(String country) {
		Actions action = new Actions(driver);
		action.sendKeys(countryDropDown, country).build().perform();
		waitForElementToAppear(searchResult);
		e.click();
	}
	
	public ConfirmationPage submitOrder() {
		submitButton.click();
		return new ConfirmationPage(driver);
	}
	

}
