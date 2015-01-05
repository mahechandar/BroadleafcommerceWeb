package BroadleafcommerceWeb.Automation;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest 
{
	WebDriver driver;
	
	@BeforeMethod
	public void InvokeBrowser()
	{
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
		//*** Navigate to Home Page URL http://demo.broadleafcommerce.org/
		//*** Validate Login/Register link is displayed. 
		driver.get("http://demo.broadleafcommerce.org/");
		Assert.assertTrue(driver.findElement(By.linkText("Login")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
	}
	
	@AfterMethod
	public void CloseBrowser()
	{
		driver.close();
		driver.quit();
	}

	public void Login() throws InterruptedException
	{
		//*** Validate Email, Password text fields and LOGIN button are displayed 
		//*** Provide [Email] and [Password] test data and click LOGIN 
		//*** Validate WELCOME with the entered First Name page is show and Logout link is displayed. 
		Assert.assertTrue(driver.findElement(By.name("j_username")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.name("j_password")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("input.login_button.big.red")).isDisplayed());
		driver.findElement(By.name("j_username")).clear();
		driver.findElement(By.name("j_username")).sendKeys("smith@mailinator.com");
		driver.findElement(By.name("j_password")).clear();
		driver.findElement(By.name("j_password")).sendKeys("test123");
		driver.findElement(By.cssSelector("input.login_button.big.red")).click();
		Thread.sleep(2000);
	}
	
	public void Logout() throws InterruptedException
	{
		//*** Click on Logout
		//*** Ensure the logout was successful and Validate LOGIN / REGISTER link is displayed.
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.linkText("Login")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
		
	}
	
	public String Register() throws InterruptedException
	{
		//*** Validate "REGISTER OR LOG IN OR FORGOT PASSWORD" links page is displayed
		Assert.assertTrue(driver.findElement(By.id("register")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("input.register_button.big.red")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.className("login_register_alt_links")).isDisplayed());
		
		//*** Enter Email,First Name, Last Name, Password, confirm Password with correct input data
		//*** Validate WELCOME with the entered First Name page is show and Logout link is displayed.
		DateFormat currentdatetime = new SimpleDateFormat("yyyyMMddHHmmss");
		String datetime = currentdatetime.format(new Date(System.currentTimeMillis()));
		String email = "Test" + datetime + "@mailinator.com";
		driver.findElement(By.id("customer.emailAddress")).clear();
		driver.findElement(By.id("customer.emailAddress")).sendKeys(email);
		driver.findElement(By.id("customer.firstName")).clear();
		driver.findElement(By.id("customer.firstName")).sendKeys("Xavier");
		driver.findElement(By.id("customer.lastName")).clear();
		driver.findElement(By.id("customer.lastName")).sendKeys("Zion");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("test123");
		driver.findElement(By.id("passwordConfirm")).clear();
		driver.findElement(By.id("passwordConfirm")).sendKeys("test123");
		driver.findElement(By.cssSelector("input.register_button.big.red")).click();
		Thread.sleep(2000);
		return email;	
	}
	
	public void SelectProducts() throws InterruptedException
	
	{
		//*** Click "Buy Now" on the available products. Quantity is 4 after adding multiple products
		//*** Ensure 4 products are added to cart. Bag count should be 4.
		driver.findElement(By.cssSelector("#products > li:nth-child(1) > div.productActions.productActions3 > div.add_to_cart > form > input.addToCart")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#products > li:nth-child(2) > div.productActions.productActions6 > div.add_to_cart > form > input.addToCart")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#products > li:nth-child(3) > div.productActions.productActions9 > div.add_to_cart > form > input.addToCart")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#products > li:nth-child(4) > div.productActions.productActions12 > div.add_to_cart > form > input.addToCart")).click();
		Thread.sleep(2000);
		Assert.assertEquals("4", driver.findElement(By.className("headerCartItemsCount")).getText());
		
		//*** Click on  "Cart" button on top Right corner.
		driver.findElement(By.cssSelector("#cartLink")).click();
		Thread.sleep(2000);
	}
	
	public void ValidateProducts()
	{
		//*** Validate selected products is available in the cart.
		Assert.assertTrue(driver.findElement(By.linkText("Hoppin' Hot Sauce")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Day of the Dead Scotch Bonnet Hot Sauce")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Armageddon The Hot Sauce To End All")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.linkText("Cafe Louisiane Sweet Cajun Blackening Sauce")).isDisplayed());
	}
	
	public void BillingInformation() throws InterruptedException
	{
		//*** Enter Billing Information(FirstName, LastName, Phone, Address, Address2, 
		//*** City/State, and Postal Code) as provided in the test data column and click 'SAVE BILLING'
		//*** Shipping Information page  should be loaded.
		driver.findElement(By.id("address.firstName")).clear();
		driver.findElement(By.id("address.firstName")).sendKeys("Katherine");
		driver.findElement(By.id("address.lastName")).clear();
		driver.findElement(By.id("address.lastName")).sendKeys("Pina");
		driver.findElement(By.id("address.phonePrimary")).clear();
		driver.findElement(By.id("address.phonePrimary")).sendKeys("256-369-6655");
		driver.findElement(By.id("address.addressLine1")).clear();
		driver.findElement(By.id("address.addressLine1")).sendKeys("137, Anna Marczykowski");
		driver.findElement(By.id("address.addressLine2")).clear();
		driver.findElement(By.id("address.addressLine2")).sendKeys("85 Silver St");
		driver.findElement(By.id("address.city")).clear();
		driver.findElement(By.id("address.city")).sendKeys("New Britain");
		driver.findElement(By.id("address.postalCode")).clear();
		driver.findElement(By.id("address.postalCode")).sendKeys("06053-3790");
		
		WebElement state = driver.findElement(By.name("address.state"));
		Select clickThis = new Select(state);
		clickThis.selectByValue("CT");
		
		driver.findElement(By.cssSelector("input.medium.red")).click();
		Thread.sleep(2000);
	}
	
	public void ShippingInformation() throws InterruptedException
	{
		//*** Enter Shipping Information (FirstName, LastName, Phone, Address, Address2, 
		//*** City/State, and Postal Code) as provided in the test data column, Select "Shipping Method"options and click 'SELECT SHIPPING'
		//*** Payment Information page  should be loaded.		
		driver.findElements(By.id("address.firstName")).get(1).clear();
		driver.findElements(By.id("address.firstName")).get(1).sendKeys("Xavier");
		driver.findElements(By.id("address.lastName")).get(1).clear();
		driver.findElements(By.id("address.lastName")).get(1).sendKeys("Zion");
		//driver.findElements(By.id("address.phonePrimary")).get(1).clear();
		//driver.findElements(By.id("address.phonePrimary")).get(1).sendKeys("256-369-6655");
		driver.findElements(By.id("address.addressLine1")).get(1).clear();
		driver.findElements(By.id("address.addressLine1")).get(1).sendKeys("9 West 57th Street");
		//driver.findElements(By.id("address.addressLine2")).get(1).clear();
		//driver.findElements(By.id("address.addressLine2")).get(1).sendKeys("85 Silver St");
		driver.findElements(By.id("address.city")).get(1).clear();
		driver.findElements(By.id("address.city")).get(1).sendKeys("New York");
		driver.findElements(By.id("address.postalCode")).get(1).clear();
		driver.findElements(By.id("address.postalCode")).get(1).sendKeys("10019");
		
		WebElement state1 = driver.findElements(By.name("address.state")).get(1);
		Select clickThis = new Select(state1);
		clickThis.selectByValue("NY");
		
		driver.findElement(By.id("fulfillmentOptionId1")).click();
		driver.findElement(By.id("select_shipping")).click();
		Thread.sleep(2000);
	}
	
	public void PaymentInformation() throws InterruptedException
	{
		//*** Payment Information page  should be loaded.
		Assert.assertTrue(driver.findElement(By.cssSelector("div.right_content.payment_info")).isDisplayed());
		
		//*** Enter Payment Information as provided in the test data column and click 'Checkout' 
		//*** Confirmation page should display a Success message  having the Order Confirmation number 
		//***along with the ordered item details, Shipping Information and Billing Information.
		driver.findElement(By.id("cardNumber")).clear();
		driver.findElement(By.id("cardNumber")).sendKeys("4111111111111111");
		driver.findElement(By.id("securityCode")).clear();
		driver.findElement(By.id("securityCode")).sendKeys("123");
		driver.findElement(By.id("nameOnCard")).clear();
		driver.findElement(By.id("nameOnCard")).sendKeys("Xavier Zion");
		driver.findElement(By.id("cardExpDate")).clear();
		driver.findElement(By.id("cardExpDate")).sendKeys("08/2018");
		
		driver.findElement(By.cssSelector("#paymentOptions > dd:nth-child(2) > form > input.medium.red")).click();
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("#order_confirmation > h2 > span")).isDisplayed());
	}
}