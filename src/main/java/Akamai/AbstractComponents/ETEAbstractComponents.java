package Akamai.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Akamai.PageObjects.MyCartETE;
import Akamai.PageObjects.OrderPageETE;
import java.time.Duration;

public class ETEAbstractComponents {
WebDriver driver;

	
	
	public ETEAbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}


	@FindBy(css="[routerlink*='cart']")
	private WebElement clickCart;

	@FindBy(css="[routerlink*='myorders']")
	private WebElement myOrders;
	
	
	
	public MyCartETE goToCartPage()
	{
		 clickCart.click();
		 MyCartETE cart=new MyCartETE(driver);
		 return cart;
	}

	
	public OrderPageETE goToOrdersPage()
	{
		 myOrders.click();
		 OrderPageETE pageOrder=new OrderPageETE(driver);
		 return pageOrder;
	}
	
	public void waitForElememtToAppear(By FindBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}

	public void waitForWebElementToAppear(WebElement findBy)
	{
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		//value is comming from landing page
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementTodisapppear(WebElement FindBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(FindBy));

	}

}
