package test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import utilities.utility;

public class Tillcart extends utility{
	
		@Test(description = "Adding item to cart and handling it through Paypal")
		public void buying()
		{
			try
			{
				enterApplicationURLIntoAddressBarOfBrowser(get_value_from_property_file("url"));
				
				driver.findElement(By.xpath("//*[@id=\"shopify-section-1523002442207\"]/div/div/div/form/input[2]")).sendKeys("baby birds");
				Thread .sleep(5000);
				
				driver.findElement(By.xpath("//*[@id=\"shopify-section-1523002442207\"]/div/div/div/form/button")).click();
				Thread .sleep(5000);
				
				Actions a = new Actions(driver);
				
				// creating list for images
				List<WebElement> pics = driver.findElements(By.xpath("//*[@class='snize-product']"));
				for (int i = 0 ; i < pics.size(); i++)
				{
					// hovering on the images one by one 
					// getting text 
					a.moveToElement(pics.get(i)).build().perform();;
					String str = pics.get(i).getText();
					// matching text with the desire one 
					if (str.contains(get_value_from_property_file("birdname")))
					{
						//pics.get(i).click();
						// clicking on quick view button 
						driver.findElement(By.xpath("//*[@id=\"snize-product-552074543132\"]/a/div/span/button")).click();
						break;
					}
					Thread .sleep(5000);
				}
				// clicking on view full details on quick detail screen 
				Thread .sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"snize-modal-product-quick-view\"]/div/div[2]/a[2]")).click();
				Thread .sleep(5000);
				
				// Clicking on Add to cart
				driver.findElement(By.xpath("//*[@id=\"AddToCart-product-template\"]")).click();
				
				// process to click on paypal
				
				JavascriptExecutor jsx = (JavascriptExecutor)driver;
				jsx.executeScript("window.scrollBy(0,500)");
				Thread.sleep(10000);
				driver.switchTo().frame(driver.findElement(By.xpath("//*[@class = 'xcomponent-component-frame xcomponent-visible']")));
				driver.findElement(By.xpath("//*[@id='paypal-animation-container']")).click();
				
				// Handling windows
				// paypal windoww
				
				Set<String> ids = driver.getWindowHandles();
				Iterator<String> id = ids.iterator();
				String parent = id.next();
				String child = id.next();
				driver.switchTo().window(child);
				System.out.println(driver.getTitle());
			}
				
			catch(Exception e )
			{
				System.out.println(e);
			}
		
		}
}


/*int icount = (driver.findElements(By.tagName("iframe")).size());
System.out.println("frames"+icount);	
for ( int i = 0 ; i < icount;i++)
{	
	System.out.println(i);
	driver.switchTo().frame(i);
	int pcount = driver.findElements(By.xpath("//*[@id='paypal-animation-container']")).size();
	System.out.println(pcount);
	if (pcount >= 1)
	{
		driver.findElement(By.xpath("//*[@id='paypal-animation-container']")).click();
		break;
	}
	
}*/

