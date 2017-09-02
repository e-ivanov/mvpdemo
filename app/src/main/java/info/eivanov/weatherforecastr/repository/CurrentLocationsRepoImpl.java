package info.eivanov.weatherforecastr.repository;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import info.eivanov.weatherforecastr.model.City;

/**
 * Created by killer on 9/1/17.
 */


public class CurrentLocationsRepoImpl extends BaseRepository<Long, City> implements CurrentLocationsRepo {

    private final SharedPreferences prefs;
    private final Gson gson;
    private City defaultLocation = null;


    public CurrentLocationsRepoImpl(SharedPreferences prefs, Gson gson) {
        this.prefs = prefs;
        this.gson = gson;
        initCache();
    }

    private void initCache(){
        for(Map.Entry<String, ?> entry : prefs.getAll().entrySet()){
            City city = gson.fromJson(String.valueOf(entry.getValue()), City.class);
            cache.put(city.getId(), city);
            if(city.isDefault()){
                this.defaultLocation = city;
            }
        }
    }

    @Override
    public Collection<City> getLocations() {
        return cache.values();

    }

    @Override
    public List<City> searchCityByName(String name) {
        return null;
    }

    @Override
    public City getCityById(long id) {
        return cache.get(id);
    }

    @Override
    public void deleteCityById(long id) {
        cache.remove(id);
        prefs.edit().remove(String.valueOf(id)).commit();
    }

    @Override
    public void addCity(City city) {
        cache.put(city.getId(), city);
        prefs.edit().putString(String.valueOf(city.getId()), gson.toJson(city));
    }

    @Override
    public City getDefaultLocation() {
        return this.defaultLocation;
    }

    @Override
    public void setDefaultLocation(City city) {
        this.defaultLocation.setDefault(false);
        this.prefs.edit().putString(String.valueOf(this.defaultLocation.getId()), gson.toJson(city));
        cache.put(this.defaultLocation.getId(), this.defaultLocation);

        this.defaultLocation = city;

        this.defaultLocation.setDefault(true);
        this.prefs.edit().putString(String.valueOf(city.getId()), gson.toJson(city));
        cache.put(city.getId(), city);
    }
}
