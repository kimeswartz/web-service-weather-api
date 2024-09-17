package com.useo.webserviceweatherapi.repository;

import com.useo.webserviceweatherapi.model.WeatherInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherInformation, Long> {
}
