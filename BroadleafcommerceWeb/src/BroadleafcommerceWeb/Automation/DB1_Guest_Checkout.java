package BroadleafcommerceWeb.Automation;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DB1_Guest_Checkout extends BaseTest 
{
	@Test
	public void DB1_guest_checkout() throws InterruptedException
	{
		//*** Click "Buy Now" on the available products. Quantity is 4 after adding multiple products
		//*** Ensure 4 products are added to cart. Bag count should be 4.
		SelectProducts();
		
		//*** Validate selected products is available in the cart.
		ValidateProducts();
		
		//*** Click on "Checkout" button available on cart PopUp window.
		//*** Order Summary page should load with all the 4 products and  'Email OR Login to your account OR register a new account ' section
		driver.findElement(By.cssSelector("a.big-button.red-button")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector("div.group.order-info.edit-order-info")).isDisplayed());
		
		//*** Enter the email address and click "Checkout As Guest"
		DateFormat currentdatetime = new SimpleDateFormat("yyyyMMddHHmm");
		String datetime = currentdatetime.format(new Date(System.currentTimeMillis()));
		String email = "Test" + datetime + "@mailinator.com";
		driver.findElement(By.name("emailAddress")).clear();
		driver.findElement(By.name("emailAddress")).sendKeys(email);
		driver.findElement(By.cssSelector("input.small.red")).click();
		Thread.sleep(2000);
		
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
