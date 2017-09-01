package info.eivanov.weatherforecastr.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepoImpl;


public class MainActivity extends Activity {

    @Inject
    CurrentLocationsRepo currentLocationsRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherForecastrApp.getApp(this).getRepositoryComponent().inject(this);
        Toast.makeText(this, currentLocationsRepo.toString(), Toast.LENGTH_LONG).show();
    }
}
