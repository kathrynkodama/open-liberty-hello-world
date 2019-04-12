package io.openliberty.sample.system;

import java.net.URL;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.MalformedURLException;
import javax.ws.rs.ProcessingException;
import java.util.Properties;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import io.openliberty.sample.client.SystemClient;
import io.openliberty.sample.client.UnknownUrlException;
import io.openliberty.sample.client.UnknownUrlExceptionMapper;

public class HelloWorldUtils {

  // tag::builder[]
  public Properties getProperties(String hostname, int portNumber) {
    String customURLString = "http://" + hostname + ":" + portNumber + "/hello";
    URL customURL = null;
    try {
      customURL = new URL(customURLString);
      SystemClient customRestClient = RestClientBuilder.newBuilder()
                                                       .baseUrl(customURL)
                                                       .register(
                                                           UnknownUrlExceptionMapper.class)
                                                       .build(SystemClient.class);
      return customRestClient.getProperties();
    } catch (ProcessingException ex) {
      handleProcessingException(ex);
    } catch (UnknownUrlException e) {
      System.err.println("The given URL is unreachable.");
    } catch (MalformedURLException e) {
      System.err.println("The given URL is not formatted correctly.");
    }
    return null;
  }
  // end::builder[]

  public void handleProcessingException(ProcessingException ex) {
    Throwable rootEx = ExceptionUtils.getRootCause(ex);
    if (rootEx != null && rootEx instanceof UnknownHostException) {
      System.err.println("The specified host is unknown.");
    } else {
      throw ex;
    }
  }

  // tag::doc[]
  /**
   * Builds the URI string to the system service for a particular host.
   * 
   * @param protocol
   *          - http or https.
   * @param host
   *          - name of host.
   * @param port
   *          - port number.
   * @param path
   *          - Note that the path needs to start with a slash!!!
   * @return String representation of the URI to the system properties service.
   */
  // end::doc[]
  public static String buildUrl(String protocol, String host, int port,
      String path) {
    try {
      URI uri = new URI(protocol, null, host, port, path, null, null);
      return uri.toString();
    } catch (Exception e) {
      System.out.println("URISyntaxException");
      return null;
    }
  }

}  