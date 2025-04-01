package Akamai.stepDefination;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import Akamai.PageObjects.ConformationPageETE;
import Akamai.PageObjects.HomePageETE;
import Akamai.PageObjects.LandingPageETE;
import Akamai.PageObjects.MyCartETE;
import Akamai.PageObjects.checkOutPageETE;
import Akamai.TestComponentsETE.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;

public class StepDefinationsimpl extends BaseTest {
	LandingPageETE landingpage;
	HomePageETE carpage;
	MyCartETE cart;
	checkOutPageETE checkout;
	ConformationPageETE conform;
	@Given(value = "I landed on Ecommerce Page")
	public void i_LandedOn_EcommerceWebsite() throws IOException
	{
		landingpage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String userName, String password) {
	    carpage = landingpage.loginApplication(userName, password);
	}
	
	//    When I add product<productName> to cart
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
	    carpage.addProductToCart(productName);
	}
	//And checkOut<productName> and submit the order
    
	@When("^I check out (.+) and submit the order$")
	public void i_check_out_and_submit_order(String productName) throws InterruptedException {
	    MyCartETE cart = carpage.goToCartPage();

	    boolean match = cart.verifyProductDisplay(productName);
	    Assert.assertTrue(match, "Product is not displayed in the cart!");

	    checkout = cart.goTocheckOutPage();   // Fixed typo: goTocheckOutPage -> goToCheckoutPage
	    checkout.selectCountry("India");
	    conform = checkout.SubmitOrder();     // Fixed typo: SubmitOrder -> submitOrder
	}

	//Then "THANKYOU FOR THE ORDER." message is displayed on ConformationPage
	@Then("\"{string}\" message is displayed on ConfirmationPage")
	public void message_is_displayed_on_confirmation_page(String expectedMessage) {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, 0);");  // Scrolls to the top of the page
	    String actualMessage = conform.getConformationMessage();

	    Assert.assertNotNull(actualMessage, "Confirmation message is null!");
	    Assert.assertEquals(actualMessage.trim(), expectedMessage.trim(), "Confirmation message does not match!");
	}



}
