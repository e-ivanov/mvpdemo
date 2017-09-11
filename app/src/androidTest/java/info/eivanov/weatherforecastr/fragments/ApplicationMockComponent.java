package info.eivanov.weatherforecastr.fragments;

import javax.inject.Singleton;

import dagger.Component;
import info.eivanov.weatherforecastr.ApplicationMockModule;
import info.eivanov.weatherforecastr.activities.MainActivity;
import info.eivanov.weatherforecastr.activities.SplashActivity;
import info.eivanov.weatherforecastr.di.components.ApplicationComponent;
import info.eivanov.weatherforecastr.di.modules.ApplicationModule;
import info.eivanov.weatherforecastr.di.modules.NetworkModule;
import info.eivanov.weatherforecastr.di.modules.RepositoryModule;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;

/**
 * Created by killer on 9/9/17.
 */
@Singleton
@Component(modules = {ApplicationMockModule.class, NetworkMockModule.class, RepositoryMockModule.class})
public interface ApplicationMockComponent extends ApplicationComponent{
    CurrentLocationsRepo getCurrentLocationRepo();

    GetWeatherInfoRepo getWeatherRepo();

    void inject(SplashActivityTest activity);

    void inject(MainActivityTest activity);
}
