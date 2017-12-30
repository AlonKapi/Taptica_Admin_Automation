package admin.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CredentialsGui extends Observable implements Runnable, ActionListener{
	JFrame frame;
	JTextField loginInstruction;
	JTextArea username;
	JPasswordField password;
	private String[] credentials;
	private String user;
	private char[] passArr;

	public CredentialsGui() {
		this.frame = new JFrame("Login");
		credentials = new String[2];
	}
	
	public void hideWindow() {
		frame.setVisible(false);
	}
	
	@Override
	public void run() {
		// login gui
		frame.setSize(190, 200);
		JLayeredPane pane = frame.getLayeredPane();
		
		loginInstruction = new JTextField("Enter your credentials here: ");
		loginInstruction.setEditable(false);
		loginInstruction.setBounds(10, 10, 150, 20);
		loginInstruction.setBorder(null);
		
		username = new JTextArea();
		username.setBounds(10, 50, 150, 20);
		username.setBorder(new LineBorder(Color.BLACK, 1));
		
		password = new JPasswordField();
		password.setBounds(10, 90, 150, 20);
		password.setBorder(new LineBorder(Color.BLACK, 1));
		
		Button linkBtn = new Button("Submit");
		linkBtn.setBounds(55, 130, 60, 20);
		
		linkBtn.addActionListener(this);
		
		pane.add(loginInstruction, new Integer(1));
		pane.add(username, new Integer(2));
		pane.add(password, new Integer(3));
		pane.add(linkBtn, new Integer(4));
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		user = username.getText();
		passArr = password.getPassword();
		
		if (user == null) {
			loginInstruction.setText("Username can't be empty..");
		}
		
		else if (passArr == null) {
			loginInstruction.setText("Password can't be empty..");
		}
		
		else if (user != null && passArr != null) {
			StringBuilder str = new StringBuilder();
			
			for (char c : passArr) {
				str.append(c);
			}
			
			credentials[0] = user;
			credentials[1] = str.toString();
			
			setChanged();
			notifyObservers(credentials);
		}
	}
}
