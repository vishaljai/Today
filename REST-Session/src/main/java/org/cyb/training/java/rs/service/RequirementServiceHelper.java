package org.cyb.training.java.rs.service;

import org.cyb.training.java.rs.model.RequirementV3;
import org.cyb.training.java.rs.view.RequestReqView;

public class RequirementServiceHelper {
	
	
	
	public static void convertRequirementViewToRequirement (RequirementV3 requirement, RequestReqView view ) {
		IUserService usrService = new DefaultUserService();
		
		requirement.setId(view.getId());
		requirement.setTitle(view.getTitle());
		requirement.setDescription(view.getDescription());
		requirement.setEstimate(view.getEstimate());
		requirement.setEfforts(view.getEfforts());
		requirement.setOwner(usrService.getUser(view.getOwner()));
		requirement.setReqstate(view.getState());
	}

}
