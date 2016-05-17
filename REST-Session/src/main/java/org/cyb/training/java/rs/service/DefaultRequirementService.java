package org.cyb.training.java.rs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cyb.training.java.rs.model.RequirementV2;
import org.cyb.training.java.rs.model.User;
import org.cyb.training.java.rs.utils.Constants.ReqState;

public class DefaultRequirementService  implements IRequirementService{

	HashMap<User,List<RequirementV2>> mapRequirements = new HashMap<User, List<RequirementV2>> ();
	IUserService userService = new DefaultUserService();
	
	public DefaultRequirementService() {
		init();
	}
	
	void init() {
		List<RequirementV2> user1List = new ArrayList<RequirementV2>();
		List<RequirementV2> user2List = new ArrayList<RequirementV2>();
		List<RequirementV2> user3List = new ArrayList<RequirementV2>();
		
		User user1 = userService.getUser(1);
		User user2 = userService.getUser(2);
		User user3 = userService.getUser(3);
		
		user1List.add(new RequirementV2(1,"Requirement1", "Description for Requirement1", 10.0, 0, user1, ReqState.Backlog));
		user1List.add(new RequirementV2(2,"Requirement2", "Description for Requirement2", 13.0, 5, user1, ReqState.Active));
		user1List.add(new RequirementV2(3,"Requirement3", "Description for Requirement3", 20.0, 20, user1, ReqState.Closed));
		
		user2List.add(new RequirementV2(4,"Requirement4", "Description for Requirement4", 10.0, 0, user2, ReqState.Backlog));
		user2List.add(new RequirementV2(5,"Requirement5", "Description for Requirement5", 13.0, 5, user2, ReqState.Active));
		user2List.add(new RequirementV2(6,"Requirement6", "Description for Requirement6", 20.0, 20, user3, ReqState.Closed));
		
		user3List.add(new RequirementV2(7,"Requirement7", "Description for Requirement7", 10.0, 0, user3, ReqState.Backlog));
		user3List.add(new RequirementV2(8,"Requirement8", "Description for Requirement8", 13.0, 5, user3, ReqState.Active));
		user3List.add(new RequirementV2(9,"Requirement9", "Description for Requirement9", 20.0, 20, user3, ReqState.Closed));
		
		mapRequirements.put(user1, user1List);
		mapRequirements.put(user2, user2List);
		mapRequirements.put(user3, user3List);
		
	}
	
	public List<RequirementV2> getRequirementList() {
		List<RequirementV2> allReqs = new ArrayList<RequirementV2>();
		List<User> users = userService.getUserList();
		
		for(User u : users) {
			List<RequirementV2> userRequs = mapRequirements.get(u);
			allReqs.addAll(userRequs);
		}
		return allReqs;
	}

	public RequirementV2 getRequirement(int reqId) {
		RequirementV2 req = null;
		List<User> users  = userService.getUserList();
		
		for(User u : users) {
			List<RequirementV2> userRequs = mapRequirements.get(u);
			for (RequirementV2 r : userRequs) {
				if (r.getId() == reqId) {
					req = r;
					break;
				}
			}
				
		}
		return req;

	}

	public List<RequirementV2> getRequirementList(int userId) {

		List<RequirementV2> requirements = null;
		User user = userService.getUser(userId);
		if (mapRequirements.containsKey(user)){
			requirements = mapRequirements.get(user);
		}
		return requirements;
	}
	
	

}
