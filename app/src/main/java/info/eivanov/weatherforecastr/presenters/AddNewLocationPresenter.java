package info.eivanov.weatherforecastr.presenters;

import android.app.Fragment;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.view.AddNewLocationContract;

/**
 * Created by killer on 9/2/17.
 */

public class AddNewLocationPresenter implements AddNewLocationContract.Presenter {

    private final CurrentLocationsRepo currentLocationsRepo;

    public AddNewLocationPresenter(CurrentLocationsRepo currentLocationsRepo) {
        this.currentLocationsRepo = currentLocationsRepo;
    }
}
