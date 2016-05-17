package org.cyb.training.java.rs.v6.view;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyb.training.java.rs.v6.model.User;

@XmlRootElement
public class UserView {
	private String  email;
	private String userName;
	
	public UserView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserView(User user) {
		this.email = user.getEmail();
		this.userName = user.getUserName();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
