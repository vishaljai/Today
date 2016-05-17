package org.cyb.training.java.rs.v5.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.cyb.training.java.rs.v5.model.Requirement;

@XmlRootElement
public class RequirementView {

	@NotNull (message="Request id can not be null")
	int id;
	@Size(min=1, max=20)
	String title;
	String description;
	int owner;
		
	public RequirementView() {
		// TODO Auto-generated constructor stub
	}
	
	public RequirementView(Requirement req) {
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
