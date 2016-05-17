package org.cyb.training.java.rs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cyb.training.java.rs.model.RequirementV3;
import org.cyb.training.java.rs.model.User;
import org.cyb.training.java.rs.utils.Constants.ReqState;

public class DefaultRequirementServiceV3  implements IRequirementServiceV3{

	HashMap<User,List<RequirementV3>> mapRequirements = new HashMap<User, List<RequirementV3>> ();
	IUserService userService = new DefaultUserService();
	
	private static IRequirementServiceV3 instance = null;
	public DefaultRequirementServiceV3() {
		init();
	}
	public static IRequirementServiceV3 getInstance() {
		if (instance == null) {
			instance = new DefaultRequirementServiceV3();
		}
		return instance;
	}
	
	
	void init() {
		List<RequirementV3> user1List = new ArrayList<RequirementV3>();
		List<RequirementV3> user2List = new ArrayList<RequirementV3>();
		List<RequirementV3> user3List = new ArrayList<RequirementV3>();
		
		User user1 = userService.getUser(1);
		User user2 = userService.getUser(2);
		User user3 = userService.getUser(3);
		
		user1List.add(new RequirementV3(1,"Requirement1", "Description for Requirement1", 10.0, 0, user1, ReqState.Backlog));
		user1List.add(new RequirementV3(2,"Requirement2", "Description for Requirement2", 13.0, 5, user1, ReqState.Active));
		user1List.add(new RequirementV3(3,"Requirement3", "Description for Requirement3", 20.0, 20, user1, ReqState.Closed));
		
		user2List.add(new RequirementV3(4,"Requirement4", "Description for Requirement4", 10.0, 0, user2, ReqState.Backlog));
		user2List.add(new RequirementV3(5,"Requirement5", "Description for Requirement5", 13.0, 5, user2, ReqState.Active));
		user2List.add(new RequirementV3(6,"Requirement6", "Description for Requirement6", 20.0, 20, user3, ReqState.Closed));
		
		user3List.add(new RequirementV3(7,"Requirement7", "Description for Requirement7", 10.0, 0, user3, ReqState.Backlog));
		user3List.add(new RequirementV3(8,"Requirement8", "Description for Requirement8", 13.0, 5, user3, ReqState.Active));
		user3List.add(new RequirementV3(9,"Requirement9", "Description for Requirement9", 20.0, 20, user3, ReqState.Closed));
		
		mapRequirements.put(user1, user1List);
		mapRequirements.put(user2, user2List);
		mapRequirements.put(user3, user3List);
		
		System.out.println("inside init");
		
	}
	
	public List<RequirementV3> getRequirementList() {
		List<RequirementV3> allReqs = new ArrayList<RequirementV3>();
		List<User> users = userService.getUserList();
		
		for(User u : users) {
			List<RequirementV3> userRequs = mapRequirements.get(u);
			allReqs.addAll(userRequs);
		}
		return allReqs;
	}

	public RequirementV3 getRequirement(int reqId) {
		RequirementV3 req = null;
		List<User> users  = userService.getUserList();
		
		for(User u : users) {
			List<RequirementV3> userRequs = mapRequirements.get(u);
			for (RequirementV3 r : userRequs) {
				if (r.getId() == reqId) {
					req = r;
					break;
				}
			}
				
		}
		return req;

	}

	public List<RequirementV3> getRequirementList(int userId) {

		List<RequirementV3> requirements = null;
		User user = userService.getUser(userId);
		if (mapRequirements.containsKey(user)){
			requirements = mapRequirements.get(user);
		}
		return requirements;
	}
	
	public RequirementV3 add(RequirementV3 r) {
	
		User owner = userService.getUser(r.getOwner().getUserId());
		System.out.println("User:"+owner);
		if (mapRequirements.get(owner) != null ) {
			List<RequirementV3> requirements = mapRequirements.get(owner);
			requirements.add(r);
			//mapRequirements.put(owner, requirements);
			System.out.println(mapRequirements.get(owner).size());
		}		
		return r;
	}
	public RequirementV3 update(RequirementV3 r) {
		List<RequirementV3> requirements = getRequirementList();
		for (RequirementV3 req : requirements) {
			if (req.getId() == r.getId()) {
				requirements.remove(req);
				req.setTitle(r.getTitle());
				req.setDescription(r.getDescription());
				req.setEstimate(r.getEstimate());
				req.setEfforts(r.getEfforts());
				req.setOwner(r.getOwner());
				req.setReqstate(r.getReqstate());
				requirements.add(req);
				break;
			}
			
		}
		//User owner = userService.getUser(r.getOwner().getUserId());
		//mapRequirements.put(owner, requirements);
		return r;

	}
	public void delete(int reqid) {
		List<RequirementV3> requirements = getRequirementList();
		
		for (RequirementV3 req : requirements) {			
			if (req.getId() == reqid) {
				req.setDeleted(true);			
			}
		}
	}

}
