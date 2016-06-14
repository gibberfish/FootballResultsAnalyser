package uk.co.mindbadger.hello;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class HelloJaxRs {
    @GET
    public String message() {
        return "HelloJaxRs";
    }
}
