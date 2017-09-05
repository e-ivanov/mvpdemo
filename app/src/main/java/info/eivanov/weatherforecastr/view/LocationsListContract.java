package info.eivanov.weatherforecastr.view;

import java.util.List;

import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;

/**
 * Created by killer on 9/2/17.
 */

public class LocationsListContract {

    public interface View extends BaseView{

    }

    public interface Presenter{
        public CurrentLocationsRepo getLocationsRepo();
        public void deleteCityById(long id);
        public List<City> getLocations();
        public void addNewLocation();
        public void showCurrentWeatherForLocation(long cityId);
        public void unsubscribe();
    }
}
