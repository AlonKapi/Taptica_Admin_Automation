package admin.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import admin_bulk.BulkUpdateoffers;
import admin_test.Crendentials;
import admin_test.Offer;

public class MainWindow extends Observable implements ActionListener, Runnable{
	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JFrame loginView;
	private JButton bulkRun;
	private JButton adminRun;
	private JTextField userName;
	private JTextField passField;
	private DefaultTableModel modelOffers;
	public static File credFile = new File(".\\AdminLogin.txt");
	protected ArrayList<Offer> offersList;
	Map<String, ArrayList<BulkUpdateoffers>> offersPerAdvId;
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	@Override
	public void run() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 33, 850, 500);
		frame.getContentPane().add(tabbedPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll1 = new JScrollPane(table);
		tabbedPane.addTab("Details", null, scroll1, null);
		
		table_1 = new JTable();
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_1.setFillsViewportHeight(true);
		JScrollPane scroll2 = new JScrollPane(table_1);
		tabbedPane.addTab("Edits", null, scroll2, null);
		
		table_2 = new JTable();
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table_2.setFillsViewportHeight(true);
		JScrollPane scroll3 = new JScrollPane(table_2);
		tabbedPane.addTab("Bulk", null, scroll3, null);
		
		JMenuBar bar = new JMenuBar();
		
		JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setBounds(0, 0, 107, 22);
		frame.getContentPane().add(optionsMenu);
		
		JMenuItem mntmFromExcel = new JMenuItem("From Excel");
		optionsMenu.add(mntmFromExcel);
		
		mntmFromExcel.addActionListener(this);
		
		JMenuItem mntmLoadBulkOffers = new JMenuItem("Load Bulk Offers");
		optionsMenu.add(mntmLoadBulkOffers);
		
		mntmLoadBulkOffers.addActionListener(this);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		optionsMenu.add(mntmLogin);
		
		mntmLogin.addActionListener(this);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		optionsMenu.add(mntmExit);
		
		mntmExit.addActionListener(this);
		
		optionsMenu.add(mntmFromExcel);
		optionsMenu.add(mntmLoadBulkOffers);
		optionsMenu.add(mntmLogin);
		optionsMenu.addSeparator();
		optionsMenu.add(mntmExit);
		
		bar.add(optionsMenu);
		frame.setJMenuBar(bar);
		
		bulkRun = new JButton("Run Bulk Offers");
		bulkRun.setBounds(610, 0, 130, 23);
		frame.getContentPane().add(bulkRun);
		
		bulkRun.addActionListener(this);
		bulkRun.setVisible(false);
		
		adminRun = new JButton("Run Admin");
		adminRun.setBounds(750, 0, 101, 23);
		frame.getContentPane().add(adminRun);
		
		adminRun.addActionListener(this);
		
		adminRun.setVisible(false);
		
		frame.setVisible(true);
		
		loginView = new JFrame();
		
		loginView.setLayout(new FlowLayout());
		loginView.setVisible(false);
		loginView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JLabel user = new JLabel("UserName:");
		userName = new JTextField(20);
		JLabel pass = new JLabel("Password:");
		passField = new JTextField(20);
		JButton submit = new JButton("Save");
		
		submit.addActionListener(this);
		
		loginView.add(user);
		loginView.add(userName);
		loginView.add(pass);
		loginView.add(passField);
		loginView.add(submit);
		loginView.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String temp = e.getActionCommand();
		
		switch(temp) {
		case "From Excel":
			getFilePath();
			break;
		case "Load Bulk Offers":
			BulkOffers();
			break;
		case "Login":
			loginView.setVisible(true);
			break;
		case "Exit":
			System.out.println("Goodbye!");
			System.exit(0);
			break;
		case "Run Bulk Offers":
			setChanged();
			notifyObservers("RUN_BULK");
			break;
		case "Run Admin":
			setChanged();
			notifyObservers("RUN");
			break;
		case "Save":
			saveCred();
			break;
		}
	}
	
	public void BulkOffers(){
		final JFileChooser chooser = new JFileChooser("./");
		int returned = chooser.showOpenDialog(frame);
		if (returned == JFileChooser.APPROVE_OPTION)
		{
			File f = chooser.getSelectedFile();
			String filepath = f.getAbsolutePath();
			setChanged();
			notifyObservers("BULK " + filepath);
		}
	}
	
	public void fillBulkTable() {
		DefaultTableModel model = (DefaultTableModel)table_2.getModel();
		
		Object[] tableCols = {"Adv ID","Offer Id"};
		
		for (Object name : tableCols) {
			model.addColumn(name);
		}
		
		for (String ob : offersPerAdvId.keySet()) {
			ArrayList<BulkUpdateoffers> temp = offersPerAdvId.get(ob);
			String offers = "";
			for (int i=0; i<temp.size(); i++) {
				offers += temp.get(i).toString() + ", ";
			}
			Object[] obj = {ob, offers};
			model.addRow(obj);
		}
		
		bulkRun.setVisible(true);
	}
	
	public void getFilePath() {
		JFileChooser fileChooser = new JFileChooser("./");
		int returned = fileChooser.showOpenDialog(frame);
		
		if (returned == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			String filepath = f.getAbsolutePath();
			
			setChanged();
			notifyObservers("OFFERS " + filepath);
		}
	}
	
	public void saveCred() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		Crendentials ob = null;
		try {
			credFile.delete();
			fos = new FileOutputStream(credFile);
			oos = new ObjectOutputStream(fos);
			ob = new Crendentials(userName.getText(), passField.getText());
			oos.writeObject(ob);
		} 
		catch (IOException err) {
			err.printStackTrace();
		} 
		finally {
			try {
				fos.close();
			} 
			catch (IOException e1) {
				
				e1.printStackTrace();
			}
		}
		loginView.dispose();
	}
	
	public void fillTable() {
		modelOffers = (DefaultTableModel)table.getModel();
		
		for (Object name : Offer.toColums()) {
			modelOffers.addColumn(name);
		}
		
		for (Offer ob : offersList) {
			modelOffers.addRow(ob.toData());
		}
		
		DefaultTableModel model2 = (DefaultTableModel)table_1.getModel();
		Object[] table2Cols = {"OfferName","Tracking Link","Application URL","Comments"};
		
		for (Object name : table2Cols) {
			model2.addColumn(name);
		}
		
		for (Offer ob : offersList) {
			Object[] temp = {ob.getOfferName(), ob.getOfferTrackingLink(), ob.getAppURL(), ob.getComments()};
			model2.addRow(temp);
		}
		
		adminRun.setVisible(true);
		
		model2.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getFirstRow() == e.getLastRow()) {	// only allow a single row change
					if (e.getColumn() == 1) {
						offersList.get(e.getFirstRow()).setOfferTrackingLink((String) model2.getValueAt(e.getFirstRow(), e.getColumn()));
					}
					else if(e.getColumn() == 2) {
						offersList.get(e.getFirstRow()).setAppURL((String) model2.getValueAt(e.getFirstRow(), e.getColumn()));
					}
					setChanged();
					notifyObservers(offersList);
				}
			}
		});
	}
	
	public void changeTableFinished() {
		DefaultTableModel modelNew = (DefaultTableModel) table.getModel();
		
		Object[] tableCols = {"Offer Name","Offer Id"};
		
		for (Object name : tableCols) {
			modelNew.addColumn(name);
		}
		
		for (Offer ob : offersList) {
			Object[] temp = {ob.getOfferName(), ob.getOfferID()};
			modelNew.addRow(temp);
		}
	}
	
	public void finishedMsg(String msg) {
		JOptionPane.showMessageDialog(frame, msg);
	}
	
	public void setOffers(ArrayList<Offer> offersList) {
		this.offersList = offersList;
	}
	
	public Map<String, ArrayList<BulkUpdateoffers>> getOffersPerAdvId() {
		return offersPerAdvId;
	}

	public void setOffersPerAdvId(Map<String, ArrayList<BulkUpdateoffers>> offersPerAdvId) {
		this.offersPerAdvId = offersPerAdvId;
	}
}
