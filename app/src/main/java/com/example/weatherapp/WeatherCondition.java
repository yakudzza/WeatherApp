package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class WeatherCondition {

    @SerializedName("condition")
    private WeatherCondition condition;

    public WeatherCondition getCondition(){
        return condition;
    }

}
