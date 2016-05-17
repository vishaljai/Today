package org.cyb.training.java.rs.service;

import java.util.List;

import org.cyb.training.java.rs.model.User;

public interface IUserService {
	public List<User> getUserList();
	public User getUser (int userid);

}
