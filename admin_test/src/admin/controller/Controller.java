package admin.controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import admin.view.MainWindow;
import admin_test.Model;
import admin_test.Offer;

public class Controller implements Observer{
	private Model model;
	private MainWindow gui;
	
	public Controller(Model model, MainWindow gui) {
		this.model = model;
		this.gui = gui;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		String temp;
		if (o instanceof MainWindow) {	// received from the main GUI
			if (arg instanceof String) {
				temp = (String) arg;
				
				if (temp.contains(" ")) {	// must be a file path
					String[] strArr = temp.split(" ");
					
					if (strArr[0].equals("BULK")) {	// update the bulk file path
						model.setupBulk(strArr[1]);
					}else {							// update the admin file path
						model.setupExcel(strArr[1]);
					}
				}
				
				else {
					if (temp.equals("RUN")) {
						model.runAdmin();
					}
					else if (temp.equals("RUN_BULK")) {
						model.runBulk();
					}
				}
			}
			else {
				model.setOffers((ArrayList<Offer>) arg);
			}
		}
		
		else if (o instanceof Model) {
			temp = (String) arg;
			
			// finished setting up the excel, sends the offers list to the gui to show
			if (temp.equals("OK")) {
				gui.setOffers(model.getOffers());
				gui.fillTable();
			}
			// completed opening all the offers
			else if (temp.equals("FINISHED_ADMIN")){
				gui.setOffers(model.getOffers());
				gui.changeTableFinished();
				gui.finishedMsg("Finished opening offers!\nPlease see table for offer ids.");
			}
			else if (temp.equals("FINISHED_BULK")) {
				gui.finishedMsg("Finished updating bulk!");
			}
			
			else if (temp.equals("BULK_OK")) {
				gui.setOffersPerAdvId(model.getOffersPerAdvId());
				gui.fillBulkTable();
			}
		}
	}
}
