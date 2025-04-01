package Akamai.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Akamai.AbstractComponents.ETEAbstractComponents;

public class HomePageETE extends ETEAbstractComponents {
	WebDriver driver;
	
	public  HomePageETE(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}
	
	//WebElement userEmailId=driver.findElement(By.id("userEmail"));
	//List<WebElement> Products=driver.findElements(By.cssSelector(".mb-3"));
	
	
	
	@FindBy(css=".mb-3")
	List<WebElement> Products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	By productBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By createToastmessage=By.id("toast-container");
	
	
	public List<WebElement> getProductList()
	{
		waitForElememtToAppear(productBy);
		return Products;
	}
	
	public WebElement getProductByName(String productName)
	{
	
		WebElement Prod=getProductList().stream().filter(Product->Product.findElement
					(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return Prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		//Thread.sleep(1000);
		waitForElememtToAppear(createToastmessage);
		waitForElementTodisapppear(spinner);
	}
	
	
	

}
