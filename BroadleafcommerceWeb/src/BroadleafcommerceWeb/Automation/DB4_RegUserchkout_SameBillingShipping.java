package BroadleafcommerceWeb.Automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DB4_RegUserchkout_SameBillingShipping extends BaseTest 
{

	@Test
	public void DB4_regUserchkout_sameBillingShipping() throws InterruptedException
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
		Assert.assertTrue(driver.findElement(By.cssSelector("#cart > div > div:nth-child(4) > div:nth-child(1) > div > div:nth-child(2) > div")).isDisplayed());
		
		//*** Click "Register a new account" link displayed on  Order summary Page.Enter Email,First Name, Last Name, Password, confirm Password with appropriate values. 
		//*** Validate "REGISTER OR LOG IN OR FORGOT PASSWORD" links page is displayed
		//*** Enter Email,First Name, Last Name, Password, confirm Password with correct input data
		//*** Validate WELCOME with the entered First Name page is show and Logout link is displayed.
		driver.findElement(By.cssSelector("#cart > div > div:nth-child(4) > div:nth-child(1) > div > div:nth-child(2) > div > p > a:nth-child(4)")).click();
		Thread.sleep(2000);
		Register();
		
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
