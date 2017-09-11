package info.eivanov.weatherforecastr.fragments;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.mockito.Mockito;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.eivanov.weatherforecastr.di.modules.RepositoryModule;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepoImpl;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepoImpl;
import info.eivanov.weatherforecastr.retrofit.MapAPIService;

/**
 * Created by killer on 9/9/17.
 */

@Module
public class RepositoryMockModule{

    @Provides
    @Singleton
    public CurrentLocationsRepo providesCurrentLocationsRepo() {
        return Mockito.mock(CurrentLocationsRepo.class);
    }


    @Provides
    @Singleton
    public GetWeatherInfoRepo providesWeatherInfoRepo(){
        return Mockito.mock(GetWeatherInfoRepo.class);
    }
}
