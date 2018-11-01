package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.utility;

public class LoginPage extends utility {
	
	// constructor
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// finding web elements for object repository
	@FindBy(xpath="//*[@title='Log In']")
	public WebElement login_button;
	
	// finding username
	@FindBy(xpath = "//*[@id='email']")
	public WebElement email_field;
	
	// finding password field
	@FindBy(xpath = "//*[@id = 'pass']")
	public WebElement password_field;
	
	// finding Sign in button 
	@FindBy(xpath = "//*[@id= 'send2']")
	public WebElement signin_button;
	
	// finding sign out button
	@FindBy(xpath = "//*[@title = 'Log Out']")
	public WebElement signout_button;
	
	
	
	// login into application 
		public void login(String username, String password )
		{
			login_button.click();
			System.out.println("login button clicked");
			
			Fluentwait_by_visibility_of_element(email_field);
			//log.info("waiting for the page to load" );
	
			email_field.clear();
			email_field.sendKeys(username);
			log.info("username / email entered");
			System.out.println("email entered");
			
			password_field.clear();
			password_field.sendKeys(password);
			log.info("password entered");
			System.out.println("password entered");
			
			signin_button.click();
			
			System.out.println("signin clicked");
			log.info("clicked on login button ");
			
			
		}
	public void loginbutton_click()
	{
		login_button.click();
	}
	
}
