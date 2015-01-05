package BroadleafcommerceWeb.Automation;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DB2_Guestchkout_SameBilingShipping extends BaseTest 
{

	@Test
	public void DB2_guestchkout_sameBilingShipping() throws InterruptedException
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
		DateFormat currentdatetime = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = currentdatetime.format(new Date(System.currentTimeMillis()));
		String email = "Test" + datetime + "@mailinator.com";
		driver.findElement(By.name("emailAddress")).clear();
		driver.findElement(By.name("emailAddress")).sendKeys(email);
		driver.findElement(By.cssSelector("input.small.red")).click();
		Thread.sleep(2000);
		
		//*** Billing Information page should be loaded. 
		BillingInformation();
		
		//*** Click on "Use Billing Information" check box, Select "Shipping Method"options and click 'SELECT SHIPPING' 
		//*** Payment Information page  should be loaded.
		driver.findElement(By.id("use_billing_address")).click();
		driver.findElements(By.id("address.firstName")).get(0).getAttribute("value").equals("Katherine");
		driver.findElements(By.id("address.lastName")).get(0).getAttribute("value").equals("Pina");
		driver.findElements(By.id("address.phonePrimary")).get(0).getAttribute("value").equals("256-369-6655");
		driver.findElements(By.id("address.addressLine1")).get(0).getAttribute("value").equals("137, Anna Marczykowski");
		driver.findElements(By.id("address.addressLine2")).get(0).getAttribute("value").equals("85 Silver St");
		driver.findElements(By.id("address.city")).get(0).getAttribute("value").equals("New Britain");
		
		//*** verify Billing and shipping values are same
		driver.findElements(By.id("address.postalCode")).get(0).getAttribute("value").equals("06053-3790");
		driver.findElement(By.id("fulfillmentOptionId1")).click();
		driver.findElement(By.id("select_shipping")).click();
		Thread.sleep(2000);
		
		//*** Enter Payment Information as provided in the test data column and click 'Checkout' 
		//*** Confirmation page should display a Success message  having the Order Confirmation number 
		//***along with the ordered item details, Shipping Information and Billing Information.
		PaymentInformation();
		
		//*** Validate selected products is available in the cart.
		ValidateProducts();
	}
}
