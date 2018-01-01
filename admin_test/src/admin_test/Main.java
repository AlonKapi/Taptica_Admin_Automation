package admin_test;

import admin.controller.Controller;
import admin.view.MainWindow;

public class Main{
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C://chromedriver/chromedriver.exe"); // getting driver
		//String filePath = "C://offerslist.xlsx";
		
		// creating the model
		Model model = new Model();

		// creating the view
		MainWindow gui = new MainWindow();
		
		// creating the controller
		Controller controller = new Controller(model, gui);
		
		// adding observers
		model.addObserver(controller);
		gui.addObserver(controller);
		
		// starting the gui
		new Thread(gui).start();
	}
}
