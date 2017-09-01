package info.eivanov.weatherforecastr.repository;

import android.content.SharedPreferences;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.eivanov.weatherforecastr.model.City;

/**
 * Created by killer on 9/1/17.
 */


public class CurrentLocationsRepoImpl implements CurrentLocationsRepo {

    private final SharedPreferences prefs;

    public CurrentLocationsRepoImpl(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public List<City> getLocations() {
        return null;
    }

    @Override
    public List<City> searchCityByName(String name) {
        return null;
    }

    @Override
    public City getCityById(long id) {
        return null;
    }

    @Override
    public void deleteCityById(long id) {

    }

    @Override
    public void addCity(City city) {

    }
}
