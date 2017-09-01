package info.eivanov.weatherforecastr.di.components;

import javax.inject.Singleton;

import dagger.Component;
import info.eivanov.weatherforecastr.activities.MainActivity;
import info.eivanov.weatherforecastr.activities.SplashActivity;
import info.eivanov.weatherforecastr.di.modules.ApplicationModule;
import info.eivanov.weatherforecastr.di.modules.NetworkModule;
import info.eivanov.weatherforecastr.di.modules.RepositoryModule;

/**
 * Created by killer on 9/1/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, RepositoryModule.class})
public interface RepositoryComponent {
    public void inject(MainActivity mainActivity);
    public void inject(SplashActivity splashActivity);
}
