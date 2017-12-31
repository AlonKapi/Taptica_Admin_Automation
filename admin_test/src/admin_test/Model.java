package admin_test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import org.openqa.selenium.chrome.ChromeDriver;
import admin_bulk.*;

public class Model extends Observable{
	private ExcelData excel;
	private Admin admin;
	private ArrayList<Offer> offersList;
	public ArrayList<BulkUpdateoffers> bulkList;
	ArrayList<advDATAinfo> dataperAdv;
	Map<String, ArrayList<BulkUpdateoffers>> offersPerAdvId;
	
	public Model() {
	}
	
	public ArrayList<Offer> getOffers() {
		return this.offersList;
	}
	
	public void setOffers(ArrayList<Offer> offers) {
		this.offersList = offers;
	}
	
	public Map<String, ArrayList<BulkUpdateoffers>> getOffersPerAdvId() {
		return offersPerAdvId;
	}

	public void setOffersPerAdvId(Map<String, ArrayList<BulkUpdateoffers>> offersPerAdvId) {
		this.offersPerAdvId = offersPerAdvId;
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
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
		String UserName = null;
		String passAdmin = null;
		File o = new File(".\\AdminLogin.txt");
		Crendentials ob = null;
		
		if (!o.exists()) throw new IOException();
		fis = new FileInputStream(o);
		ois = new ObjectInputStream(fis);
		ob = (Crendentials)ois.readObject();
		UserName = ob.getUserName();
		passAdmin = ob.getLogin();
		
		admin = new Admin(new ChromeDriver());
		
		for (int i=0 ; i<offersList.size(); i++) {	// running each offer creation
			String offerid = admin.adminRun(offersList.get(i),UserName,passAdmin);
			offersList.get(i).setOfferID(offerid);
		}
		
		setChanged();
		notifyObservers(new String("FINISHED_ADMIN"));
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void setupBulk(String filepath) {
		ExcelBulkOffers excel = new ExcelBulkOffers(filepath);
		InfoDataExcel exceldata = new InfoDataExcel(".\\e.xlsx");
		
		try {
			dataperAdv = exceldata.getDataInfoFromExcel();
			offersPerAdvId = excel.getOffersPerAdvId();
			bulkList = excel.getBulkOfferFromExcel();
			setChanged();
			notifyObservers(new String("BULK_OK"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void runBulk() {
		advBULK advb = new advBULK(new ChromeDriver());
		try {
			advb.bulkRun(offersPerAdvId,dataperAdv);
			setChanged();
			notifyObservers(new String("FINISHED_BULK"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
