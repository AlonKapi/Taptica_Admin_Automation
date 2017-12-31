package admin_test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	protected String filePath;
	protected FileInputStream in = null;
	protected ArrayList<Offer> offersArr;
	
	public ExcelData(){
		this.offersArr = new ArrayList<Offer>();
	}
	
	public void setFilePath(String filepath) {
		this.filePath = filepath;
		
		try {
			if (in != null)
				in.close();
			in = new FileInputStream(filePath);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Offer> getOffersData() throws IOException {
		DataFormatter formatter = new DataFormatter();
		
		Workbook workbook = new XSSFWorkbook(in);
		Sheet sheet = workbook.getSheetAt(0); 
		Row headerRow = sheet.getRow(0);	// used to determine the current place in the workbook
		
		
		for (int i=1; i<sheet.getLastRowNum()+1; i++) {	// iterates through all the rows minus the header, each row is an offer
			Offer offer = new Offer();
			Row dataRow = sheet.getRow(i);
			
			for (int j=0; j<headerRow.getLastCellNum(); j++) {	// iterates through all the cells, saving the relevant data
				Cell headerCell = headerRow.getCell(j);
				Cell dataCell = dataRow.getCell(j);
				
				String header = formatter.formatCellValue(headerCell);
				String temp = formatter.formatCellValue(dataCell);
				
				switch(header) {
				case "NAME":
					offer.setOfferName(temp);
					break;
				case "DEVICES__C":
					String deviceTemp = temp;
					String[] deviceArr;
					if(deviceTemp.contains(";"))
						deviceArr = deviceTemp.split(";");
					else {
						deviceArr = new String[1];
						deviceArr[0] = deviceTemp;
					}
					
					for (String s : deviceArr) {
						switch(s) {
						case "iPhone":
							s = "IPhone";
							break;
						case "iPad":
							s = "IPad";
							break;
						case "iPod":
							s = "IPod";
							break;
						case "Android Smartphone":
							s = "Android";
							break;
						case "Android Tablet":
							s = "AndroidTablet";
							break;
						}
						
						offer.getOfferPlatforms().add(s);
					}
					break;
				case "DAILY_CAP__C":
					offer.setOfferDailyCap(temp);
					break;
				case "BUDGET_LIMIT__C":
					offer.setOfferBudgetLimit(temp);
					break;
				case "PAYOUT__C":
					offer.setOfferPayout(temp);
					break;
				case "LINKS__C":
					offer.setOfferTrackingLink(temp);
					break;
				case "REVENUE__C":
					offer.setOfferRevenue(temp);
					break;
				case "OFFER_CLASS__C":
					offer.setOfferClass(temp);
					break;
				case "COUNTRIES_CODE__C":
					String OfferCountriesTemp = temp;
					String[] OfferCountries;
					
					if(OfferCountriesTemp.contains(";"))
						OfferCountries = OfferCountriesTemp.split(";");
					
					else {
						OfferCountries = new String[1];
						OfferCountries[0] = OfferCountriesTemp;
					}
					
					for (String f : OfferCountries)
						offer.getOfferCountries().add(f);
					break;
				case "REGION__C": // to add later
					break;
				case "CITY__C": // to add later
					break;
				case "END_DATE_NEW__C":
					String temp2 = temp.substring(0, 10);
					String[] dateArr = temp2.split("-");
					String dateReal = dateArr[1]+"/"+dateArr[2]+"/"+dateArr[0];
					temp2 = temp.substring(11, 16);
					dateReal = dateReal + " " + temp2;
					offer.setOfferEndDate(dateReal);
					break;
				case "START_DATE_NEW__C": // need to use this?
					break;
				case "ADVERTISER_IMPRESSION_LINK__C": // need to use this?
					break;
				case "REQUIREMENTS__C":
					offer.setOfferRequirements(temp);
					break;
				case "KPI__C":
					offer.setOfferKPI(temp);
					break;
				case "BUDGET_TYPE__C":
					offer.setOfferBudgetType(temp);
					break;
				case "BUDGET_START_DATE__C":
					offer.setOfferBudgetStartDate(temp);
					break;
				case "COMMENTS__C":
					offer.setComments(temp);
					break;	
				}
			}
			
			offersArr.add(offer);
		}
		
		workbook.close();
		return offersArr;
	}
}
