package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.utility;

public class Login_using_excel extends utility {
	public Login_using_excel(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 // login button 
	@FindBy(xpath = "//*[@class='site-header__account']")
	public WebElement login_button;
	
	// email field
	@FindBy(xpath = "//input[@id='CustomerEmail']")
	public WebElement Email;
	
	// Password field 
	@FindBy(xpath = "//input[@id='CustomerPassword']")
	public WebElement password;
	
	// sign in button 
	@FindBy(xpath = "//input[@value='Sign In']")
	public WebElement Signin;
	
	public void login() 
	{ 
		try
		{
			login_button.click();
			Fluentwait_by_visibility_of_element(Email);
			Email.sendKeys("akash");
			password.sendKeys("dhingra");
			Signin.click();
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
