package io.openliberty.sample.system;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class HelloWorldHealth implements HealthCheck {
  
  @Inject
  HelloWorldConfig config; 
 
  public boolean isHealthy() {
    if (config.isInMaintenance()) {
      return false;
    }
    try {
      String url = HelloWorldUtils.buildUrl("http", "localhost",
          Integer.parseInt(System.getProperty("default.http.port")),
          "/hello/properties");
      Client client = ClientBuilder.newClient();
      Response response = client.target(url).request(MediaType.APPLICATION_JSON)
                                .get();
      if (response.getStatus() != 200) {
        return false;
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public HealthCheckResponse call() {
    if (!isHealthy()) {
      return HealthCheckResponse.named(HelloWorldResource.class.getSimpleName())
                                .withData("services", "not available").down()
                                .build();
    }
    return HealthCheckResponse.named(HelloWorldResource.class.getSimpleName())
                              .withData("services", "available").up().build();
  }

}