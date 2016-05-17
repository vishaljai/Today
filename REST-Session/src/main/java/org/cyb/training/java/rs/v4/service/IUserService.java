package org.cyb.training.java.rs.v4.service;

import java.util.List;

import org.cyb.training.java.rs.v4.model.User;

public interface IUserService {
	public List<User> getUserList();
	public User getUser (int userid);

}
