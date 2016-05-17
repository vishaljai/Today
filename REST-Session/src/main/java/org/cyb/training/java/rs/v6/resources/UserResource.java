package org.cyb.training.java.rs.v6.resources;


import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.cyb.training.java.rs.v6.exception.CoreServiceException;
import org.cyb.training.java.rs.v6.model.User;
import org.cyb.training.java.rs.v6.service.DefaultUserService;
import org.cyb.training.java.rs.v6.service.IUserService;
import org.cyb.training.java.rs.v6.view.UserView;

@Path("/users/v6")
public class UserResource {
	IUserService userService = DefaultUserService.getInstance();
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") int userId) throws CoreServiceException{
		User user = userService.getUser(userId);

		if (user == null) {
			throw new CoreServiceException("Not a registered user");
		}
		CacheControl cc = new CacheControl();
		cc.setMaxAge(120);
		cc.setPrivate(true);
		ResponseBuilder res = Response.status(Status.OK);
		res.cacheControl(cc);
		res.entity(new UserView(user));
		return res.build();
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listUsers() {
		return userService.getUserList();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id, User user) throws CoreServiceException {
		try {
			userService.updateUser(id, user);
			
		}catch (Exception e) {
			throw new CoreServiceException("Updation failed");
		}
		
		ResponseBuilder res = Response.status(Status.OK);
		res.entity(new UserView(user));
		return res.build();
	}

}

