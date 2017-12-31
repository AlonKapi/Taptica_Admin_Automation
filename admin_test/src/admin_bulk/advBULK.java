package admin_bulk;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Map;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class advBULK {
protected WebDriver driver;
	
	public advBULK(WebDriver driver) {
		this.driver = driver;
	}
	public void bulkRun(Map ob,ArrayList<advDATAinfo> dataperAdv) throws InterruptedException
	{
		Map <String , ArrayList> offersperadv = ob;//setting the map collation that contain Adv as a key and offer as a List.
		ArrayList info_list =dataperAdv;
		driver.get("http://ocs.taptica.com/start.php");//Get URl
		WebElement User =driver.findElement(By.xpath("//*[@id=\"name\"]"));//find user
		WebElement pass =driver.findElement(By.xpath("//*[@id=\"password\"]"));//find password
		WebElement login =driver.findElement(By.xpath("//*[@id=\"login\"]/form/input[3]"));//find login
		User.sendKeys("shuli");//send key
		pass.sendKeys("shuli");//send key
		login.click();//login click
		
		Robot r;//Press ENTER on dialog box Using a ROBOT  
		try {
			r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(2000);
		driver.getCurrentUrl();//Get current url
		
		for(String obs : offersperadv.keySet())
		{
			WebElement Text_Area = driver.findElement(By.xpath("/html/body/form/div[1]/textarea"));//find a text area place to put all the offers Ids
			WebElement Sumbit =	driver.findElement(By.xpath("/html/body/form/div[2]/input[102]"));//find a sumbit click option
			for(int i=0;i<dataperAdv.size();i++)
			{
				if (obs.equals(dataperAdv.get(i).getAdvID()))
				{
					Thread.sleep(1000);
					String temp = dataperAdv.get(i).getAdvXpath();
					Thread.sleep(1000);
					WebElement tempfind= driver.findElement(By.xpath(temp));
					Thread.sleep(1000);
					tempfind.click();
					for(int x = 0 ;x < offersperadv.get(obs).size();x++)
					{
						
						Text_Area.sendKeys(offersperadv.get(obs).get(x).toString());
						Text_Area.sendKeys(Keys.RETURN);
					}
					Thread.sleep(1000);
					Sumbit.submit();
					Thread.sleep(1000);
					try {
						r = new Robot();
						r.keyPress(KeyEvent.VK_ENTER);
						r.keyRelease(KeyEvent.VK_ENTER);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Thread.sleep(1000);
				}
				
			}
			
		}
	}
}