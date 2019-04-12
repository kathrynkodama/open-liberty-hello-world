package io.openliberty.sample.system;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class HelloWorldConfig {

  @Inject
  @ConfigProperty(name = "io_openliberty_guides_port_number")
  private int portNumber;

  @Inject
  @ConfigProperty(name = "io_openliberty_guides_inventory_inMaintenance")
  private Provider<Boolean> inMaintenance;

  public boolean isInMaintenance() {
    return inMaintenance.get();
  }

  public int getPortNumber() {
    return portNumber;
  }
}
