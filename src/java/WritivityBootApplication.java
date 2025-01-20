package src.java;

// Lien : http://localhost:8080/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EntityScan("src.java.model")
@EnableJpaRepositories("src.java.Utils")
@RestController
public class WritivityBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WritivityBootApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("ça marche ?", name);
    }

}
