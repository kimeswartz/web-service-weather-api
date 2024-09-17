package com.useo.webserviceweatherapi.service;

import com.useo.webserviceweatherapi.WeatherService;
import com.useo.webserviceweatherapi.model.WeatherInformation;
import com.useo.webserviceweatherapi.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private WeatherRepository weatherRepository;

    @Test
    public void testGetWeatherData() {
        // Mock WeatherInformation object
        WeatherInformation mockWeatherInfo = new WeatherInformation();
        mockWeatherInfo.setName("Stockholm");
        mockWeatherInfo.setTemperature(15.0);
        mockWeatherInfo.setDescription("Clear sky");

        // Set up mock behavior
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(WeatherInformation.class)))
                .thenReturn(mockWeatherInfo);
        Mockito.when(weatherRepository.save(Mockito.any(WeatherInformation.class)))
                .thenReturn(mockWeatherInfo);

        // Call the service method
        WeatherInformation result = weatherService.getWeatherData("Stockholm");

        // Print details to the console
        System.out.println("Result from weatherService.getWeatherData('Stockholm'):");
        System.out.println("Name: " + result.getName());
        System.out.println("Temperature: " + result.getTemperature());
        System.out.println("Description: " + result.getDescription());

        // Assertions
        assertNotNull(result);
        assertEquals("Stockholm", result.getName());
        assertEquals(15.0, result.getTemperature());
        assertEquals("Clear sky", result.getDescription());
    }
}