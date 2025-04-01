package TestYantraSolution_New.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestYantraSolution_New.TestComponents.BaseTest;
import TestYantraSolutions_New.PageObjects.HomePage;
import TestYantraSolutions_New.PageObjects.MyCart;
import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {

	@Test(retryAnalyzer=TestYantraSolution_New.TestComponents.Retry.class)
    public void qoginErrorValidation() throws IOException, InterruptedException {
        // Assuming landingpage is already initialized in BaseTest
        landingpage.loginApplication("sourabhbhosale@gmail.com", "Sourabhd@99");
        
        // Assert error message
        Assert.assertEquals("Incorrect Remove email or password.", landingpage.getTheErrorMessage());
        
        System.out.println("loginErrorValidation Executed");
    }


	@Test(retryAnalyzer=TestYantraSolution_New.TestComponents.Retry.class)
	public void prouctErrorValidation() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
       // launchApplication();
		String prodName = "ADIDAS ORIGINAL";
		HomePage carpage = landingpage.loginApplication("sourabhbhosale@gmail.com", "Sourabh@99");
		carpage.addProductToCart(prodName);
		MyCart cart = carpage.goToCartPage();
		boolean match = cart.verifyProductDisplay("ADIDAS ORIGINAL 23");
		Assert.assertFalse(match);
		
		System.out.println("prouctErrorValidation Executed");
		//tearDown();
	}

	
	
	
	

}
