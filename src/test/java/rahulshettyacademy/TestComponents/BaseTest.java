package rahulshettyacademy.TestComponents;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		// properties class
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream("/Users/choco/eclipse-workspace/SeleniumFrameworkDesign/src/test/java/rahulshettyacademy/resources/GlobalData.properties");
		prop.load(input);
		
		///String browserName = prop.getProperty("browser");
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions opt = new ChromeOptions();
			WebDriverManager.chromedriver().setup();	
			if(browserName.contains("headless")) {
				opt.addArguments("headless");
			}
			driver = new ChromeDriver(opt);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeTest() {
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String takeScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot shot = (TakesScreenshot) driver;
		File source = shot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
}
