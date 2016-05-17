package org.cyb.training.java.rs.v6.exception;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;



@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

	public Response toResponse(ConstraintViolationException ex) {
		String error ="";
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
        	if (constraintViolation !=null && constraintViolation.getMessage() != null) {
        		error = constraintViolation.getMessage();
        		break;
        	}
        }
        return Response.status(Status.PRECONDITION_FAILED).entity(error)
                .build();
	}
	
}
  