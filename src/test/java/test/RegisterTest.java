package test;

import org.testng.annotations.Test;

import utilities.utility;

public class RegisterTest extends utility {
	
	@Test(description = "creating new user by join now")
	public void TC_001()
	{
		enterApplicationURLIntoAddressBarOfBrowser(get_value_from_property_file("url"));
		System.out.println("url invoked");
		System.out.println(driver);
		login_obj.login_button.click();
		//Signup_into_Application();
		Register_obj.Sign_Up();
		
		
	}

}
