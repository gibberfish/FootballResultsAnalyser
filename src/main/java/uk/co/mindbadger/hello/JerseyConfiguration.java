package uk.co.mindbadger.hello;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        register(HelloJaxRs.class);
        register(GetSeasonsJaxRs.class);
    }
}
