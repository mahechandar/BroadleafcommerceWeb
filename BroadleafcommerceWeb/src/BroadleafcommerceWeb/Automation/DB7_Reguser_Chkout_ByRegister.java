package BroadleafcommerceWeb.Automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DB7_Reguser_Chkout_ByRegister extends BaseTest 
{
	@Test
	public void DB7_reguser_chkout_byRegister() throws InterruptedException
	{
		//*** Click REGISTER link in the home page
		driver.findElement(By.linkText("Register")).click();
		
		//*** Validate "REGISTER OR LOG IN OR FORGOT PASSWORD" links page is displayed
		//*** Enter Email,First Name, Last Name, Password, confirm Password with correct input data
		//*** Validate WELCOME with the entered First Name page is show and Logout link is displayed.
		Register();
		
		//*** Click "Buy Now" on the available products. Quantity is 4 after adding multiple products
		//*** Ensure 4 products are added to cart. Bag count should be 4.
		SelectProducts();
		
		//*** Validate selected products is available in the cart.
		ValidateProducts();
		
		//*** Click on "Checkout" button available on cart PopUp window.
		//*** Order Summary page should load with all the 4 products and  'Email OR Login to your account OR register a new account ' section
		driver.findElement(By.cssSelector("a.big-button.red-button")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector("#cart > div > div:nth-child(4) > div:nth-child(1) > div > div:nth-child(2) > div")).isDisplayed());
		
		//*** Billing Information page should be loaded. 
		BillingInformation();
		
		//*** Enter Shipping Information (FirstName, LastName, Phone, Address, Address2, 
		//*** City/State, and Postal Code) as provided in the test data column, Select "Shipping Method"options and click 'SELECT SHIPPING'
		//*** Payment Information page  should be loaded.
		ShippingInformation();
		
		//*** Enter Payment Information as provided in the test data column and click 'Checkout' 
		//*** Confirmation page should display a Success message  having the Order Confirmation number 
		//***along with the ordered item details, Shipping Information and Billing Information.
		PaymentInformation();
		
		//*** Validate selected products is available in the cart.
		ValidateProducts();
	}
}
