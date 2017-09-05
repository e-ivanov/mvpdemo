package info.eivanov.weatherforecastr.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.fragments.AddNewLocationFragment;
import info.eivanov.weatherforecastr.fragments.LocationsListFragment;
import info.eivanov.weatherforecastr.fragments.ViewCurrentWeatherFragment;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;


public class MainActivity extends Activity implements Navigator{

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
            if(currentLocationsRepo.getLocations().size() > 0){
                showScreen(LocationsListFragment.newInstance(), LocationsListFragment.TAG, false);
            }else{
                showScreen(AddNewLocationFragment.newInstance(), AddNewLocationFragment.TAG, false);
            }
        }
        WeatherForecastrApp.getApp(this).getApplicationComponent().inject(this);
        Toast.makeText(this, currentLocationsRepo.toString(), Toast.LENGTH_LONG).show();
    }

    public void showScreen(Fragment view, String tag, boolean addToHistory) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, view, tag);
        if(addToHistory){
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    @Override
    public void showCurrentWeatherScreen(long cityId) {
        showScreen(ViewCurrentWeatherFragment.newInstance(cityId), ViewCurrentWeatherFragment.TAG, true);
    }

    @Override
    public void showCurrentLocationsScreen() {
        showScreen(LocationsListFragment.newInstance(), LocationsListFragment.TAG, false);
    }

    @Override
    public void showAddNewLocationScreen() {
        showScreen(AddNewLocationFragment.newInstance(), AddNewLocationFragment.TAG, true);
    }
}
