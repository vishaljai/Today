package org.cyb.training.java.rs.v4.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.cyb.training.java.rs.v4.model.Requirement;
import org.cyb.training.java.rs.v4.service.DefaultRequirementService;
import org.cyb.training.java.rs.v4.service.IRequirementService;
import org.cyb.training.java.rs.v4.service.RequirementServiceHelper;
import org.cyb.training.java.rs.v4.view.RequestReqView;
import org.cyb.training.java.rs.v4.view.ResponseReqView;


@Path("/requirements/v4")
public class RequirementResources {
	
	IRequirementService reqService = DefaultRequirementService.getInstance();
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listRequirements() {
	
		List<ResponseReqView> resView = new ArrayList<ResponseReqView>();
		List<Requirement> reqs = reqService.getRequirementList();
		for (Requirement req : reqs){
			if(!req.isDeleted()){
				resView.add(new ResponseReqView(req));
			}
		}
		Response.ResponseBuilder res = Response.status(Status.OK);
		GenericEntity<List<ResponseReqView>> entity = new GenericEntity<List<ResponseReqView>>(resView) {};
		res.entity(entity);
		return res.build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequirement(@PathParam("id") int id) {
		Response.ResponseBuilder res = null;
		Requirement req = reqService.getRequirement(id);
		
		if (req != null && !req.isDeleted()) {
			res = Response.status(Status.OK);
			res.entity(new ResponseReqView(req));
		}
		else
			res = Response.status(Status.OK);
		return res.build();
	}

	@GET
	@Path("/list/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listReqirementByUserId(@QueryParam("userid") int userId) {
		List<ResponseReqView> reqs = new ArrayList<ResponseReqView> ();
		List<Requirement> requirements = reqService.getRequirementList(userId);

		for(Requirement r : requirements) {
			reqs.add(new ResponseReqView(r));
		}
		Response.ResponseBuilder res = Response.status(Status.OK);
		GenericEntity<List<ResponseReqView>> entity = new GenericEntity<List<ResponseReqView>>(reqs) {};
		res.entity(entity);	
		return res.build();
	}
	@GET
	@Path("/count/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequirementCountByUserId(@PathParam("userid") int userid) {
		
		int count = reqService.getRequirementList(userid).size();
		
		Response.ResponseBuilder res = Response.status(Status.OK);
		String cnt = String.valueOf(count);
		res.entity(cnt);
		return res.build();
		}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRequirement(RequestReqView view) {
		Requirement requirement = new Requirement();
		RequirementServiceHelper.convertRequirementViewToRequirement(requirement, view);
		reqService.add(requirement);
		Response.ResponseBuilder res = Response.status(Status.OK);
		res.entity(new ResponseReqView(requirement));
		return res.build();
		
		
	}
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRequirement(RequestReqView view) {
		Requirement requirement = new Requirement();
		RequirementServiceHelper.convertRequirementViewToRequirement(requirement, view);
		reqService.update(requirement);
		Response.ResponseBuilder res = Response.status(Status.OK);
		res.entity(new ResponseReqView(requirement));
		return res.build();
		
		
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRequirement(@PathParam("id") int id) {
		reqService.delete(id);
		Response.ResponseBuilder res = Response.status(Status.OK);
		return res.build();
	}

	
}
