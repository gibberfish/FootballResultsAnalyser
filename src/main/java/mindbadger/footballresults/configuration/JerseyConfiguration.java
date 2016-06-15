package mindbadger.footballresults.configuration;

import javax.ws.rs.ApplicationPath;

import mindbadger.footballresults.api.SeasonsAPI;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(SeasonsAPI.class);
    }
}
