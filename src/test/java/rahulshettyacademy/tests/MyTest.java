package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("Webdriver.firefox.driver", "Users/choco/Documents/coding/geckodriver");
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("chocoking@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("@Bc23456");
		driver.findElement(By.id("login")).click();
		
		// SHOPPING PAGE
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("products")));
		Thread.sleep(2000);
		
		//Teacher way to find product
		//List<WebElement> listOfProducts = driver.findElements(By.cssSelector(".mb-3"));
		//WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		List<WebElement> products = driver.findElements(By.cssSelector("div[class='card-body']"));
		
		String name = products.get(0).findElement(By.tagName("h5")).getText();
		System.out.println("product name " + name);
		products.get(0).findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[aria-label='Product Added To Cart']")));
		String alertText = driver.findElement(By.cssSelector("div[aria-label='Product Added To Cart']")).getText();
		String expectedProductAddMessage = "Product Added To Cart";
		System.out.println(alertText);
		
		// verify success message
		if (alertText.equalsIgnoreCase(expectedProductAddMessage)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
		Thread.sleep(2000);		
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("button[class='btn btn-custom']")));
		WebElement cartButton = driver.findElement(By.cssSelector("button[routerlink*='cart']"));
		wait.until(ExpectedConditions.elementToBeClickable(cartButton));
		cartButton.click();
		
		Thread.sleep(2000);
		String productInCart = driver.findElement(By.xpath("//div[@class='cartSection']/h3")).getText();
		System.out.println("Product in cart "+productInCart);

		// verify product name in cart
		if (productInCart.equalsIgnoreCase(name)) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
		
		// CLICK ON CHECKOUT
		driver.findElement(By.xpath("//div[@class='infoWrap']/div[3]/button")).click();

		// checkout
		// card num
		driver.findElement(By.cssSelector("input[class*='input']:nth-child(2)")).clear();
		driver.findElement(By.cssSelector("input[class*='input']:nth-child(2)")).sendKeys("12345678");
		// expiry
		Select month = new Select(driver.findElement(By.cssSelector("select:nth-child(2)")));
		month.selectByVisibleText("11");
		Select year = new Select(driver.findElement(By.cssSelector("select:nth-child(3)")));
		year.selectByVisibleText("20");
		// ccv   
		//css selector: div.row:nth-child(2) > div:nth-child(2) > input:nth-child(2)
		//xpath: /html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[1]/form/div/div[2]/div[2]/input
		
		driver.findElement(By.cssSelector("div.row:nth-child(2) > div:nth-child(2) > input:nth-child(2)")).sendKeys("100");
		// name
		driver.findElement(By.cssSelector("div.row:nth-child(3) > div:nth-child(1) > input:nth-child(2)")).sendKeys("Choco");
		// country
		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"Vietnam").build().perform();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][1]")).click();
		// submit
		driver.findElement(By.linkText("PLACE ORDER")).click();
		
		// on checkout success, check "THANK YOU FOR THE ORDER" text and get order id
		String text = driver.findElement(By.xpath("//tbody/tr/td/h1")).getText();
		System.out.println("Text on success page " + text);
		String orderId = driver.findElement(By.xpath("//tbody/tr[3]/td/label")).getText();
		System.out.println("Order id on success page " + orderId);
		// on manage order, check if order id exist
		

		// view order and check details like order id, billing address, delivery address, and try to delete order
		
	}

}
