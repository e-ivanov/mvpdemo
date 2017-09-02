package info.eivanov.weatherforecastr.repository;

import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import info.eivanov.weatherforecastr.retrofit.MapAPIService;
import io.reactivex.Observable;

/**
 * Created by killer on 9/1/17.
 */

public class GetWeatherInfoRepoImpl implements GetWeatherInfoRepo {

    private final MapAPIService mapAPIService;

    public GetWeatherInfoRepoImpl(MapAPIService mapAPIService) {
        this.mapAPIService = mapAPIService;
    }

    @Override
    public Observable<WeatherForecastResponse> getWeatherForecastForCity(long id) {
        return mapAPIService.getCurrentWeatherForCity(id);
    }
}
