package info.eivanov.weatherforecastr.repository;

import android.util.Log;
import android.util.LruCache;

import java.util.List;

import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import info.eivanov.weatherforecastr.retrofit.MapAPIService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;
import timber.log.Timber;

/**
 * Created by killer on 9/1/17.
 */

public class GetWeatherInfoRepoImpl implements GetWeatherInfoRepo {

    private static final String WEATHER_CACHE_TAG = "weather_id_";
    private static final String SEARCH_CITY_CACHE_TAG = "city_search_q_";
    private final LruCache<String, Observable<?>> cache = new LruCache<>(50);

    private final MapAPIService mapAPIService;

    public GetWeatherInfoRepoImpl(MapAPIService mapAPIService) {
        this.mapAPIService = mapAPIService;
    }

    @Override
    public Observable<WeatherForecastResponse> getWeatherForecastForCity(long id) {
        return cacheObservable(mapAPIService.getCurrentWeatherForCity(id),
                               WEATHER_CACHE_TAG+id);
    }

    @Override
    public Observable<City> searchLocations(String searchQuery) {
        return cacheObservable(mapAPIService.getCitiesByName(searchQuery),
                               SEARCH_CITY_CACHE_TAG+searchQuery);
    }

    private <T>Observable<T> cacheObservable(Observable<T> observable, String tag){
        Observable<T> fromCache = (Observable<T>)cache.get(tag);
        if(fromCache != null){
            Timber.d( "THIS IS A CACHE HIT!");
            return fromCache;
        }

        ReplaySubject<T> subject = ReplaySubject.create();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(subject);
        fromCache = subject;
        cache.put(tag, fromCache);
        Timber.d("WEATHER_REPO", "THIS IS A CACHE MISSSS!");
        return fromCache;
    }
}
