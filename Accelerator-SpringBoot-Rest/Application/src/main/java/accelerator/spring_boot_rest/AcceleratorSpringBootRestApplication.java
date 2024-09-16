package accelerator.spring_boot_rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to bootstrap the Accelerator Spring Boot REST application.
 * This class uses Spring Boot's SpringApplication to launch the application.
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Accelerator API", version = "1.0", description = "A fully functioning Springboot RestAPI accelerator"))
public class AcceleratorSpringBootRestApplication {

    /**
     * Main method to start the Accelerator Spring Boot REST application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(final String[] args) {
        SpringApplication.run(AcceleratorSpringBootRestApplication.class, args);
    }

}
