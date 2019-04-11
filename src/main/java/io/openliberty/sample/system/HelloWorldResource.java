package io.openliberty.sample.system;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Path("/world")
public class HelloWorldResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getProperties() {
	      return Response.ok("Hello world!").build();
  }
}