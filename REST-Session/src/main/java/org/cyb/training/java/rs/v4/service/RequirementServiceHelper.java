package org.cyb.training.java.rs.v4.service;

import org.cyb.training.java.rs.v4.model.Requirement;
import org.cyb.training.java.rs.v4.view.RequestReqView;

public class RequirementServiceHelper {
	
	
	
	public static void convertRequirementViewToRequirement (Requirement requirement, RequestReqView view ) {
		IUserService usrService = DefaultUserService.getInstance();
		
		requirement.setId(view.getId());
		requirement.setTitle(view.getTitle());
		requirement.setDescription(view.getDescription());
		requirement.setEstimate(view.getEstimate());
		requirement.setEfforts(view.getEfforts());
		requirement.setOwner(usrService.getUser(view.getOwner()));
		requirement.setReqstate(view.getState());
	}

}
