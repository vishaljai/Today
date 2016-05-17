package org.cyb.training.java.rs.v6.view;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.cyb.training.java.rs.v6.model.Requirement;
import org.cyb.training.java.rs.v6.utils.Constants.ReqState;

@XmlRootElement
public class RequestReqView extends RequirementView{
	
	@NotNull
	private double estimate;
	private double efforts;
	private ReqState state;
	
	public RequestReqView() {
		super();
	}
	
	public RequestReqView (Requirement r) {
		id = r.getId();
		title = r.getTitle();
		description = r.getDescription();
		owner = r.getOwner().getUserId();
		estimate = r.getEstimate();
		efforts = r.getEfforts();
		}

	@NotNull (message="Request estimate can not be null")
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

	public ReqState getState() {
		return state;
	}

	public void setState(ReqState state) {
		this.state = state;
	}
	
	

}
