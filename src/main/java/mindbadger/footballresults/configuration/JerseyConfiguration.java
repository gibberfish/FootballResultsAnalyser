package mindbadger.footballresults.configuration;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;

import javax.ws.rs.ApplicationPath;

import mindbadger.footballresults.api.SeasonsAPI;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/api")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
    	registerEndpoints();
    	configureSwagger();
    }
    
    private void configureSwagger() {
        register(ApiListingResource.class);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/api/");
        beanConfig.setResourcePackage("mindbadger.footballresults.api");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
        
        register(ApiListingResource.class);
	}

	private void registerEndpoints() {
    	register(SeasonsAPI.class);
    }
}
