package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView res_temperature;
    private TextView res_des;
    private Button main_btn;
    private EditText user_field;

    private WeatherApi weatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_field = findViewById(R.id.user_field);
        res_des = findViewById(R.id.res_des);
        res_temperature = findViewById(R.id.res_temperature);
        main_btn = findViewById(R.id.main_btn);

        weatherApi = RetrofitClient.getClient().create(WeatherApi.class);

        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_field.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, R.string.hint_user_field, Toast.LENGTH_LONG).show();
                } else {
                    String city = user_field.getText().toString();
                    String apiKey = "d2af3fca104243b59ae90028243101";

                    getCurrentWeather(city, apiKey);
                }
            }
        });
    }

    private void getCurrentWeather(String city, String apiKey) {
        Call<WeatherResponse> call = weatherApi.getCurrentWeather(city, apiKey);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        CurrentWeather currentWeather = weatherResponse.getCurrent();
                        //CurrentWeather c1 = weatherResponse.getCondition();
                        if (currentWeather != null) {
                            double temperature = currentWeather.getTemperature();
                          //  String description = c1.getDescription(); // Здесь используем getDescription из CurrentWeather
                            res_temperature.setText("Temperature: " + temperature);
                           // res_des.setText("Description: " + description);
                        } else {
                            res_temperature.setText("Failed to get weather data.");
                            //res_des.setText("");
                        }
                    } else {
                        res_temperature.setText("Failed to get weather data.");
                        res_des.setText("");
                    }
                } else {
                    res_temperature.setText("Failed to get weather data.");
                    res_des.setText("");
                }
            }


            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                res_temperature.setText("Failed to connect to the server.");
                res_des.setText("");
            }
        });
    }
}
