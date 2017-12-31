package admin_test;

import java.io.Serializable;

public class Crendentials implements Serializable {
	private static final long serialVersionUID =1L;
	private String login =null;
	private String userName = null;
	
	public Crendentials(String userName, String login) {
		super();
		this.userName = userName;
		this.login = login;
	}
	public Crendentials() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
}