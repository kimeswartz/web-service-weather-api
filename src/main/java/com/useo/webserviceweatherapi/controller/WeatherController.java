package com.useo.webserviceweatherapi.controller;

import com.useo.webserviceweatherapi.model.WeatherInformation;
import com.useo.webserviceweatherapi.service.WeatherService;

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

    /**
     * Handles HTTP PUT requests to update a weather record.

     * - @PutMapping("/{id}"): Maps the PUT request to /api/weather/{id}, where {id} is a variable part of the URI.
     * - @PathVariable Long id: Extracts the dynamic 'id' from the URL. This 'id' represents the identifier of the weather record to be updated.
     * - @RequestBody WeatherInformation updatedInfo: Maps the incoming JSON request body to a WeatherInformation object,
     *   which contains the updated weather data that will replace the existing record.

     * The method interacts with the WeatherService to perform the update operation. If successful, it returns the updated
     * WeatherInformation object along with an HTTP 200 OK response.
     */

    @PutMapping("/{id}")
    public ResponseEntity<WeatherInformation> updateWeather(@PathVariable Long id, @RequestBody WeatherInformation updatedInfo) {
        WeatherInformation weather = weatherService.updateWeather(id, updatedInfo);
        return ResponseEntity.ok(weather);
    }
}
