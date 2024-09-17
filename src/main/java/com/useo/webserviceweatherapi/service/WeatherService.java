package com.useo.webserviceweatherapi;

import com.useo.webserviceweatherapi.model.WeatherInformation;
import com.useo.webserviceweatherapi.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class WeatherService {

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    public WeatherService (RestTemplate restTemplate, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplate;
        this.weatherRepository = weatherRepository;
    }

    public WeatherInformation getWeatherData(String cityName) {
        String url = String.format("%s?q=%s&appid=%s", weatherApiUrl, cityName, apiKey);
        try {
            WeatherInformation response = restTemplate.getForObject(url, WeatherInformation.class);
            if (response == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data not found");
            }
            // Save to database
            return weatherRepository.save(response);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching weather data", e);
        }
    }

    public WeatherInformation getWeatherById(Long id) {
        return weatherRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data not found"));
    }

    public void deleteWeatherById(Long id) {
        if (!weatherRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data not found");
        }
        weatherRepository.deleteById(id);
    }

    public WeatherInformation updateWeather(Long id, WeatherInformation updatedInfo) {
        return weatherRepository.findById(id)
                .map(weather -> {
                    weather.setName(updatedInfo.getName());
                    weather.setTemperature(updatedInfo.getTemperature());
                    weather.setDescription(updatedInfo.getDescription());
                    return weatherRepository.save(weather);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Weather data not found"));
    }
}
