package org.cyb.training.java.rs.v6.service;

import java.util.List;

import org.cyb.training.java.rs.v6.model.User;

public interface IUserService {
	public List<User> getUserList();
	public User getUser (int userid);
	public void updateUser(int userId, User user) throws Exception;

}
