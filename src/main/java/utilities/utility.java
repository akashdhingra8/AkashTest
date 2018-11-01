package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageobjects.LoginPage;
import pageobjects.Login_using_excel;
import pageobjects.Register;


public class utility 
{
	// global variables
	public WebDriver driver; // driver 
	public Properties prop;         // properties file object
	public  LoginPage login_obj;     // object of LoginPage class
	public  Register Register_obj;   // object of register class
	public  Login_using_excel login_using_excel_obj;   // object of Login_using_excel class
	public XSSFWorkbook wb;
	public XSSFSheet sh;
	public XSSFRow row;
	public XSSFCell cell;
	public static Logger log =LogManager.getLogger(utility.class.getName());
	
	// function to get values from propety file 
	public String get_value_from_property_file(String key) 
	{
		prop= new Properties();
		try
		{
			FileInputStream fis=new FileInputStream("Resources\\resource.properties");
			prop.load(fis);
			fis.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		String str = prop.getProperty(key);
		return str;
	
	}
	
	// Loading browser --- by getting value from property file 	
	
	public WebDriver Load_Browser() throws IOException
		{
			
			String browserName = get_value_from_property_file("browser");
			if(browserName.equalsIgnoreCase("chrome"))
				{
					System.setProperty("webdriver.chrome.driver", "Drivers//chromedriver.exe");
					driver= new ChromeDriver();
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
				}
				
			else if (browserName.equalsIgnoreCase("firefox"))
				{
					System.setProperty("webdriver.gecko.driver", "Drivers//geckodriver.exe");
					driver= new FirefoxDriver();
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();
				}
			
			else if (browserName.equalsIgnoreCase("ie"))
				{
					System.setProperty("webdriver.ie.driver", "Drivers//IEDriverServer.exe");
					driver = new InternetExplorerDriver();
					driver.manage().deleteAllCookies();
					driver.manage().window().maximize();

				} 
			
			else if (browserName.equalsIgnoreCase("edge"))
				{
					System.setProperty("webdriver.edge.driver", "Drivers//MicrosoftWebDriver.exe");
					WebDriver driver = new EdgeDriver();
					driver.manage().window().maximize();
				} 
			
			else 
				{
					try
						{
							throw new Exception("Please provide correct browser/browser name");
						} 
					catch (Exception e) 
						{
							e.printStackTrace();
						}
				}

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return driver;
		}
	
	// initializing driver and passing value of driver to page object classes
	@BeforeMethod
	public void initialise_Driver() 
	{
		try
		{
			driver = Load_Browser();
			login_obj = new LoginPage(driver);
			Register_obj = new Register(driver);
			login_using_excel_obj = new Login_using_excel(driver);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	// quiting browser
	@AfterMethod
	public void TearDownBrowser()
	{
		driver.quit();
		//driver = null;
	}
	
	// METHOD TO LOGIN INTO APPLICATION.
			public void Login_Into_Applcation(String username, String password) {
				System.out.println("login fxn inside");
				login_obj.login(username, password);
			}
			
	// METHOD TO LOGIN INTO APPLICATION.
			public void Signup_into_Application() {
				System.out.println("signup fxn inside");
				//login_obj.loginbutton_click();
				login_obj.login_button.click();
				Register_obj.Sign_Up();
			}
				
	
	// taking failure screenshots
	public void getScreenshot(String result) throws IOException
		{
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("C://test//"+result+"screenshot.png"));
	
		}
	
	// METHOD TO PUT THE URL IN ADDRESS BAR OF BROWSER.
		public void enterApplicationURLIntoAddressBarOfBrowser(String url) {
			driver.get(url);
		}
		
	// fluent wait
	public void Fluentwait_by_visibility_of_element(WebElement element)
	{;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(20, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	// fluent wait
	public void Fluentwait_by_visibility_of_frame(WebElement element)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(10,TimeUnit.SECONDS)
				.pollingEvery(2,TimeUnit.SECONDS)
				.ignoring(NoSuchFrameException.class);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}
	
	// printing all the data from excel
	public void reading_excel_and_printing() 
	{	
		try
		{
			FileInputStream fis = new FileInputStream("Resources\\selenium_framework_data_file.xlsx");
			wb = new XSSFWorkbook(fis);
			// number of sheets se total sheets se pta chal jayega 
			for (int i = 0 ; i < wb.getNumberOfSheets() ; i++)
			{
				if(wb.getSheetName(i).equalsIgnoreCase("Sheet1"))
				{
					sh=wb.getSheetAt(i);
					// get last row num se total rows pta lag jayegi 
					// starts with 0 
					// count bhi starting 0 se hi aata hai 
					// for eg excel me 1-6 rows hai .... total is 6.... but get last row num se hme 5 aayega as starting from 0
					
					for (int j =0; j <= sh.getLastRowNum();j++)
					{
						row= sh.getRow(j);
						
						// get last cell value se total columns ka pta lag jayega
						// starts with 0
						// total normal hai 
						// if 2 columns in excel then 2  will displayed as result
						
						for (int k=0; k < row.getLastCellNum();k++)
						{
							cell = row.getCell(k);
							int type = cell.getCellType();
						
							if (type == 1)
							{
								System.out.print(cell.getStringCellValue() + "     ");
							}
							else
							{
								System.out.print(cell.getNumericCellValue() + "     ");
							}
							
						}
						System.out.println();
					}
					
					
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	// reading from excel from specified key value
	public String get_value_from_excel() 
	{
		
		return null;
	}
}
