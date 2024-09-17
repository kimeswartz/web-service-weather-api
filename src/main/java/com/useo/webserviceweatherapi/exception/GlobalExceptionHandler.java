package com.useo.webserviceweatherapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
* Purpose to centralizes the handling of exceptions that occur across the entire application.
* Instead of handling exceptions individually in each controller method.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles WeatherNotFoundException
    @ExceptionHandler(WeatherNotFoundException.class)
    public ResponseEntity<String> handleWeatherNotFoundException(WeatherNotFoundException ex) {
        // Returns a 404 Not Found status with the exception message
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Handles all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Returns a 500 Internal Server Error status
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
    }
}
