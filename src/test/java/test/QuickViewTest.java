package test;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import utilities.utility;

public class QuickViewTest extends utility {

	
	@Test
	public void QuickView() throws InterruptedException
	{
		driver.get("https://www.wallmonkeys.com/pages/search-results-page?q=baby+bird");
		
		try {
			List<WebElement> pics = driver.findElements(By.xpath("//*[@class='snize-product']"));
			System.out.println(pics.size());
			Actions a = new Actions(driver);
			
				for (int i = 0 ; i < pics.size(); i++)
				{
					a.moveToElement(pics.get(i)).build().perform();;
					String str = pics.get(i).getText();
				//System.out.println(str);
				String [] arr = str.split("\n");
				// different ways to get the intended test case
				// test case is to go to every image and click on the image whose name is given in resource file
				
				/*if ("Baby Bird Pelican Wall Decal" .equalsIgnoreCase(arr[0]))
				{
					System.out.println(i);
					pics.get(i).click();
					break;
				}
				if (str.contains("Baby Bird Pelican Wall Decal"))
				{
					pics.get(i).click();
					break;
				}
				if (str.contains(get_value_from_property_file("birdname")))
				{
					pics.get(i).click();
					break;
				}*/
				if(arr[0].equalsIgnoreCase(get_value_from_property_file("birdname")))
				{
					pics.get(i).click();
					break;
				}
			}
		}
		catch(Exception e) 
		{
			System.out.println(e.getStackTrace());
		}
		
		/*a.moveToElement(driver.findElement(By.xpath("//*[@id=\"snize-product-552074543132\"]/a/div/div/span/img[2]"))).build().perform();
		driver.findElement(By.xpath("//*[@id=\"snize-product-552074543132\"]/a/div/span/button")).click();
		//view full detail pe click 
		//driver.findElement(By.xpath("//*[@id=\"snize-modal-product-quick-view\"]/div/div[2]/a[2]")).click();
	//////////////////////////////////////////////////////////////////////////////////////////	
		
		
		Select s  = new Select(driver.findElement(By.xpath("//*[@id='snize-option-selector-1']")));
	List<WebElement> l = s.getOptions();
	int count  = l.size();
	for (int i = 0 ; i < count ; i ++)
	{
		System.out.println(l.get(i).getText());
	}*/
	
	
		Thread.sleep(10000);
		
		
	}
}
