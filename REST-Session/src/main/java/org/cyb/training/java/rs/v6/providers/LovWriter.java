package org.cyb.training.java.rs.v6.providers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Consumes(ListofValues.MIME_TYPE)
public class LovWriter implements MessageBodyWriter<ListofValues> {

	public boolean isWriteable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		
		return ListofValues.class.isAssignableFrom(type);
	}

	public long getSize(ListofValues t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return -1;
	}

	public void writeTo(ListofValues t, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException,
			WebApplicationException {
		
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(entityStream));
		//bw.write(t.toString());
		entityStream.write(t.toString().getBytes());
	}
	

}
