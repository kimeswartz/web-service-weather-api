package com.useo.webserviceweatherapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // URL for weather API endpoint
    @Value("${weather.api.url}")
    private String weatherApiUrl;

    // API key
    @Value("${weather.api.key}")
    private String apiKey;

    // Bean to make HTTP requests to external services
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Bean to provide the weather API URL
    @Bean
    public String weatherApiUrl() {
        return weatherApiUrl;
    }

    // Bean to provide the weather API key
    @Bean
    public String apiKey() {
        return apiKey;
    }
}