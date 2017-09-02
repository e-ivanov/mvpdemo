package info.eivanov.weatherforecastr.di.modules;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepoImpl;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepoImpl;
import info.eivanov.weatherforecastr.retrofit.MapAPIService;

/**
 * Created by killer on 9/1/17.
 */

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    CurrentLocationsRepo providesCurrentLocationsRepo(SharedPreferences prefs) {
        return new CurrentLocationsRepoImpl(prefs);
    }

    @Provides
    @Singleton
    GetWeatherInfoRepo providesWeatherInfoRepo(MapAPIService service){
        return new GetWeatherInfoRepoImpl(service);
    }
}
