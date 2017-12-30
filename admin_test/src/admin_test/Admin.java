package admin_test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Admin{
	protected WebDriver driver;
	private String[] credentials;
	
	public Admin(WebDriver driver) {
		this.driver = driver;
		credentials = new String[2];
	}
	
	public void setCredentials(String[] cred) {
		this.credentials = cred;
	}

	public void adminRun(Offer offer) {
		try {
		driver.get("https://stagingtamp.taptica.com"); // GUI
		
		// login window
		WebElement logincheck = driver.findElement(By.name("Field"));
		logincheck.click();
		
		WebElement login = driver.findElement(By.name("username"));
		login.sendKeys(credentials[0]);
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(credentials[1]);
		
		password.submit();
		
		// transition
		Thread.sleep(4000);
		
		// navigate to blank offer page (after creating application manually)
		driver.navigate().to("https://stagingtamp.taptica.com/advertisers_new_offers_manage.html?advertisers_application_id=8765"); // GUI
		
		// transition
		Thread.sleep(2000);
		
		driver.getCurrentUrl();
		
		// offer name
		WebElement offerName = driver.findElement(By.id("offer_name"));
		offerName.clear();
		offerName.sendKeys(offer.getOfferName());
		
		// class
		Select className = new Select(driver.findElement(By.id("ClassType")));
		className.selectByVisibleText(offer.getOfferClass());
		
		// end date
		WebElement endDate = driver.findElement(By.id("to_timedate_1"));
		endDate.clear();
		endDate.sendKeys(offer.getOfferEndDate());
		endDate.sendKeys(Keys.ENTER);
		
		// tracking link
		String tracking = offer.getOfferTrackingLink();
		WebElement trackingLink = driver.findElement(By.id("tracking_url"));
		trackingLink.sendKeys(tracking);
		
		// tracking system - need to add more! *** 
		if (tracking.contains("adjust")) {
			Select trackingSystem = new Select(driver.findElement(By.id("MobileAppTracking")));
			trackingSystem.selectByVisibleText("Adjust");
		}
		
		// tracking parameters - need to add more! *** 
		if (tracking.contains("{tt_idfa}")) {
			WebElement idfaBox = driver.findElement(By.id("idfa"));
			idfaBox.click();
		}
		
		// Budget
		WebElement revenue = driver.findElement(By.id("revenue"));
		revenue.sendKeys(offer.getOfferRevenue());
		
		WebElement payout = driver.findElement(By.id("payout"));
		payout.sendKeys(offer.getOfferPayout());
		
		String budget = offer.getOfferBudgetType();
		String dailyCap = offer.getOfferDailyCap();
		String budgetTotal = offer.getOfferBudgetLimit();
		
		if (budgetTotal.equals("-1"))
			budget = "Unlimited";
		
		switch(budget) {
		case "Unlimited":
			WebElement cap = driver.findElement(By.id("daily_cup"));
			cap.sendKeys(dailyCap);
			break;
			
		case "Elapsed":
			Select budgetType = new Select(driver.findElement(By.id("OfferTotalBudgetType")));
			budgetType.selectByVisibleText("Elapsed");
			
			WebElement cap2 = driver.findElement(By.id("daily_cup"));
			cap2.sendKeys(dailyCap);
			
			WebElement startDate = driver.findElement(By.id("budget_start_date"));
			startDate.clear();
			String temp = offer.getOfferBudgetStartDate();
			if (temp != "") {
				startDate.sendKeys(temp);
			}
			else {
				startDate.sendKeys("10/12/2017");
			}
			
			WebElement currBudget = driver.findElement(By.id("current_budget"));
			currBudget.clear();
			currBudget.sendKeys(budgetTotal);
			break;
			
		case "Monthly":
			Select budgetType2 = new Select(driver.findElement(By.id("OfferTotalBudgetType")));
			budgetType2.selectByVisibleText("Monthly");
			
			WebElement cap3 = driver.findElement(By.id("daily_cup"));
			cap3.sendKeys(dailyCap);
			
			WebElement currBudget2 = driver.findElement(By.id("current_budget"));
			currBudget2.clear();
			currBudget2.sendKeys(budgetTotal);
			break;
			
		default:
			throw new NoSuchElementException("Error with budget type!");
		}
		
		// Country
		WebElement countryClick = driver.findElement(By.xpath("//*[@id='countriesSmartselectWrapper']/div/button"));
		countryClick.click();
		
		WebElement country;
		for (int i=0; i<offer.getOfferCountries().size(); i++) {
			country = driver.findElement(By.cssSelector("#countriesSmartselectWrapper li[data-value="+offer.getOfferCountries().get(i)+"]"));
			country.click();
		}
		
		// Platforms
		WebElement platformClick = driver.findElement(By.xpath("//*[@id=\"platformsSmartselectWrapper\"]/div/button"));
		platformClick.click();
		
		WebElement platform;
		for (int i=0; i<offer.getOfferPlatforms().size(); i++) {
			platform = driver.findElement(By.cssSelector("#platformsSmartselectWrapper li[data-value="+offer.getOfferPlatforms().get(i)+"]"));
			platform.click();
		}
		
		Thread.sleep(1000);
		
		// Save offer
		WebElement saveClick = driver.findElement(By.id("tab1_save"));
		saveClick.click();
		
		Thread.sleep(8000);
		
		// step 5
		WebElement step5Click = driver.findElement(By.xpath("//*[@id=\"offrer_manage_tabs\"]/li[5]"));
		step5Click.click();
		
		// transition
		Thread.sleep(2000);
		
		// requirements
		WebElement Requirements = driver.findElement(By.id("offer_requirements"));
		Requirements.sendKeys(offer.getOfferRequirements());
		
		//KPI
		WebElement KPI = driver.findElement(By.id("offer_kpi"));
		KPI.sendKeys(offer.getOfferKPI());
		
		Thread.sleep(1000);
		
		// save
		WebElement SaveStep5 = driver.findElement(By.xpath("//*[@id=\"tab5_save\"]"));
		SaveStep5.click();
		
		String url = driver.getCurrentUrl();
		System.out.println("Offer opened successfully! Link = " + url);
		
		}catch(NoSuchElementException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
		finally {
			//driver.close();
		}
	}
}
