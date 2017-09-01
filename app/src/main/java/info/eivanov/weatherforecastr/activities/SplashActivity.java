package info.eivanov.weatherforecastr.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepoImpl;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {

    @Inject
    CurrentLocationsRepo currentLocationsRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherForecastrApp.getApp(this).getRepositoryComponent().inject(this);
        Toast.makeText(this, currentLocationsRepo.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
