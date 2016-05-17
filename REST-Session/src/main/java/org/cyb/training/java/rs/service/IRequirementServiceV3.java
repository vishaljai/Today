package org.cyb.training.java.rs.service;


import java.util.List;

import org.cyb.training.java.rs.model.RequirementV3;

public interface IRequirementServiceV3 {

	public List<RequirementV3> getRequirementList();
	public RequirementV3 getRequirement(int reqId);
	public List<RequirementV3> getRequirementList(int userId);
	public RequirementV3 add (RequirementV3 r);
	public RequirementV3 update(RequirementV3 r);
	public void delete(int reqid);
}
