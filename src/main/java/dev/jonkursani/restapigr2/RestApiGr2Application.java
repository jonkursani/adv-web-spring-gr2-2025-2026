package dev.jonkursani.restapigr2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity // Annotation based authorization
@EnableScheduling // Enable scheduled tasks (Jobs)
public class RestApiGr2Application {

    public static void main(String[] args) {
        SpringApplication.run(RestApiGr2Application.class, args);
    }

}
