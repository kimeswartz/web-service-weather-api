package com.useo.webserviceweatherapi.service;

import com.useo.webserviceweatherapi.exception.WeatherNotFoundException;
import com.useo.webserviceweatherapi.model.OpenWeatherMapResponse;
import com.useo.webserviceweatherapi.model.WeatherInformation;
import com.useo.webserviceweatherapi.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    public WeatherService(RestTemplate restTemplate, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplate;
        this.weatherRepository = weatherRepository;
    }

    public WeatherInformation getWeatherData(String cityName) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", weatherApiUrl, cityName, apiKey);
        try {
            OpenWeatherMapResponse response = restTemplate.getForObject(url, OpenWeatherMapResponse.class);
            if (response == null || response.getMain() == null || response.getWeather() == null) {
                throw new WeatherNotFoundException("Weather data not found for city: " + cityName);
            }

            WeatherInformation weatherInfo = new WeatherInformation();
            weatherInfo.setName(response.getCityName());
            weatherInfo.setTemperature(response.getMain().getTemp());
            weatherInfo.setDescription(response.getWeather()[0].getDescription());

            return weatherRepository.save(weatherInfo);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching weather data", e);
        }
    }

    public WeatherInformation getWeatherById(Long id) {
        return weatherRepository.findById(id)
                .orElseThrow(() -> new WeatherNotFoundException("Weather data not found with ID: " + id));
    }

    public void deleteWeatherById(Long id) {
        if (!weatherRepository.existsById(id)) {
            throw new WeatherNotFoundException("Weather data not found with ID: " + id);
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
                .orElseThrow(() -> new WeatherNotFoundException("Weather data not found with ID: " + id));
    }
}