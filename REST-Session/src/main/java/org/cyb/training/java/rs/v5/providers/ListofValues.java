package org.cyb.training.java.rs.v5.providers;

import java.io.Serializable;
import java.util.List;

import org.cyb.training.java.rs.v5.utils.Constants.ReqState;
import org.cyb.training.java.rs.v5.view.RequestReqView;
import org.cyb.training.java.rs.v5.view.ResponseReqView;

public class ListofValues implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public  static final String MIME_TYPE="application/lov";
	private String[] values;
	
	public ListofValues() {
		
	}
	public ListofValues(String commaDilimtedStr) {
		values = commaDilimtedStr.split(",");
	}
	
	public RequestReqView toRequestView() {
		
		RequestReqView req = new RequestReqView();
		int id = Integer.parseInt(values[0]);
		String title = values[1];
		String description = values[2];
		int owner = Integer.parseInt(values[3]);
		double estimate = Double.parseDouble(values[4]);
		double efforts = Double.parseDouble(values[5]);
		String state = values[6].trim();
		
		req.setId(id);
		req.setTitle(title);
		req.setDescription(description);
		req.setOwner(owner);
		req.setEstimate(estimate);
		req.setEfforts(efforts);
		req.setState(toReqState(state));
		
		return req;
	}
	private ReqState toReqState(String reqState) {
		ReqState  state;
		switch (reqState) {
		case "Backlog" :
			state = ReqState.Backlog;
			break;
		case "Active" :
			state = ReqState.Active;
			break;
		case "Closed" :
			state = ReqState.Closed;
			break;
		default:
			state = ReqState.Backlog;
		}
		return state;
			
		}
	private static String fromReqState (ReqState reqState) {
		String state;
		switch (reqState) {
		case Backlog :
			state = "Backlog";
			break;
		case Active :
			state = "Active";
			break;
		case Closed:
			state = "Closed";
			break;
		default:
			state = "Backlog";
			break;
		}
		return state;
		
  }
	public String getMIMEType () {
		return MIME_TYPE;
	}
	/*public static ListofValues fromRequestView (RequestReqView req) {
		String buffer;
		
		buffer = String.valueOf(req.getId());
		buffer += "," + req.getTitle();
		buffer += "," + req.getDescription();
		buffer += "," + String.valueOf(req.getOwner());
		buffer += "," + String.valueOf(req.getEstimate());
		buffer += "," + String.valueOf(req.getEfforts());
		buffer += "," + String.valueOf(fromReqState(req.getState()));
		
		ListofValues val = new ListofValues(buffer);
		return val;
	}*/
	
	public static ListofValues fromResponseView (ResponseReqView res) {
		String buffer;
		
		buffer = String.valueOf(res.getId());
		buffer += "," + res.getTitle();
		buffer += "," + res.getDescription();
		buffer += "," + String.valueOf(res.getOwner());		
		buffer += "," + String.valueOf(res.getProgress());
		buffer += "," + String.valueOf(fromReqState(res.getReqstate()));
		
		ListofValues val = new ListofValues(buffer);
		return val;
	}
	public String toString() {
		String line ="";
		for (String val : values) {
			line += val + ",";
			
		}
		line = line.substring(0, line.length()-1);
		return line;
	}
}


