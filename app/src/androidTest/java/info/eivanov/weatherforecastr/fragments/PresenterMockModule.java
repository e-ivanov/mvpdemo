package info.eivanov.weatherforecastr.fragments;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.di.modules.PresenterModule;
import info.eivanov.weatherforecastr.di.scope.FragmentScope;
import info.eivanov.weatherforecastr.presenters.AddNewLocationPresenter;
import info.eivanov.weatherforecastr.presenters.LocationListPresenter;
import info.eivanov.weatherforecastr.presenters.ShowCurrentWeatherPresenter;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;
import info.eivanov.weatherforecastr.view.AddNewLocationContract;
import info.eivanov.weatherforecastr.view.LocationsListContract;
import info.eivanov.weatherforecastr.view.ShowCurrentWeatherContract;

/**
 * Created by killer on 9/9/17.
 */

@Module
public class PresenterMockModule{


        @Provides
        @FragmentScope
        public AddNewLocationContract.Presenter providesAddNewLocationPresenter() {
            return Mockito.mock(AddNewLocationContract.Presenter.class);
        }

        @Provides
        @FragmentScope
        public LocationsListContract.Presenter providesLocationsListPresenter() {
            return Mockito.mock(LocationsListContract.Presenter.class);
        }

        @Provides
        @FragmentScope
        public ShowCurrentWeatherContract.Presenter providesShowCurrentWeatherPresenter() {
            return Mockito.mock(ShowCurrentWeatherContract.Presenter.class);
        }
}
