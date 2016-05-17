package org.cyb.training.java.rs.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyb.training.java.rs.utils.Constants.ReqState;

@XmlRootElement
public class Requirement {
	private int id;
	private String title;
	private String description;
	private double estimate;
	private double efforts;
	private String owner;
	private ReqState reqState;
	
	public Requirement() {
		super();
		// TODO Auto-generated constructor stub
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
	public double getEstimate() {
		return estimate;
	}
	public void setEstimate(double estimate) {
		this.estimate = estimate;
	}
	public double getEfforts() {
		return efforts;
	}
	public void setEfforts(double efforts) {
		this.efforts = efforts;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public ReqState getReqState() {
		return reqState;
	}
	public void setReqState(ReqState reqState) {
		this.reqState = reqState;
	}
	
	

}
