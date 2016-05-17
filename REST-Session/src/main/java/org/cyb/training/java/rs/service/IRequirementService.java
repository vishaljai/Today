package org.cyb.training.java.rs.service;

import java.util.List;

import org.cyb.training.java.rs.model.RequirementV2;

public interface IRequirementService {

	public List<RequirementV2> getRequirementList();
	public RequirementV2 getRequirement(int reqId);
	public List<RequirementV2> getRequirementList(int userId);
}
