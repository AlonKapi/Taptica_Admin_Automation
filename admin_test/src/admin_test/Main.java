package admin_test;

import admin.controller.Controller;
import admin.view.CredentialsGui;
import admin.view.GUI;

public class Main{
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C://chromedriver/chromedriver.exe"); // getting driver
		//String filePath = "C://offerslist.xlsx";
		
		// creating the model
		Model model = new Model();
		
		// creating the view
		CredentialsGui cred = new CredentialsGui();
		GUI gui = new GUI();
		
		// creating the controller
		Controller controller = new Controller(model, cred, gui);
		
		// adding observers
		model.addObserver(controller);
		cred.addObserver(controller);
		gui.addObserver(controller);
		
		// starting point from the login gui
		new Thread(cred).start();
	}
}
