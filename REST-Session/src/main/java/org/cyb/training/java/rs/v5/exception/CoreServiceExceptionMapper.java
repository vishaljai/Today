package org.cyb.training.java.rs.v5.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CoreServiceExceptionMapper implements ExceptionMapper<CoreServiceException>{

	public Response toResponse(CoreServiceException e) {
		
		return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build(); 
	}
	

}
