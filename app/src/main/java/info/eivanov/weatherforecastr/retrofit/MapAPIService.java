package info.eivanov.weatherforecastr.retrofit;

import java.util.List;

import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by killer on 9/1/17.
 */

public interface MapAPIService {

    @GET("weather")
    Observable<WeatherForecastResponse> getCurrentWeatherForCity(@Query("id") long id);

    @GET("weather")
    Observable<List<City>> getCitiesByName(@Query("q") String name);
}
