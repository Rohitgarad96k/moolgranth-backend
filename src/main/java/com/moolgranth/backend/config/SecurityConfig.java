package com.moolgranth.backend.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. Tell Spring Security to use our custom CORS rules below
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
            .csrf(AbstractHttpConfigurer::disable) 
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() 
            );
        return http.build();
    }

    // 2. The Global CORS Policy
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // THE FIX: Allow your live website domains AND your local testing environment!
        configuration.setAllowedOrigins(Arrays.asList(
        "https://moolgranth.com", 
            "https://www.moolgranth.com", 
            "http://localhost:5173"
        )); 
        
        // Explicitly allow methods
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); 
        
        // Allow standard headers that React and browsers send
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept", "Origin", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        
        // Ensure browser allows frontend to read the response
        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Apply these rules to EVERY endpoint in your API
        source.registerCorsConfiguration("/**", configuration); 
        
        return source;
    }
}