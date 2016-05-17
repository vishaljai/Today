package org.cyb.training.java.rs.v5.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyb.training.java.rs.v5.utils.Constants;
import org.cyb.training.java.rs.v5.utils.Constants.ReqState;


@XmlRootElement
public class Requirement {
	int id;
	String title;
	String description;
	private double estimate;
	private double efforts;
	private User owner;
	private Constants.ReqState reqstate;
	private boolean isDeleted;
	private Date lastModified;
	
	
	
	public Requirement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Requirement(int id, String title, String description,
			double estimate, double efforts, User owner, ReqState reqstate, Date lastModified) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.estimate = estimate;
		this.efforts = efforts;
		this.owner = owner;
		this.reqstate = reqstate;
		this.lastModified = lastModified;
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
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Constants.ReqState getReqstate() {
		return reqstate;
	}
	public void setReqstate(Constants.ReqState reqstate) {
		this.reqstate = reqstate;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	
	
}
