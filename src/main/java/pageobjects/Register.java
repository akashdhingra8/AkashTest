package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.utility;

public class Register extends utility {

	public Register (WebDriver rdriver)
	{
		this.driver= rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	// finding join now / Sign up button 
	@FindBy(xpath = "//*[contains(@title , 'Create an Account')]")
	public WebElement joinnow_button;
	
	// finding first name 
	@FindBy(xpath = "//*[@id= 'firstname']")
	public WebElement firstname;
	
	// finding last name 
	@FindBy(xpath = "//*[@id= 'lastname']")
	public WebElement lastname;
	
	// finding email address 
		@FindBy(xpath = "//*[@id= 'email_address']")
		public WebElement email;
		
		// finding password 
		@FindBy(xpath = "//*[@id= 'password']")
		public WebElement password;
		
		// finding confirm password 
		@FindBy(xpath = "//*[@id= 'confirmation']")
		public WebElement confirm_password;
		
		// finding sign up button 
		@FindBy(xpath= "//*[contains(@title , 'Sign Up')]")
		public WebElement signup_button;  
		
		
		public void Sign_Up() 
		{	
			System.out.println(driver);
			//login_obj.login_button.click();
			//login_obj.loginbutton_click();
			Fluentwait_by_visibility_of_element(joinnow_button);
			
			
			System.out.println("inside function sign up");
			joinnow_button.click();
			
			Fluentwait_by_visibility_of_element(firstname);
			
			firstname.clear();
			firstname.sendKeys("akash");
			System.out.println("firstname entered");
			
			lastname.clear();
			lastname.sendKeys("dhingra");
			System.out.println("lastname entered");
			
			email.clear();
			email.sendKeys("akash@yopmail.com");
			System.out.println("email entered");
			
			password.clear();
			password.sendKeys("chetu@123");
			System.out.println("password entered");
			
			confirm_password.clear();
			confirm_password.sendKeys("chetu@123");
			System.out.println("confirm password  entered");
			
			signup_button.click();
			System.out.println("button clicked ");
			
			
		}
		
		
		


}
