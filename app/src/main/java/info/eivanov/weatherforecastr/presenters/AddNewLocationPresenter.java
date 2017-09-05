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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by killer on 9/2/17.
 */

public class AddNewLocationPresenter extends BasePresenter implements AddNewLocationContract.Presenter {

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

    @Override
    public void unsubscribe() {
        disposable.clear();
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
                .debounce(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<City>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        view.showLoadingIndicator();
//                        view.toggleAutocompleteInput(false);
                        disposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull City cities) {
                        Timber.d("MY_TAGGGGG", cities.toString());
                        view.hideLoadingIndicator();
                        view.getAutoCompleteAdaper().getCurrentItems().clear();
                        view.getAutoCompleteAdaper().getCurrentItems().add(cities);
                        view.getAutoCompleteAdaper().notifyDataSetChanged();
                        view.getAutoCompleteAdaper().getFilter().filter(view.getAutocompleteView().getText(),
                                                                        view.getAutocompleteView());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e("MY_TAGGGGG", e.getMessage());
                        view.toggleAutocompleteInput(true);
                        view.hideLoadingIndicator();
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("MY_TAGGGGG", "Completeddddd");
                        view.toggleAutocompleteInput(true);
//                        view.hideLoadingIndicator();
                    }
                });
    }
}
