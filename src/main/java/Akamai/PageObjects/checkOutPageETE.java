package Akamai.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Akamai.AbstractComponents.ETEAbstractComponents;

public class checkOutPageETE extends ETEAbstractComponents{

	WebDriver driver;
	public checkOutPageETE(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//a[contains(text(),\"Place Order\")]")
	WebElement submit;
	
	@FindBy(xpath ="//div[@class=\"form-group\"]/input")
	WebElement country;
	

	@FindBy(xpath ="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	By reuslts=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) throws InterruptedException
	{
		Actions a =new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll down by a little amount (e.g., 300 pixels)
		js.executeScript("window.scrollBy(0, 400);");
		Thread.sleep(1000);
		a.sendKeys(country, countryName).build().perform();	
		    waitForElememtToAppear(reuslts);
		    selectCountry.click(); 
	}
	public ConformationPageETE SubmitOrder()
	{
		submit.click();
		return new ConformationPageETE(driver);
	}
}
