package org.cyb.training.java.rs.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cyb.training.java.rs.model.Requirement;
import org.cyb.training.java.rs.utils.Constants;

@Path("/requirements/v1")
public class RequirementResourceV1 {
	
	@GET	
	@Produces(MediaType.APPLICATION_XML)
	public Requirement getRequirement() {
		Requirement requirement = new Requirement();
		requirement.setId(1);
		requirement.setTitle("Requirement 1");
		requirement.setDescription("Description for requirement1");
		requirement.setOwner("Rajendra");
		requirement.setReqState(Constants.ReqState.Backlog);
		requirement.setEstimate(10.0);
		requirement.setEfforts(0);
		
		return requirement;
	}

}
