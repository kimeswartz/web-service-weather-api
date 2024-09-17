package com.useo.webserviceweatherapi.controller;

import com.useo.webserviceweatherapi.model.WeatherInformation;
import com.useo.webserviceweatherapi.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherInformation> getWeatherById(@PathVariable Long id) {
        WeatherInformation weather = weatherService.getWeatherById(id);
        return ResponseEntity.ok(weather);
    }

    @PostMapping("/search")
    public ResponseEntity<WeatherInformation> getWeatherData(@RequestParam String cityName) {
        WeatherInformation weather = weatherService.getWeatherData(cityName);
        return ResponseEntity.ok(weather);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeatherById(@PathVariable Long id) {
        weatherService.deleteWeatherById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeatherInformation> updateWeather(@PathVariable Long id, @RequestBody WeatherInformation updatedInfo) {
        WeatherInformation weather = weatherService.updateWeather(id, updatedInfo);
        return ResponseEntity.ok(weather);
    }
}
