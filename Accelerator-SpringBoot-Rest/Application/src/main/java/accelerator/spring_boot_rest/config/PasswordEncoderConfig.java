package accelerator.spring_boot_rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This class is used to configure the password encoder for the application.
 */
@Configuration
@SuppressWarnings("PMD.AtLeastOneConstructor")
public class PasswordEncoderConfig {

    /**
     * This method is used to create a BCryptPasswordEncoder object to encode passwords.
     *
     * @return a BCryptPasswordEncoder object to encode passwords
     */
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
