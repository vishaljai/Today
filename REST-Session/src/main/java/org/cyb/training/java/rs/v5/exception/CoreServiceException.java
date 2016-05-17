package org.cyb.training.java.rs.v5.exception;

import java.io.Serializable;


public class CoreServiceException extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public CoreServiceException () {
		super();
	}
	public CoreServiceException(String msg) {
		super(msg);
	}
	public CoreServiceException (String msg, Exception e) {
		
		super(msg, e);
	}
	
}