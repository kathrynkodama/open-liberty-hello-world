package io.openliberty.sample.client;

import java.util.Properties;
import javax.enterprise.context.Dependent;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

// tag::annotations[]
@Dependent
@RegisterRestClient
@RegisterProvider(UnknownUrlExceptionMapper.class)
@Path("/properties")
public interface SystemClient {
  // end::annotations[]

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Properties getProperties() throws UnknownUrlException, ProcessingException;
} 