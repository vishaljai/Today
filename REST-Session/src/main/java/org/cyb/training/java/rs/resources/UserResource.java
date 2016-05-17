package org.cyb.training.java.rs.resources;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cyb.training.java.rs.model.User;
import org.cyb.training.java.rs.service.DefaultUserService;
import org.cyb.training.java.rs.service.IUserService;

@Path("/users")
public class UserResource {
	IUserService userService = new DefaultUserService();
	
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
