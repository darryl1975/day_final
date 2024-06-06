package sg.edu.nus.iss.day39.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
        .info(new Info()
        .title("My OpenAPI documentation - Day 39 Lecture")
        .description("CSF Day 39")
        .version("version 1.0"));
    }
}