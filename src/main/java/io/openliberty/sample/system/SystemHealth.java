package io.openliberty.sample.system;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class SystemHealth implements HealthCheck {
  @Override
  public HealthCheckResponse call() {
    if (!System.getProperty("wlp.server.name").equals("helloWorldServer")) {
      return HealthCheckResponse.named(SystemResource.class.getSimpleName())
                                .withData("default server", "not available").down()
                                .build();
    }
    return HealthCheckResponse.named(SystemResource.class.getSimpleName())
                              .withData("helloWorld server", "available").up().build();
  }
}