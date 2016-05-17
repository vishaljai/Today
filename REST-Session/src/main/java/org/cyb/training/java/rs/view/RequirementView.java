package org.cyb.training.java.rs.view;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyb.training.java.rs.model.RequirementV3;

@XmlRootElement
public class RequirementView {

	int id;
	String title;
	String description;
	int owner;
		
	public RequirementView() {
		// TODO Auto-generated constructor stub
	}
	
	public RequirementView(RequirementV3 req) {
		this.id = req.getId();
		this.title = req.getTitle();
		this.description = req.getDescription();
		this.owner = req.getOwner().getUserId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}




}
