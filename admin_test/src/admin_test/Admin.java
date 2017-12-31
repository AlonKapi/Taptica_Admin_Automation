package admin_test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Admin{
	protected WebDriver driver;
	
	public Admin(WebDriver driver) {
		this.driver = driver;
	}
	
	public String adminRun(Offer offer, String UserName, String passAdmin) {
		String offerId = null;
		try {
		driver.get("https://network.taptica.com/login.html");
		// login window
		WebElement logincheck = driver.findElement(By.name("Field"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement login = driver.findElement(By.name("username"));
		
		logincheck.click();
		login.sendKeys(UserName);
		password.sendKeys(passAdmin);
		password.submit();
		// transition
		Thread.sleep(4000);
		
		// navigate to blank offer page (after creating application manually)
		driver.navigate().to(offer.getAppURL()); // GUI
		
		// transition
		Thread.sleep(2000);
		driver.getCurrentUrl();
		
		//Find Element for step 1
		WebElement offerName = driver.findElement(By.id("offer_name"));
		Select className = new Select(driver.findElement(By.id("ClassType")));
		WebElement endDate = driver.findElement(By.id("to_timedate_1"));
		WebElement trackingLink = driver.findElement(By.id("tracking_url"));
		
		Select trackingSystemAdjust = new Select(driver.findElement(By.id("MobileAppTracking")));
		Select trackingSystemKochava = new Select(driver.findElement(By.id("MobileAppTracking")));
		Select trackingSystemappia = new Select(driver.findElement(By.id("MobileAppTracking")));
		Select trackingSystemappsflyer = new Select(driver.findElement(By.id("MobileAppTracking")));
		Select trackingSystemApsalar = new Select(driver.findElement(By.id("MobileAppTracking")));
		Select trackingSystemmeasuramentap = new Select(driver.findElement(By.id("MobileAppTracking")));
		Select trackingSystemapi_02 = new Select(driver.findElement(By.id("MobileAppTracking")));
		Select trackingSystemtlnk = new Select(driver.findElement(By.id("MobileAppTracking")));
		WebElement idfaBox = driver.findElement(By.id("idfa"));
		WebElement idfa_sha1 = driver.findElement(By.id("idfa_sha1"));
		WebElement idfa_md5 = driver.findElement(By.id("idfa_md5"));
		WebElement udid = driver.findElement(By.id("udid"));
		WebElement udid_sha1 = driver.findElement(By.id("udid_sha1"));
		WebElement udid_md5 = driver.findElement(By.id("udid_md5"));
		WebElement mac = driver.findElement(By.id("mac"));
		WebElement mac_sha1 = driver.findElement(By.id("mac_sha1"));
		WebElement mac_md5 = driver.findElement(By.id("mac_md5"));
		WebElement imei = driver.findElement(By.id("imei"));
		WebElement imei_sha1 = driver.findElement(By.id("imei_sha1"));
		WebElement imei_md5 = driver.findElement(By.id("imei_md5"));
		WebElement android_id = driver.findElement(By.id("android_id"));
		WebElement android_id_sha1 = driver.findElement(By.id("android_id_sha1"));
		WebElement android_id_md5 = driver.findElement(By.id("android_id_md5"));
		WebElement advertising_id = driver.findElement(By.id("advertising_id"));
		WebElement advertising_id_sha1 = driver.findElement(By.id("advertising_id_sha1"));
		WebElement advertising_id_md5 = driver.findElement(By.id("advertising_id_md5"));
		WebElement revenue = driver.findElement(By.id("revenue"));
		WebElement payout = driver.findElement(By.id("payout"));
		WebElement countryClick = driver.findElement(By.xpath("//*[@id='countriesSmartselectWrapper']/div/button"));
		WebElement saveClick = driver.findElement(By.xpath("//*[@id=\"tab1_save\"]"));
	
		// offer name
		offerName.clear();
		offerName.sendKeys(offer.getOfferName());
		// class
		className.selectByVisibleText(offer.getOfferClass());
		// end date
		endDate.clear();
		endDate.sendKeys(offer.getOfferEndDate());
		endDate.sendKeys(Keys.ENTER);
		// tracking link
		String tracking = offer.getOfferTrackingLink();
		trackingLink.sendKeys(tracking);
		
		// tracking system - Adjust *** 
		if (tracking.contains("adjust")) {
			trackingSystemAdjust.selectByVisibleText("Adjust");
		}
		//	tracking system - Kochava***
		if (tracking.contains("kochava")) {
			trackingSystemKochava.selectByVisibleText("Kochava");
		}
		//		tracking system - Appia***
		if (tracking.contains("appia")) {
			trackingSystemappia.selectByVisibleText("Appia");
		}
		//		tracking system - Appsflyer***
		if (tracking.contains("appsflyer")) {
			trackingSystemappsflyer.selectByVisibleText("Appsflyer");
		}
		//		tracking system - Apsalar***
		if (tracking.contains("apsalar")) {
			trackingSystemApsalar.selectByVisibleText("Apsalar");
		}
		//		tracking system - TUNE***
		if (tracking.contains("measuramentap")) {
			trackingSystemmeasuramentap.selectByVisibleText("TUNE");
		}
		if (tracking.contains("api-02")) {
			trackingSystemapi_02.selectByVisibleText("TUNE");
		}
		if (tracking.contains("tlnk.io")) {
			trackingSystemtlnk.selectByVisibleText("TUNE");
		}
		// tracking parameters - IDFA *** 
		if (tracking.contains("{tt_idfa}")) {
			idfaBox.click();
		}
		if (tracking.contains("{tt_idfa_sha1}")) {
			idfa_sha1.click();
		}
		if (tracking.contains("{tt_idfa_md5}")) {
			idfa_md5.click();
		}
		// tracking parameters - UDID ***
		if (tracking.contains("{tt_udid}")) {
			udid.click();
		}
		if (tracking.contains("{tt_udid_sha1}")) {
			udid_sha1.click();
		}
		if (tracking.contains("{tt_udid_md5}")) {
			udid_md5.click();
		}
		// tracking parameters - MAC ***
		if (tracking.contains("{tt_mac}")) {
			mac.click();
		}
		if (tracking.contains("{tt_mac_sha1}")) {
			mac_sha1.click();
		}
		if (tracking.contains("{tt_mac_md5}")) {
			mac_md5.click();
		}
		// tracking parameters - IMEI ***
		if (tracking.contains("{tt_imei}")) {
			imei.click();
		}
		if (tracking.contains("{tt_imei_sha1}")) {
			imei_sha1.click();
		}
		if (tracking.contains("{tt_imei_md5}")) {
			imei_md5.click();
		}
		// tracking parameters - Android_ID ***
		if (tracking.contains("{tt_android_id}")) {
			android_id.click();
		}
		if (tracking.contains("{tt_android_id_sha1}")) {
			android_id_sha1.click();
		}
		if (tracking.contains("{tt_android_id_md5}")) {
			android_id_md5.click();
		}
		// tracking parameters - Google Advertising_ID (GAID) ***
		if (tracking.contains("{tt_advertising_id}")) {
			advertising_id.click();
		}
		if (tracking.contains("{tt_advertising_id_sha1}")) {
			advertising_id_sha1.click();
		}
		if (tracking.contains("{tt_advertising_id_md5}")) {
			advertising_id_md5.click();
		}
		// Budget
		revenue.sendKeys(offer.getOfferRevenue());
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
				startDate.sendKeys(temp);
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
		}
		// Country
		countryClick.click();
		WebElement country;
		for (int i=0; i<offer.getOfferCountries().size(); i++) {
			country = driver.findElement(By.cssSelector("#countriesSmartselectWrapper li[data-value="+offer.getOfferCountries().get(i)+"]"));
			country.click();
			Thread.sleep(1500);
		}
		// Platforms
		WebElement platformClick = driver.findElement(By.xpath("//*[@id=\"platformsSmartselectWrapper\"]/div/button"));
		platformClick.click();
		WebElement platform;
		for (int i=0; i<offer.getOfferPlatforms().size(); i++) {
			platform = driver.findElement(By.cssSelector("#platformsSmartselectWrapper li[data-value="+offer.getOfferPlatforms().get(i)+"]"));
			platform.click();
		}
		Thread.sleep(4000);
		// Save offer
		saveClick.click();
		Thread.sleep(8000);
		// step 5
		//Find Element
		WebElement step5Click = driver.findElement(By.xpath("//*[@id=\"offrer_manage_tabs\"]/li[5]"));
		step5Click.click();
		Thread.sleep(3000);
		WebElement Requirements = driver.findElement(By.id("offer_requirements"));
		WebElement KPI = driver.findElement(By.id("offer_kpi"));
		// transition
		Thread.sleep(1000);
		// requirements send info
		Requirements.sendKeys(offer.getOfferRequirements());
		//KPI send info
		KPI.sendKeys(offer.getOfferKPI());
		Thread.sleep(1000);	
		// save
		WebElement SaveStep5 = driver.findElement(By.xpath("//*[@id=\"tab5_save\"]"));
		SaveStep5.click();
		String url = driver.getCurrentUrl();
		String[] url2=url.split("=");
		System.out.println(url2[0]);
		System.out.println(url2[1]);
		System.out.println("Offer opened successfully! Link = " + url);
		
		offerId = url2[1];
		
		}catch(NoSuchElementException | InterruptedException e) {
			System.out.println(e.getMessage());
		}
		finally {
			//driver.close();
		}
		return offerId;
	}
}
