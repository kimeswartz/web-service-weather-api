package com.useo.webserviceweatherapi.service;

import com.useo.webserviceweatherapi.model.WeatherInformation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testGetWeatherData() {
        // Prepare mock response
        WeatherInformation mockWeatherInfo = new WeatherInformation();
        mockWeatherInfo.setName("Stockholm");
        // Set other properties of mockWeatherInfo as needed

        Mockito.when(restTemplate.getForObject(anyString(), Mockito.eq(WeatherInformation.class)))
                .thenReturn(mockWeatherInfo);

        // Call the method under test
        WeatherInformation result = weatherService.getWeatherData(59.3293, 18.0686); // Stockholm coordinates

        // Verify results
        assertNotNull(result);
        assertNotNull(result.getName());
        // Add more assertions as needed
    }
}
