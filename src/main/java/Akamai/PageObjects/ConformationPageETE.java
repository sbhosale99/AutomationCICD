package Akamai.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Akamai.AbstractComponents.ETEAbstractComponents;

public class ConformationPageETE extends ETEAbstractComponents {

	
	WebDriver driver;
	
	public ConformationPageETE(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[@class=\"hero-primary\"]")
	WebElement conformationMessage;
	
	
	public String getConformationMessage()
	{
		return conformationMessage.getText();
	}
}
