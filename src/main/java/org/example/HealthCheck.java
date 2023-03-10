package org.example;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "healthcheck" path)
 */
@Path("healthcheck")
public class HealthCheck {

    private static final Logger LOGGER = Logger.getLogger(HealthCheck.class);

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String HealthResponse(){
        LOGGER.info("Getting the HealthResponse");
        return "OK";
    }
}
