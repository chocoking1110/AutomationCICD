package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriver driver;
	
	@FindBy(css=".hero-primary")
	WebElement successMessage;
	
	@FindBy(css="label[routerlink*='myorders']")
	WebElement orderHistoryLink;
	
	public String getSuccessMessage() {
		String confirmMessage = successMessage.getText();
		return confirmMessage;
	}
}
