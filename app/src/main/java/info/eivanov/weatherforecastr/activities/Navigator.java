package info.eivanov.weatherforecastr.activities;


import android.app.Fragment;

/**
 * Created by killer on 9/3/17.
 */

public interface Navigator {
    void showCurrentWeatherScreen(long cityId);
    void showCurrentLocationsScreen();
    void showAddNewLocationScreen();
}
