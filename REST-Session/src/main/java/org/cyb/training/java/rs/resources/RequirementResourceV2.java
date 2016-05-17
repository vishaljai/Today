package org.cyb.training.java.rs.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.cyb.training.java.rs.model.RequirementV2;
import org.cyb.training.java.rs.service.DefaultRequirementService;
import org.cyb.training.java.rs.service.IRequirementService;

@Path("/requirements/v2")
public class RequirementResourceV2 {
	
	IRequirementService reqService = new DefaultRequirementService();
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_XML)
	public List<RequirementV2> listRequirements() {
	
		List<RequirementV2> reqs = reqService.getRequirementList();
		return reqs;
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public RequirementV2 getRequirement(@PathParam("id") int id) {
		RequirementV2 req = reqService.getRequirement(id);
		return req;
	}
	
	@GET
	@Path("/listByReqtId")
	@Produces(MediaType.APPLICATION_XML)
	public RequirementV2 getRequirementByRequirementId(@QueryParam("reqtId") int reqtId) {
		RequirementV2 req = reqService.getRequirement(reqtId);
		return req;
	}
	
}
