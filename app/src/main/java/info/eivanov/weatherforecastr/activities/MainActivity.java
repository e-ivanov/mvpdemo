package info.eivanov.weatherforecastr.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.fragments.AddNewLocationFragment;
import info.eivanov.weatherforecastr.fragments.LocationsListFragment;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;


public class MainActivity extends Activity {

    @Inject
    CurrentLocationsRepo currentLocationsRepo;

    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherForecastrApp.getApp(this).getApplicationComponent().inject(this);
        fragmentContainer = (FrameLayout)findViewById(R.id.fragmentContainer);
        if(getFragmentManager().findFragmentById(R.id.fragmentContainer) == null){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if(currentLocationsRepo.getLocations().size() > 0){
                ft.add(R.id.fragmentContainer, LocationsListFragment.newInstance()).commit();
            }else{
                ft.add(R.id.fragmentContainer, AddNewLocationFragment.newInstance()).commit();
            }

        }
        WeatherForecastrApp.getApp(this).getApplicationComponent().inject(this);
        Toast.makeText(this, currentLocationsRepo.toString(), Toast.LENGTH_LONG).show();
    }
}
