package dev.jonkursani.restapigr2.configs;

import org.flywaydb.core.Flyway;
import org.springframework.boot.flyway.autoconfigure.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
    @Bean
    public FlywayMigrationStrategy flywayMigration() {
//        return flyway -> flyway.migrate();
        return Flyway::migrate;
    }
}