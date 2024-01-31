package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {

    @SerializedName("temp_c")
    private double temperature;
    @SerializedName("text")
    private String description;

    public double getTemperature() {
        return temperature;
    }

    public String getDescription(){
        return description;
    }
}
