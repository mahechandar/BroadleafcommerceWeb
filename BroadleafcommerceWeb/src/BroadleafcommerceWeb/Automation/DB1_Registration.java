package BroadleafcommerceWeb.Automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DB1_Registration extends BaseTest 
{
	@Test
	public void DB1_registration() throws InterruptedException
	{
		//*** Click REGISTER link in the home page
		driver.findElement(By.linkText("Register")).click();
		
		//*** Validate "REGISTER OR LOG IN OR FORGOT PASSWORD" links page is displayed
		//*** Enter Email,First Name, Last Name, Password, confirm Password with correct input data
		//*** Validate WELCOME with the entered First Name page is show and Logout link is displayed.
		Register();
		
		//*** Validate WELCOME with the entered First Name page is show and Logout link is displayed.
		Assert.assertEquals("Welcome", driver.findElement(By.cssSelector("#cart_info > span > span")).getText());
		Assert.assertEquals("Xavier", driver.findElement(By.cssSelector("#cart_info > span > a")).getText());
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
		//*** Click on Logout
		//*** Ensure the logout was successful and Validate LOGIN / REGISTER link is displayed.
		Logout();
	}

}
