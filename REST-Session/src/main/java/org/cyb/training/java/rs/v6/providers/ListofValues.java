package org.cyb.training.java.rs.v6.providers;

import java.io.Serializable;
import java.util.List;

import org.cyb.training.java.rs.v6.utils.Constants.ReqState;
import org.cyb.training.java.rs.v6.view.RequestReqView;

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
		int state = Integer.parseInt(values[6]);
		
		req.setId(id);
		req.setTitle(title);
		req.setDescription(description);
		req.setOwner(owner);
		req.setEstimate(estimate);
		req.setEfforts(efforts);
		req.setState(toReqState(state));
		
		return req;
	}
	private ReqState toReqState(int inState) {
		ReqState  state;
		switch (inState) {
		case 0 :
			state = ReqState.Backlog;
			break;
		case 1 :
			state = ReqState.Active;
			break;
		case 2 :
			state = ReqState.Closed;
			break;
		default:
			state = ReqState.Backlog;
		}
		return state;
			
		}
	private static int fromReqState (ReqState state) {
		int inState = 0;
		switch (state) {
		case Backlog :
			inState = 0;
			break;
		case Active :
			inState = 1;
			break;
		case Closed:
			inState = 2;
			break;
		default:
			inState = 3;
			break;
		}
		return inState;
		
  }
	public String getMIMEType () {
		return MIME_TYPE;
	}
	public static ListofValues fromRequestView (RequestReqView req) {
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


