package Akamai.EtETest;

import org.testng.Assert;
import org.testng.annotations.Test;

import Akamai.PageObjects.HomePageETE;
import Akamai.PageObjects.MyCartETE;
import Akamai.TestComponentsETE.BaseTest;
import java.io.IOException;

public class ErrorValidationsTestETE extends BaseTest {

	@Test
    public void qoginErrorValidation() throws IOException, InterruptedException {
        // Assuming landingpage is already initialized in BaseTest
		   landingpage.loginApplication("sourabhbhosale@gmail.com", "Sourabhd@99");
        
        // Assert error message
        Assert.assertEquals("Incorrect Remove email or password.", landingpage.getTheErrorMessage());
        
        System.out.println("loginErrorValidation Executed");
    }


	@Test
	public void prouctErrorValidation() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
       // launchApplication();
		String prodName = "ADIDAS ORIGINAL";
		HomePageETE carpage = landingpage.loginApplication("sourabhbhosale@gmail.com", "Sourabh@99");
		carpage.addProductToCart(prodName);
		MyCartETE cart = carpage.goToCartPage();
		boolean match = cart.verifyProductDisplay("ADIDAS ORIGINAL 23");
		Assert.assertFalse(match);
		
		System.out.println("prouctErrorValidation Executed");
		//tearDown();
	}

	
	
	
	

}
