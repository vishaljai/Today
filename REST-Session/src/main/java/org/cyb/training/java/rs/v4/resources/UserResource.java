package org.cyb.training.java.rs.v4.resources;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cyb.training.java.rs.v4.model.User;
import org.cyb.training.java.rs.v4.service.DefaultUserService;
import org.cyb.training.java.rs.v4.service.IUserService;

@Path("/users/v4")
public class UserResource {
	IUserService userService = DefaultUserService.getInstance();
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") int userId){
		return userService.getUser(userId);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listUsers() {
		return userService.getUserList();
	}

}
