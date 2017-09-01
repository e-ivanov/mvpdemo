package info.eivanov.weatherforecastr.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;


public class MainActivity extends Activity {

    @Inject
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherForecastrApp.getApp(this).getNetComponent().inject(this);
        Toast.makeText(this, preferences.toString(), Toast.LENGTH_LONG).show();
    }
}
