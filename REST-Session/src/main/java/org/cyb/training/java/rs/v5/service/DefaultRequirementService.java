package org.cyb.training.java.rs.v5.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.cyb.training.java.rs.v5.model.Requirement;
import org.cyb.training.java.rs.v5.model.User;
import org.cyb.training.java.rs.v5.utils.Constants.ReqState;

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
		
		Date currentDate = Calendar.getInstance().getTime();
		
		user1List.add(new Requirement(1,"Requirement1", "Description for Requirement1", 10.0, 0, user1, ReqState.Backlog,currentDate ));
		user1List.add(new Requirement(2,"Requirement2", "Description for Requirement2", 13.0, 5, user1, ReqState.Active, currentDate));
		user1List.add(new Requirement(3,"Requirement3", "Description for Requirement3", 20.0, 20, user1, ReqState.Closed, currentDate));
		
		user2List.add(new Requirement(4,"Requirement4", "Description for Requirement4", 10.0, 0, user2, ReqState.Backlog, currentDate));
		user2List.add(new Requirement(5,"Requirement5", "Description for Requirement5", 13.0, 5, user2, ReqState.Active, currentDate));
		user2List.add(new Requirement(6,"Requirement6", "Description for Requirement6", 20.0, 20, user3, ReqState.Closed, currentDate));
		
		user3List.add(new Requirement(7,"Requirement7", "Description for Requirement7", 10.0, 0, user3, ReqState.Backlog, currentDate));
		user3List.add(new Requirement(8,"Requirement8", "Description for Requirement8", 13.0, 5, user3, ReqState.Active, currentDate));
		user3List.add(new Requirement(9,"Requirement9", "Description for Requirement9", 20.0, 20, user3, ReqState.Closed, currentDate));
		
		mapRequirements.put(user1, user1List);
		mapRequirements.put(user2, user2List);
		mapRequirements.put(user3, user3List);
		
	}
	
	public List<Requirement> getRequirementList(){
		List<Requirement> allReqs = new ArrayList<Requirement>();
		List<User> users = userService.getUserList();
		
		for(User u : users) {
			List<Requirement> userRequs = mapRequirements.get(u);
			allReqs.addAll(userRequs);
		}
		return allReqs;
	}

	public Requirement getRequirement(int reqId)  throws Exception {
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
			r.setLastModified(Calendar.getInstance().getTime());
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
				req.setLastModified(Calendar.getInstance().getTime());
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
