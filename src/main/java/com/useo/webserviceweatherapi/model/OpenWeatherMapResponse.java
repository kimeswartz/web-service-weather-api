package com.useo.webserviceweatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenWeatherMapResponse {

    /**
     * This class represents the structure of the response from the OpenWeatherMap API.
     * This class is used to map the JSON response from the OpenWeatherMap API
     * into Java objects, making it easier to work with the data in the application.
     */

    @JsonProperty("name")
    private String cityName;

    @JsonProperty("main")
    private Main main;

    @JsonProperty("weather")
    private Weather[] weather;

    // Getters and setters
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public static class Main {
        private double temp;

        // Getters and setters
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }

    public static class Weather {
        private String description;

        // Getters and setters
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

