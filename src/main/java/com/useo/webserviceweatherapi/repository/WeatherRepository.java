package com.useo.webserviceweatherapi.repository;

import com.useo.webserviceweatherapi.model.WeatherInformation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends JpaRepository, providing CRUD operations and additional
 * database query capabilities for the WeatherInformation entity. Spring Data JPA
 * automatically implements this interface, allowing you to perform database
 * operations without boilerplate code.
 */

public interface WeatherRepository extends JpaRepository<WeatherInformation, Long> {
}
