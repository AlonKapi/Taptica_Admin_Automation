package admin_test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import org.openqa.selenium.chrome.ChromeDriver;

public class Model extends Observable{
	private ExcelData excel;
	private Admin admin;
	private ArrayList<Offer> offersList;
	private String[] credentials;
	
	public Model() {
		credentials = new String[2];
	}
	
	public ArrayList<Offer> getOffers() {
		return this.offersList;
	}
	
	public void setupExcel(String filepath) {
		try {
			excel = new ExcelData();
			excel.setFilePath(filepath);		// setting path and opening the excel file
			offersList = excel.getOffersData();	// getting the data and creating offers array
			setChanged();
			notifyObservers(new String("OK"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runAdmin() {
		admin = new Admin(new ChromeDriver());
		admin.setCredentials(credentials);
		
		for (int i=0 ; i<offersList.size(); i++) {	// running each offer creation
			admin.adminRun(offersList.get(i));
		}
		
		setChanged();
		notifyObservers(new String("FINISHED"));
	}
	
	public void setCredentials(String[] cred) {
		this.credentials = cred;
	}
}
