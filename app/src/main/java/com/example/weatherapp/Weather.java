package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("current")
    private CurrentWeather currentWeather;

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public static class CurrentWeather {
        @SerializedName("condition")
        private Condition condition;

        public Condition getCondition() {
            return condition;
        }
    }

    public static class Condition {
        @SerializedName("text")
        private String description;

        public String getDescription() {
            return description;
        }
    }
}
