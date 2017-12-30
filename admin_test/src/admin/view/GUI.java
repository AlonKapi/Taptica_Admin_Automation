package admin.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import admin_test.Offer;

public class GUI extends Observable implements Runnable, ActionListener{
	private JFrame frame;
	protected ArrayList<Offer> offersList;
	JPanel excelPanel;
	JPanel offersPanel;
	String[] columnNames = {"Name", "Class", "End Date", "Budget Type", "Budget Limit", "Budget Start Date", "Daily Cap", "Revenue", "Payout"
			, "Countries", "Platforms", "Requirements", "KPI"};
	
	public GUI() {
		this.frame = new JFrame("Automate Offers Creation");
		this.frame.setSize(500, 500);
	}
	
	@Override
	public void run() {
		excelPanel = new JPanel();
		
		JTextField instruction = new JTextField("Select the excel file");
		instruction.setEditable(false);
		instruction.setBounds(10, 10, 200, 20);
		instruction.setBorder(null);
		
		JButton excelBtn = new JButton("Select");
		excelBtn.addActionListener(this);
		
		excelPanel.add(instruction, new Integer(1));
		excelPanel.add(excelBtn, new Integer(2));
		
		frame.add(excelPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void getFilePath() {
		JFileChooser fileChooser = new JFileChooser("./");
		int returned = fileChooser.showOpenDialog(frame);
		
		if (returned == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			String filepath = f.getAbsolutePath();
			
			System.out.println(filepath);
			setChanged();
			notifyObservers(filepath);
		}
	}
	
	
	
	public void offersTable() {
		frame.remove(excelPanel);
		
		offersPanel = new JPanel();
		
		JTextField instruction = new JTextField("Verify the information");
		instruction.setEditable(false);
		instruction.setBounds(10, 10, 200, 20);
		instruction.setBorder(null);
		
		JButton excelBtn = new JButton("Next");
		excelBtn.addActionListener(this);
		
		Vector<String> columns = new Vector<String>();
		for (String s : columnNames) {
			columns.add(s);
		}
		
		Vector<String[]> data = new Vector<String[]>();
		
		for (Offer o : offersList) {
			data.add(o.getOfferAsRow());
		}
		
		Object[][] data2 = {
				{"Name", "Class", "End Date", "Budget Type", "Budget Limit", "Budget Start Date", "Daily Cap", "Revenue", "Payout"
				, "Countries", "Platforms", "Requirements", "KPI"}, 
				{"sad", "gfd", "End asd", "Budget Type", "Budget Limit", "Budget Start Date", "Daily Cap", "Revenue", "Payout"
				, "Countries", "Platforms", "Requirements", "KPI"}
				};
		
		JTable table = new JTable(data2, columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scroll = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		
		offersPanel.add(instruction, new Integer(1));
		offersPanel.add(scroll, new Integer(2));
		
		frame.add(offersPanel);
	}
	
	public void setOffers(ArrayList<Offer> offersList) {
		this.offersList = offersList;
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		String event = arg.getActionCommand();
		
		if (event.equals("Select")) {
			getFilePath();
		}
		else if (event.equals("Next")) {
			
		}
	}
}
