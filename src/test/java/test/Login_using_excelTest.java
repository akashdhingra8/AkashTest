package test;

import org.testng.annotations.Test;

import utilities.utility;

public class Login_using_excelTest extends utility {
	
	@Test
	public void tc_001() throws InterruptedException
	{
		enterApplicationURLIntoAddressBarOfBrowser(get_value_from_property_file("url"));
		login_using_excel_obj.login();
	}

}
