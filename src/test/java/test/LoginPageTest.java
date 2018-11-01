package test;



import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.utility;

public class LoginPageTest extends utility {
	
	@Test(groups = "Login", description = "LOGIN TO APPLICATION WITH VALID CREDENTIALS" )
	public void TC_001() throws InterruptedException
	{
		enterApplicationURLIntoAddressBarOfBrowser(get_value_from_property_file("url"));
		log.info("url is invoked ");
		
		Login_Into_Applcation(get_value_from_property_file("username") , get_value_from_property_file("password"));
		
		//Thread.sleep(10000);
		Fluentwait_by_visibility_of_element(login_obj.signout_button);
		System.out.println("login successfull");
		
		System.out.println(driver.getCurrentUrl());	
		
		String expected= (get_value_from_property_file("url"));
		String actual = driver.getCurrentUrl();
		Assert.assertNotEquals(actual, expected);
		System.out.println("Checked 1st time");
		Assert.assertNotEquals(get_value_from_property_file("url"), driver.getCurrentUrl());
		System.out.println("Checked 2nd time");
	}
}
