package org.cyb.training.java.rs.view;

import javax.xml.bind.annotation.XmlRootElement;

import org.cyb.training.java.rs.model.RequirementV3;
import org.cyb.training.java.rs.utils.Constants;

@XmlRootElement
public class ResponseReqView extends RequirementView{

	private double progress;
	private Constants.ReqState reqstate;
	
	public ResponseReqView() {
		super();
	}

	public ResponseReqView(RequirementV3 req) {
		id = req.getId();
		title = req.getTitle();
		description = req.getDescription();
		owner = req.getOwner().getUserId();
		progress = (100 * req.getEfforts())/req.getEstimate();
		reqstate = req.getReqstate();
	}
	public double getProgress() {
		return progress;
	}
	public void setProgress(double progress) {
		this.progress = progress;
	}
	public Constants.ReqState getReqstate() {
		return reqstate;
	}
	public void setReqstate(Constants.ReqState reqstate) {
		this.reqstate = reqstate;
	}
	
}
