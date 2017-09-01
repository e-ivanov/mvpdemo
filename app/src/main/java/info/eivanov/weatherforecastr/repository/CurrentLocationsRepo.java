package info.eivanov.weatherforecastr.repository;

import java.util.List;

import info.eivanov.weatherforecastr.model.City;

/**
 * Created by killer on 9/1/17.
 */

public interface CurrentLocationsRepo {

    public List<City> getLocations();
    public List<City> searchCityByName(String name);
    public City getCityById(long id);
    public void deleteCityById(long id);
    public void addCity(City city);
}
