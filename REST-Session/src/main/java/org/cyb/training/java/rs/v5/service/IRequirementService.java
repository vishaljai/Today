package org.cyb.training.java.rs.v5.service;


import java.util.List;

import org.cyb.training.java.rs.v5.model.Requirement;

public interface IRequirementService {

	public List<Requirement> getRequirementList();
	public Requirement getRequirement(int reqId) throws Exception;
	public List<Requirement> getRequirementList(int userId);
	public Requirement add (Requirement r);
	public Requirement update(Requirement r);
	public void delete(int reqid);
}
