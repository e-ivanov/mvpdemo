package info.eivanov.weatherforecastr.presenters;

import java.util.List;

import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.view.LocationsListContract;

/**
 * Created by killer on 9/3/17.
 */

public class LocationListPresenter implements LocationsListContract.Presenter {

    private final CurrentLocationsRepo currentLocationsRepo;

    public LocationListPresenter(CurrentLocationsRepo currentLocationsRepo) {
        this.currentLocationsRepo = currentLocationsRepo;
    }

    @Override
    public CurrentLocationsRepo getLocationsRepo() {
        return currentLocationsRepo;
    }

    @Override
    public void deleteCityById(long id) {
        currentLocationsRepo.deleteCityById(id);
    }

    @Override
    public List<City> getLocations() {
        return currentLocationsRepo.getLocations();
    }
}
