package BroadleafcommerceWeb.Automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DB2_Login_Logout extends BaseTest 
{
	@Test
	public void DB2_login_logout() throws InterruptedException
	{
		//*** Click on LOGIN link.
		driver.findElement(By.linkText("Login")).click();
		
		//*** Login information should be loaded
		Login();
		
		//*** Validate WELCOME with the entered First Name page is show and Logout link is displayed.
		Assert.assertEquals("Welcome", driver.findElement(By.cssSelector("#cart_info > span > span")).getText());
		Assert.assertEquals("Smith", driver.findElement(By.cssSelector("#cart_info > span > a")).getText());
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		
		//*** Click on Logout
		//*** Ensure the logout was successful and Validate LOGIN / REGISTER link is displayed.
		Logout();
	}

}