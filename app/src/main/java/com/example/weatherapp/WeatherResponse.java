package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.concurrent.locks.Condition;

public class WeatherResponse {

    @SerializedName("current")
    private CurrentWeather current;

    public CurrentWeather getCurrent() {
        return current;
    }

}
