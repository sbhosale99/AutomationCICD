package Akamai.PageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyCartETE {
	WebDriver driver;
	
	public  MyCartETE(WebDriver driver)
	{
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> addPrducts=driver.findElements(By.cssSelector(".cartSection h3"));
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitales;
	
	@FindBy(xpath="//li[@class=\"totalRow\"]/button")
	WebElement checkOutclick;
	
	
	public boolean verifyProductDisplay(String productName)
	{
		boolean match=  productTitales.stream().anyMatch(addProduct-> addProduct.getText().equalsIgnoreCase(productName));
		return match;

	}
	public checkOutPageETE goTocheckOutPage() throws InterruptedException
	{
		Actions a =new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll down by a little amount (e.g., 300 pixels)
		js.executeScript("window.scrollBy(0, 400);");
		Thread.sleep(1000);
		checkOutclick.click();
		 return new checkOutPageETE(driver);
	}
	
}

