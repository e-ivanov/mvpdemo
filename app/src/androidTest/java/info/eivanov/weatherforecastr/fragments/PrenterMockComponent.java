package info.eivanov.weatherforecastr.fragments;

import dagger.Component;
import info.eivanov.weatherforecastr.di.components.PresenterComponent;
import info.eivanov.weatherforecastr.di.scope.FragmentScope;

/**
 * Created by killer on 9/9/17.
 */

@FragmentScope
@Component(modules = {PresenterMockModule.class}, dependencies = ApplicationMockComponent.class)
public interface PrenterMockComponent extends PresenterComponent{

    public void inject(AddNewLocationFragmentTest presenter);
    public void inject(LocationsListFragmentTest presenter);
    public void inject(ViewCurrentWeatherFragmentTest preserner);
}
