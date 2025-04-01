package Akamai.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Akamai.AbstractComponents.ETEAbstractComponents;

public class LandingPageETE extends ETEAbstractComponents{
	WebDriver driver;
	
	public  LandingPageETE(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmailId=driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement userEmailId;
	
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css = ".toast-message ")
	WebElement errorMessage;
	
	
	public HomePageETE loginApplication(String email,String pass)
	{
		userEmailId.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		HomePageETE carpage=new HomePageETE(driver);
		return carpage;
		
	}
	
	public String getTheErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	//.sendKeys("sourabhbhosale@gmail.com");
	//driver.findElement(By.id("userPassword")).sendKeys("Sourabh@99");
	//driver.findElement(By.id("login")).click();
}
