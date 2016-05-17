package org.cyb.training.java.rs.v4.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cyb.training.java.rs.v4.model.Requirement;
import org.cyb.training.java.rs.v4.model.User;
import org.cyb.training.java.rs.v4.utils.Constants.ReqState;

public class DefaultRequirementService  implements IRequirementService{

	HashMap<User,List<Requirement>> mapRequirements = new HashMap<User, List<Requirement>> ();
	IUserService userService = DefaultUserService.getInstance();
	
	private static IRequirementService instance = null;
	private DefaultRequirementService() {
		init();
	}
	public static IRequirementService getInstance() {
		if (instance == null) {
			instance = new DefaultRequirementService();
		}
		return instance;
	}
	
	
	void init() {
		List<Requirement> user1List = new ArrayList<Requirement>();
		List<Requirement> user2List = new ArrayList<Requirement>();
		List<Requirement> user3List = new ArrayList<Requirement>();
		
		User user1 = userService.getUser(1);
		User user2 = userService.getUser(2);
		User user3 = userService.getUser(3);
		
		user1List.add(new Requirement(1,"Requirement1", "Description for Requirement1", 10.0, 0, user1, ReqState.Backlog));
		user1List.add(new Requirement(2,"Requirement2", "Description for Requirement2", 13.0, 5, user1, ReqState.Active));
		user1List.add(new Requirement(3,"Requirement3", "Description for Requirement3", 20.0, 20, user1, ReqState.Closed));
		
		user2List.add(new Requirement(4,"Requirement4", "Description for Requirement4", 10.0, 0, user2, ReqState.Backlog));
		user2List.add(new Requirement(5,"Requirement5", "Description for Requirement5", 13.0, 5, user2, ReqState.Active));
		user2List.add(new Requirement(6,"Requirement6", "Description for Requirement6", 20.0, 20, user3, ReqState.Closed));
		
		user3List.add(new Requirement(7,"Requirement7", "Description for Requirement7", 10.0, 0, user3, ReqState.Backlog));
		user3List.add(new Requirement(8,"Requirement8", "Description for Requirement8", 13.0, 5, user3, ReqState.Active));
		user3List.add(new Requirement(9,"Requirement9", "Description for Requirement9", 20.0, 20, user3, ReqState.Closed));
		
		mapRequirements.put(user1, user1List);
		mapRequirements.put(user2, user2List);
		mapRequirements.put(user3, user3List);
		
	}
	
	public List<Requirement> getRequirementList() {
		List<Requirement> allReqs = new ArrayList<Requirement>();
		List<User> users = userService.getUserList();
		
		for(User u : users) {
			List<Requirement> userRequs = mapRequirements.get(u);
			allReqs.addAll(userRequs);
		}
		return allReqs;
	}

	public Requirement getRequirement(int reqId) {
		Requirement req = null;
		List<User> users  = userService.getUserList();
		
		for(User u : users) {
			List<Requirement> userRequs = mapRequirements.get(u);
			for (Requirement r : userRequs) {
				if (r.getId() == reqId) {
					req = r;
					break;
				}
			}
				
		}
		return req;

	}

	public List<Requirement> getRequirementList(int userId) {

		List<Requirement> requirements = null;
		User user = userService.getUser(userId);
		if (mapRequirements.containsKey(user)){
			requirements = mapRequirements.get(user);
		}
		return requirements;
	}
	
	public Requirement add(Requirement r) {
	
		User owner = r.getOwner();
		if (mapRequirements.containsKey(owner)) {
			List<Requirement> requirements = mapRequirements.get(owner);
			requirements.add(r);
		}
		return r;
	}
	public Requirement update(Requirement r) {
		List<Requirement> requirements = getRequirementList();
		for (Requirement req : requirements) {
			if (req.getId() == r.getId()) {
				req.setTitle(r.getTitle());
				req.setDescription(r.getDescription());
				req.setEstimate(r.getEstimate());
				req.setEfforts(r.getEfforts());
				req.setOwner(r.getOwner());
				req.setReqstate(r.getReqstate());
				break;
			}
		}
		return r;

	}
	public void delete(int reqid) {
		List<Requirement> requirements = getRequirementList();
		for (Requirement req : requirements) {
			if (req.getId() == reqid) {
				req.setDeleted(true);
			}
		}
	}

}
