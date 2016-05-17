package org.cyb.training.java.rs.resources;

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

import org.cyb.training.java.rs.model.RequirementV3;
import org.cyb.training.java.rs.service.DefaultRequirementServiceV3;
import org.cyb.training.java.rs.service.IRequirementServiceV3;
import org.cyb.training.java.rs.service.RequirementServiceHelper;
import org.cyb.training.java.rs.view.RequestReqView;
import org.cyb.training.java.rs.view.ResponseReqView;


@Path("/requirements/v3")
public class RequirementResourceV3 {
	
	IRequirementServiceV3 reqService = DefaultRequirementServiceV3.getInstance();
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_XML)
	public Response listRequirements() {
	
		List<ResponseReqView> resView = new ArrayList<ResponseReqView>();
		List<RequirementV3> reqs = reqService.getRequirementList();
		for (RequirementV3 req : reqs){
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
	@Produces(MediaType.APPLICATION_XML)
	public Response getRequirement(@PathParam("id") int id) {
		Response.ResponseBuilder res = null;
		RequirementV3 req = reqService.getRequirement(id);
		
		if (req != null && !req.isDeleted()) {
			res = Response.status(Status.OK);
			res.entity(new ResponseReqView(req));
		}
		else
			res = Response.status(Status.OK);
		return res.build();
	}

	
	
	@POST
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response addRequirement(RequestReqView view) {
		RequirementV3 requirement = new RequirementV3();
		RequirementServiceHelper.convertRequirementViewToRequirement(requirement, view);
		reqService.add(requirement);
		Response.ResponseBuilder res = Response.status(Status.OK);
		res.entity(new ResponseReqView(requirement));
		return res.build();
		
		
	}
	@PUT
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateRequirement(RequestReqView view) {
		RequirementV3 requirement = new RequirementV3();
		RequirementServiceHelper.convertRequirementViewToRequirement(requirement, view);
		reqService.update(requirement);
		Response.ResponseBuilder res = Response.status(Status.OK);
		res.entity(new ResponseReqView(requirement));
		return res.build();
		
		
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteRequirement(@PathParam("id") int id) {
		reqService.delete(id);
		Response.ResponseBuilder res = Response.status(Status.OK);
		return res.build();
	}

	
}
