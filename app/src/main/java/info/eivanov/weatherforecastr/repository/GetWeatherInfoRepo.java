package info.eivanov.weatherforecastr.repository;

import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import rx.Observable;

/**
 * Created by killer on 9/1/17.
 */

public interface GetWeatherInfoRepo {

    public Observable<WeatherForecastResponse> getWeatherForecastForCity(long id);
}
