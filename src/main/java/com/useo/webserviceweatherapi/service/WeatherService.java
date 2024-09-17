package com.useo.webserviceweatherapi.service;

import com.useo.webserviceweatherapi.model.WeatherInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final String weatherApiUrl;
    private final String apiKey;

    @Autowired
    public WeatherService(RestTemplate restTemplate, String weatherApiUrl, String apiKey) {
        this.restTemplate = restTemplate;
        this.weatherApiUrl = weatherApiUrl;
        this.apiKey = apiKey;
    }

    public WeatherInformation getWeatherData(double lat, double lon) {
        String url = String.format("%s?lat=%f&lon=%f&appid=%s", weatherApiUrl, lat, lon, apiKey);
        return restTemplate.getForObject(url, WeatherInformation.class);
    }
}
