package info.eivanov.weatherforecastr.retrofit;

import java.util.List;

import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by killer on 9/1/17.
 */

public interface MapAPIService {

    @GET("/api.openweathermap.org/data/2.5/weather")
    Observable<WeatherForecastResponse> getCurrentWeatherForCity(@Query("id") long id);

    @GET("api.openweathermap.org/data/2.5/weather")
    Observable<List<City>> getCitiesByName(@Query("q") String name);
}
