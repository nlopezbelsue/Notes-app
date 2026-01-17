package com.ensolvers.notes.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        // Set JVM timezone to UTC before creating the connection
        System.setProperty("user.timezone", "UTC");
        java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"));
        
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/notesdb");
        config.setUsername("notes_user");
        config.setPassword("notes_pass");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        
        // Pass connection properties to prevent timezone negotiation
        Properties props = new Properties();
        props.setProperty("user", "notes_user");
        props.setProperty("password", "notes_pass");
        props.setProperty("serverTimezone", "UTC");
        config.setDataSourceProperties(props);
        
        return new HikariDataSource(config);
    }
}
