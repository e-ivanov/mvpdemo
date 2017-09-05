package info.eivanov.weatherforecastr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;

public class SplashActivity extends AppCompatActivity {

    @Inject
    CurrentLocationsRepo currentLocationsRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        WeatherForecastrApp.getApp(this).getApplicationComponent().inject(this);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
