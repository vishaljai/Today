package org.cyb.training.java.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.cyb.training.java.rs.model.User;

public class DefaultUserService implements IUserService {
	
	List<User> users = new ArrayList<User>();
	public DefaultUserService() {
		init();
	}
	
	private void init() {
		User user1 = new User(1, "Rajendra", "rajendrapa@cybage.com");
		User user2 = new User(2, "Dilip", "dilipo@cybage.com");
		User user3 = new User(3, "Aniket", "aniketd@cybage.com");
		users.add(user1);
		users.add(user2);
		users.add(user3);

	}

	public List<User> getUserList() {
		return users;
	}

	public User getUser(int userid) {
		User user = null;
		for (User u : users) {
			if (u.getUserId() == userid) {
				user = u;
			}
		}
		return user;
	}

}
