package uk.co.mindbadger.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping("/springapi/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
}
