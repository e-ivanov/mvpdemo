package info.eivanov.weatherforecastr.di.components;

import javax.inject.Singleton;

import dagger.Component;
import info.eivanov.weatherforecastr.activities.MainActivity;
import info.eivanov.weatherforecastr.activities.SplashActivity;
import info.eivanov.weatherforecastr.di.modules.ApplicationModule;
import info.eivanov.weatherforecastr.di.modules.NetworkModule;
import info.eivanov.weatherforecastr.di.modules.RepositoryModule;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;

/**
 * Created by killer on 8/30/17.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent {

    CurrentLocationsRepo getCurrentLocationRepo();

    GetWeatherInfoRepo getWeatherRepo();

    void inject(SplashActivity activity);

    void inject(MainActivity activity);

}
