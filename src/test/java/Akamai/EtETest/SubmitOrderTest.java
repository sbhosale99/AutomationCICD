package Akamai.EtETest;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Akamai.PageObjects.ConformationPageETE;
import Akamai.PageObjects.HomePageETE;
import Akamai.PageObjects.MyCartETE;
import Akamai.PageObjects.checkOutPageETE;
import Akamai.TestComponentsETE.BaseTest;
import java.io.IOException;

public class SubmitOrderTest extends BaseTest {
	//String prodName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider = "getData")
	public void sumitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub

		//String prodName = "ADIDAS ORIGINAL";
		  // Extract data from the HashMap
        String userId = input.get("email");
        String password = input.get("password");
        String prodName = input.get("product");
		
		
		HomePageETE carpage = landingpage.loginApplication(userId, password);

		carpage.addProductToCart(prodName);
		
		MyCartETE cart = carpage.goToCartPage();

		boolean match = cart.verifyProductDisplay(prodName);

		Assert.assertTrue(match);

		checkOutPageETE checkout = cart.goTocheckOutPage();
		checkout.selectCountry("india");
		ConformationPageETE conform = checkout.SubmitOrder();

		// driver.findElement(By.xpath("//a[contains(text(),\"Place
		// Order\")]")).click();

		String textFrompage = conform.getConformationMessage();

		Assert.assertTrue(textFrompage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		
		//It will close the Page and method is from base Test
		//closeWebPage();
		System.out.println("sumitOrder Executed");
		//tearDown();

	}
	

	   @DataProvider(name = "getData")
	    public Object[][] getData() throws IOException {
	        // Creating a HashMap for each test case
		   
		 List<HashMap<String,String>> data= getJsonDataToMapETE(System.getProperty("user.dir") + "//src//test//java//Akamai//NewDataETE//PurcheshOrder.json");
		  return new Object [][] {{data.get(0)}, {data.get(1) } };
	            
	        }
	  	
	
	/*It looks like you are trying to use a @DataProvider to pass multiple sets of data to your test, but there are a few issues with your current implementation. Let's break down the issues and fix them:

	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"sourabhbhosale@gmail.com","Sourabh@99","ADIDAS ORIGINAL"}, {"akjodgand@gmail.com","Akshay@99","ZARA COAT 3"}};
	}

	*/
//       HashMap<String, String> map1 = new HashMap<>();
//       
//       map1.put("email", "sourabhbhosale@gmail.com");
//       map1.put("password", "Sourabh@99");
//       map1.put("product", "ADIDAS ORIGINAL");
//
//       HashMap<String, String> map2 = new HashMap<>();
//       map2.put("email", "akjodgand@gmail.com");
//       map2.put("password", "Akshay@99");
//       map2.put("product", "ZARA COAT 3");
//
//       // Returning the HashMaps as Object[][] for TestNG to handle
//       return new Object[][] 
	
	
	/*

	//Verify that is added product is displaying in order page
	@Test(dependsOnMethods = {"sumitOrder"})
	public void orderHistoryTest() throws IOException
	{
		//launchApplication();
		HomePage carpage = landingpage.loginApplication("sourabhbhosale@gmail.com", "Sourabh@99");
		OrderPage order=carpage.goToOrdersPage();
		Assert.assertTrue(order.verifyOrderDisplay(prodName));
		//tearDown();
	}
	*/
}

