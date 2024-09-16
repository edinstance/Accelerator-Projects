package accelerator.spring_boot_rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is used to configure CORS settings for the application.
 * It allows the frontend to make requests to the backend.
 */
@Configuration
@SuppressWarnings("PMD.AtLeastOneConstructor")
public class CORSConfig implements WebMvcConfigurer {

    /**
     * This method is used to configure CORS settings for the application.
     * It allows the frontend to make requests to the backend.
     *
     * @param registry the CorsRegistry object to configure CORS settings
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT").maxAge(-1)
                .allowedHeaders("*").allowCredentials(true);
    }
}

