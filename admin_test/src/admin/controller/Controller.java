package admin.controller;

import java.util.Observable;
import java.util.Observer;
import admin.view.CredentialsGui;
import admin.view.GUI;
import admin_test.Model;

public class Controller implements Observer{
	private Model model;
	private CredentialsGui credGui;
	private GUI gui;
	
	public Controller(Model model, CredentialsGui credGui, GUI gui) {
		this.model = model;
		this.credGui = credGui;
		this.gui = gui;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		String temp;
		if (o instanceof CredentialsGui) { // received the credentials
			
			// update the credentials in the model
			String[] cred = (String[]) arg;
			model.setCredentials(cred);
			
			// no longer need the credentials window
			credGui.hideWindow();
			
			// start the main gui window
			new Thread(gui).start();
		}
		
		else if (o instanceof GUI) {	// received from the main gui
			temp = (String) arg;
			
			// received path, setup and start excel
			model.setupExcel(temp);
		}
		
		else if (o instanceof Model) {
			temp = (String) arg;
			
			// finished setting up the excel, sends the offers list to the gui to show
			if (temp.equals("OK")) {
				gui.setOffers(model.getOffers());
				gui.offersTable();
			}
			// completed opening all the offers
			else if (temp.equals("FINISHED")){
				
			}
		}
	}
}
