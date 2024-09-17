package com.useo.webserviceweatherapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Custom exception class to handle cases where weather data is not found.
 * Is used to indicate that the requested weather information could not be found in the system.

 * The @ResponseStatus annotation ensures that when this exception is thrown,
 * an HTTP 404 Not Found status is returned in the response.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WeatherNotFoundException extends RuntimeException {
    public WeatherNotFoundException(String message) {
        super(message);
    }
}
