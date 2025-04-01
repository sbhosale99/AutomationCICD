package TestYantraSolution_New.Test;


import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import TestYantraSolutions_New.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestCase {

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		// TODO Auto-generated method stub
		
		String prodName="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		
		LandingPage page=new LandingPage(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("sourabhbhosale@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sourabh@99");
		driver.findElement(By.id("login")).click();
		
		
		driver.manage().window().maximize();
		
		List<WebElement> Products=driver.findElements(By.cssSelector(".mb-3"));
		
			WebElement Prod=Products.stream().filter(Product->Product.findElement
						(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
		
			
			 Prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
      driver.findElement(By.cssSelector("[routerlink*='cart']")).click();		
		
	
	List<WebElement> addPrducts=driver.findElements(By.cssSelector(".cartSection h3"));
	  
	boolean match=  addPrducts.stream().anyMatch(addProduct-> addProduct.getText().equalsIgnoreCase(prodName));
	Assert.assertTrue(match);
	
	driver.findElement(By.xpath("//li[@class=\"totalRow\"]/button")).click();
	
	Actions a =new Actions(driver);
	
	Thread.sleep(1000);	
	a.sendKeys(driver.findElement(By.xpath("//div[@class=\"form-group\"]/input")), "India").build().perform();
	
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down by a little amount (e.g., 300 pixels)
		js.executeScript("window.scrollBy(0, 400);");
	
	   wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//section[2]")));
    
	
    	driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String textFrompage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(textFrompage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close();
	}

	}

