package info.eivanov.weatherforecastr.presenters;

import android.app.DialogFragment;
import android.app.Fragment;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;
import info.eivanov.weatherforecastr.view.AddNewLocationContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by killer on 9/2/17.
 */

public class AddNewLocationPresenter implements AddNewLocationContract.Presenter {

    private final CurrentLocationsRepo currentLocationsRepo;
    private final GetWeatherInfoRepo getWeatherInfoRepo;
    private final Navigator navigator;
    private City currentSelection;
    private AddNewLocationContract.View view;

    public AddNewLocationPresenter(CurrentLocationsRepo currentLocationsRepo,
                                   GetWeatherInfoRepo getWeatherInfoRepo, Navigator navigator) {
        this.currentLocationsRepo = currentLocationsRepo;
        this.getWeatherInfoRepo = getWeatherInfoRepo;
        this.navigator = navigator;
    }

    public void setView(AddNewLocationContract.View view){
        this.view = view;
    }

    @Override
    public void cancel() {
        navigator.showCurrentLocationsScreen();
    }

    public City getCurrentSelection() {
        return currentSelection;
    }

    public void setCurrentSelection(City currentSelection) {
        this.currentSelection = currentSelection;
        if(currentSelection != null) {
            view.setCityDetails(currentSelection);
        }
    }

    @Override
    public void saveLocation() {
        if(currentSelection != null) {
            currentLocationsRepo.addCity(currentSelection);
            navigator.showCurrentLocationsScreen();
        }
    }

    @Override
    public void clearCurrentSelection() {
        currentSelection = null;
        view.clearCityDetails();
    }

    @Override
    public void onTextChanged(CharSequence s) {
        getWeatherInfoRepo.searchLocations(String.valueOf(s))
                .debounce(750, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<City>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull City cities) {
                        Timber.d("MY_TAGGGGG", cities.toString());
                        view.getAutoCompleteAdaper().getCurrentItems().clear();
                        view.getAutoCompleteAdaper().getCurrentItems().add(cities);
                        view.getAutoCompleteAdaper().notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e("MY_TAGGGGG", e.getMessage());
                        view.showErrorDialog("My Dialog");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("MY_TAGGGGG", "Completeddddd");
                    }
                });
    }
}
