package info.eivanov.weatherforecastr.di.components;

import dagger.Component;
import info.eivanov.weatherforecastr.di.modules.PresenterModule;
import info.eivanov.weatherforecastr.di.scope.FragmentScope;
import info.eivanov.weatherforecastr.fragments.AddNewLocationFragment;
import info.eivanov.weatherforecastr.fragments.LocationsListFragment;
import info.eivanov.weatherforecastr.fragments.ViewCurrentWeatherFragment;

/**
 * Created by killer on 9/3/17.
 */
@FragmentScope
@Component(dependencies = ApplicationComponent.class,modules = PresenterModule.class)
public interface PresenterComponent {

    void inject(AddNewLocationFragment fragment);
    void inject(LocationsListFragment fragment);
    void inject(ViewCurrentWeatherFragment fragment);
}
