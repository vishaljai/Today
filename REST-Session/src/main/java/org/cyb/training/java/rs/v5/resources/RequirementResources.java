package org.cyb.training.java.rs.v5.resources;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.cyb.training.java.rs.v5.exception.CoreServiceException;
import org.cyb.training.java.rs.v5.model.Requirement;
import org.cyb.training.java.rs.v5.providers.ListofValues;
import org.cyb.training.java.rs.v5.service.DefaultRequirementService;
import org.cyb.training.java.rs.v5.service.IRequirementService;
import org.cyb.training.java.rs.v5.service.RequirementServiceHelper;
import org.cyb.training.java.rs.v5.view.RequestReqView;
import org.cyb.training.java.rs.v5.view.ResponseReqView;


@Path("/requirements/v5")
public class RequirementResources {
	
	IRequirementService reqService = DefaultRequirementService.getInstance();
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listRequirements() throws CoreServiceException {
	
		List<ResponseReqView> resView = new ArrayList<ResponseReqView>();
		List<Requirement> reqs = reqService.getRequirementList();
		
		if (reqs == null) {
			throw new CoreServiceException("No requirements to list");
		}
		
		for (Requirement req : reqs){
			resView.add(new ResponseReqView(req));
		}
		
		Response.ResponseBuilder res = Response.status(Status.OK);
		GenericEntity<List<ResponseReqView>> entity = new GenericEntity<List<ResponseReqView>>(resView) {};
		res.entity(entity);
		return res.build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRequirement(@PathParam("id") int id, @Context Request request) throws CoreServiceException {
		Response.ResponseBuilder res = null;
		Requirement req = null;
		
		try {
			req = reqService.getRequirement(id);
		}catch (Exception e) {
			throw new CoreServiceException("There was some error", e);
		}

		if (req != null && !req.isDeleted()) {
			EntityTag etag = new EntityTag(req.getLastModified().hashCode() + "");
			res = request.evaluatePreconditions(etag);
			if (res == null) {
				System.out.println("Generating new resource..");
				res = Response.status(Status.OK);
				res.tag(etag);
				res.entity(new ResponseReqView(req));
			}
		}
		else
			throw new CoreServiceException ("No requirement or requirement is deleted");
		
		return res.build();
	}
	
	
	@GET
	@Path("/{id}")
	@Produces(ListofValues.MIME_TYPE)
	public Response getLovRequirement(@PathParam("id") int id, @Context Request request) throws CoreServiceException {
		Response.ResponseBuilder res = null;
		Requirement req = null;
		
		try {
			req = reqService.getRequirement(id);
		}catch (Exception e) {
			throw new CoreServiceException("There was some error", e);
		}

		if (req != null && !req.isDeleted()) {
			EntityTag etag = new EntityTag(req.getLastModified().hashCode() + "");
			res = request.evaluatePreconditions(etag);
			if (res == null) {
				res = Response.status(Status.OK);
				res.tag(etag);
				ResponseReqView resView = new ResponseReqView(req);				
				res.entity(ListofValues.fromResponseView(resView));
			}
		}
		else
			throw new CoreServiceException ("No requirement or requirement is deleted");
		
		return res.build();
	}

	@GET
	@Path("/list/user")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listReqirementByUserId(@QueryParam("userid") int userId) throws CoreServiceException {
		
		List<ResponseReqView> reqs = new ArrayList<ResponseReqView> ();
		List<Requirement> requirements = reqService.getRequirementList(userId);
		
		if (requirements == null) {
			throw new CoreServiceException("No requirement for given user");
		}

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
	public Response getRequirementCountByUserId(@PathParam("userid") int userid) throws CoreServiceException {
		
		int count = reqService.getRequirementList(userid).size();
		if (count == 0) {
			throw new CoreServiceException("No requirement for given user");
		}
		
		Response.ResponseBuilder res = Response.status(Status.OK);
		String cnt = String.valueOf(count);
		res.entity(cnt);
		return res.build();
		}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRequirement(@Valid RequestReqView view) {
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
	
	@POST
	@Produces(ListofValues.MIME_TYPE)
	@Consumes(ListofValues.MIME_TYPE)
	public Response addRequirement(ListofValues lov) {
		RequestReqView view = lov.toRequestView();
		Requirement requirement = new Requirement();
		RequirementServiceHelper.convertRequirementViewToRequirement(requirement, view);
		reqService.add(requirement);
		Response.ResponseBuilder res = Response.status(Status.OK);
		res.entity(lov);
		return res.build();
	}
	@GET
	@Produces("text/plain")
	@Path("/who")
	public String whoIsCalling(@Context SecurityContext context) {
		String userName = context.getUserPrincipal().getName();
		String Role = context.isUserInRole("managers") ? "Managers" : "Users";
		
		String out = "User " + userName + " is in " + Role + " Role";
		return out;
	}
}
