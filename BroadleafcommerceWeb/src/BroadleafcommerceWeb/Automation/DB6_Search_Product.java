package BroadleafcommerceWeb.Automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DB6_Search_Product extends BaseTest
{
	@Test
	public void DB6_search_product() throws InterruptedException
	{
		//Click on 'Login' button and enter [Email] and [Password] test data and click LOGIN 
		//Validate WELCOME with the entered First Name page is show and Logout link is displayed. 
		driver.findElement(By.linkText("Register")).click();
		String emailid=Register();
		Logout();
		
		//*** Login information should be loaded
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("j_username")).clear();
		driver.findElement(By.name("j_username")).sendKeys(emailid);
		driver.findElement(By.name("j_password")).clear();
		driver.findElement(By.name("j_password")).sendKeys("test123");
		driver.findElement(By.cssSelector("input.login_button.big.red")).click();
		Thread.sleep(2000);
		
		//*** Validate WELCOME with the entered First Name page is show and Logout link is displayed.
		Assert.assertEquals("Welcome", driver.findElement(By.cssSelector("#cart_info > span > span")).getText());
		Assert.assertEquals("Xavier", driver.findElement(By.cssSelector("#cart_info > span > a")).getText());
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
		//*** Enter the Key "Hopping Hot Sauce" to search box and click on Search button
		//*** Ensure the search result page with appropreate matching item should be display
		driver.findElement(By.className("search")).clear();
		driver.findElement(By.className("search")).sendKeys("Hopping Hot Sauce");
		driver.findElement(By.id("search_button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Search Results Hopping Hot Sauce (1 - 15 of 18)", driver.findElement(By.cssSelector("#left_column > header > h1")).getText());
		
		//*** Click on the Navigation Menu (HotSauces)
		//*** HOT SAUCES home page should load.
		driver.findElement(By.cssSelector("#content > nav > ul > li:nth-child(2) > a")).click();
		Thread.sleep(2000);
		
		//*** Click "Buy Now" on available products. 
		//*** Ensure the products are added to cart with correct quantity
		Assert.assertEquals("1", driver.findElement(By.cssSelector("#products > li:nth-child(3) > div.productActions.productActions3 > div.add_to_cart > form > input[type='hidden']:nth-child(2)")).getAttribute("value"));
		String quantity = driver.findElement(By.cssSelector("#products > li:nth-child(3) > div.productActions.productActions3 > div.add_to_cart > form > input[type='hidden']:nth-child(2)")).getAttribute("value");
		driver.findElement(By.cssSelector("#products > li:nth-child(3) > div.productActions.productActions3 > div.add_to_cart > form > input.addToCart")).click();
		Thread.sleep(2000);
		
		//*** Click on  " Cart" button on top Right corner.
		//*** Validate selected products should be available in cart.
		driver.findElement(By.cssSelector("#cartLink")).click();
		Thread.sleep(2000);
		Assert.assertEquals(quantity, driver.findElement(By.name("quantity")).getAttribute("value"));
	}
}
