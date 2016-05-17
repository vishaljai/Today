package org.cyb.training.java.rs.v4.service;


import java.util.List;

import org.cyb.training.java.rs.v4.model.Requirement;

public interface IRequirementService {

	public List<Requirement> getRequirementList();
	public Requirement getRequirement(int reqId);
	public List<Requirement> getRequirementList(int userId);
	public Requirement add (Requirement r);
	public Requirement update(Requirement r);
	public void delete(int reqid);
}
