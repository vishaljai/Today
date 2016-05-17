package org.cyb.training.java.rs.v5.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(ListofValues.MIME_TYPE)
public class LovReader implements MessageBodyReader<ListofValues> {

	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return ListofValues.class.isAssignableFrom(type);
	}

	public ListofValues readFrom(Class<ListofValues> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(entityStream));
		line = br.readLine();
		ListofValues vals = new ListofValues(line);
		return vals;
		
	}

}
