package info.eivanov.weatherforecastr.di.modules;

import dagger.Module;
import dagger.Provides;
import info.eivanov.weatherforecastr.di.scope.FragmentScope;
import info.eivanov.weatherforecastr.presenters.AddNewLocationPresenter;
import info.eivanov.weatherforecastr.presenters.LocationListPresenter;
import info.eivanov.weatherforecastr.presenters.ShowCurrentWeatherPresenter;
import info.eivanov.weatherforecastr.view.LocationsListContract;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;
import info.eivanov.weatherforecastr.view.AddNewLocationContract;
import info.eivanov.weatherforecastr.view.ShowCurrentWeatherContract;

/**
 * Created by killer on 9/3/17.
 */


@Module
public class PresenterModule {

    @FragmentScope
    @Provides
    AddNewLocationContract.Presenter providesAddNewLocationPresenter(CurrentLocationsRepo
                                                                             currentLocationsRepo,
                                                                     GetWeatherInfoRepo weatherInfoRepo){
        return new AddNewLocationPresenter(currentLocationsRepo, weatherInfoRepo);
    }

    @FragmentScope
    @Provides
    LocationsListContract.Presenter providesLocationsListPresenter(CurrentLocationsRepo currentLocationsRepo){
        return new LocationListPresenter(currentLocationsRepo);
    }

    @FragmentScope
    @Provides
    ShowCurrentWeatherContract.Presenter providesShowCurrentWeatherPresenter(GetWeatherInfoRepo weatherInfoRepo){
        return new ShowCurrentWeatherPresenter(weatherInfoRepo);
    }
}
