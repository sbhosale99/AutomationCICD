package Akamai.TestComponentsETE;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Akamai.PageObjects.LandingPageETE;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class BaseTest {
	protected WebDriver driver;

	public LandingPageETE landingpage;

	public WebDriver initilizeDriver() throws IOException {

		// Properties class
		Properties prop = new Properties();

		// Load the global properties file dynamically using user.dir
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//Akamai//Resources//GlobalData.properties");
		prop.load(fis);
        
		//If from command we don't pass should take from .propertes file
		//String browserName= System.getProperty("browser")!=null ?System.getProperty("browser") :prop.getProperty("browser");
		
		String browserName = System.getProperty("browser", prop.getProperty("browser"));

		 

		// Initialize driver based on the browser type specified in the properties file
		if (browserName.equalsIgnoreCase("Chrome") || browserName.equalsIgnoreCase("chromeheadless")) {
		    ChromeOptions options = new ChromeOptions();
		    
		    // Check for headless mode
		    if (browserName.equalsIgnoreCase("chromeheadless")) {
		        options.addArguments("headless"); // Use "--headless" instead of "headless"
		    }
		    
		    driver = new ChromeDriver(options);
		    driver.manage().window().setSize(new Dimension(1440, 900)); // Set window size
		}
		else if (browserName.equalsIgnoreCase("Firefox")) {
			//System.setProperty("webdriver.gecko.driver", "C://Users//soura//Downloads//geckodriver-v0.36.0-win-aarch64//geckodriver.exe");
	       driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {

			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Browser not supported: " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMapETE(String filePath) throws IOException {

		// this method wil read data of json and convert it into string variable
		String jsonContentETE = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// convert String to Hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContentETE,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	// Take ScreenShot method
	 public WebDriver getDriver() {
	        return driver;
	    }

	    public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
	        // Ensure driver is not null
	        if (driver == null) {
	            throw new IllegalStateException("WebDriver is not initialized.");
	        }

	        // Capture screenshot
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File source = ts.getScreenshotAs(OutputType.FILE);

	        // Specify file path for the screenshot
	        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
	        
	        // Copy the screenshot to the specified location
	        FileUtils.copyFile(source, file);

	        // Return the screenshot file path
	        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	    }

	@BeforeMethod(alwaysRun = true)
	public LandingPageETE launchApplication() throws IOException {
		driver = initilizeDriver();
		landingpage = new LandingPageETE(driver);
		landingpage.goTo(); // Assuming goTo() launches the URL or landing page
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		// Close the browser window
		if (driver != null) {
			driver.quit(); // This closes all the browser windows and ends the session
		}

	}
}
