package info.eivanov.weatherforecastr.di.components;

import javax.inject.Singleton;

import dagger.Component;
import info.eivanov.weatherforecastr.activities.MainActivity;
import info.eivanov.weatherforecastr.di.modules.ApplicationModule;
import info.eivanov.weatherforecastr.di.modules.NetworkModule;


/**
 * Created by killer on 8/30/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface NetComponent {

    public void inject(MainActivity mainActivity);
}
