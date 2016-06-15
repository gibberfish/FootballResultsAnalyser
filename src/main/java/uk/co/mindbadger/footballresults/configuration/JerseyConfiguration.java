package uk.co.mindbadger.footballresults.configuration;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import uk.co.mindbadger.footballresults.api.GetSeasonsJaxRs;

@Component
@ApplicationPath("/api")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(GetSeasonsJaxRs.class);
    }
}
