package borges.dimitrus.rootcanalapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RootcanalapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RootcanalapiApplication.class, args);
    }

    @GetMapping("hello")
    public String hello(@RequestParam(value="myName", defaultValue = "ANyName") String name){
        return String.format("Hello %S!", name);
    }

}
