package Akamai.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPageETE {
	WebDriver driver;
	
	public  OrderPageETE(WebDriver driver)
	{
		super();
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> addPrducts=driver.findElements(By.cssSelector(".cartSection h3"));
	
	
	@FindBy(xpath ="//table/tbody/tr/td[text()=\"ADIDAS ORIGINAL\"]")
	List<WebElement> productNames;
	

	
	
	public boolean verifyOrderDisplay(String productName)
	{
		boolean match=  productNames.stream().anyMatch(addProduct-> addProduct.getText().equalsIgnoreCase(productName));
		return match;

	}
	
}

