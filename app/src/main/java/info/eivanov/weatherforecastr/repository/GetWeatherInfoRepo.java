package info.eivanov.weatherforecastr.repository;

import java.util.List;

import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import io.reactivex.Observable;


/**
 * Created by killer on 9/1/17.
 */

public interface GetWeatherInfoRepo {

    public Observable<WeatherForecastResponse> getWeatherForecastForCity(long id);
    public Observable<City> searchLocations(String searchQuery);
}
