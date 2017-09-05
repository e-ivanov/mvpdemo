package info.eivanov.weatherforecastr.presenters;

import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;
import info.eivanov.weatherforecastr.view.ShowCurrentWeatherContract;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by killer on 9/2/17.
 */

public class ShowCurrentWeatherPresenter extends BasePresenter implements ShowCurrentWeatherContract.Presenter {


    private final GetWeatherInfoRepo weatherInfoRepo;
    private final Navigator navigator;
    private ShowCurrentWeatherContract.View view;
    private Observable<WeatherForecastResponse> observable;

    public ShowCurrentWeatherPresenter(GetWeatherInfoRepo weatherInfoRepo, Navigator navigator) {
        this.weatherInfoRepo = weatherInfoRepo;
        this.navigator = navigator;
    }

    @Override
    public void setView(ShowCurrentWeatherContract.View view) {
        this.view = view;
    }

    @Override
    public void loadForecast(long cityId) {
        getObservable(cityId).subscribe(new Observer<WeatherForecastResponse>() {
          @Override
          public void onSubscribe(@NonNull Disposable d) {
              view.showLoadingIndicator();
              disposable.add(d);
          }

          @Override
          public void onNext(@NonNull WeatherForecastResponse weatherForecastResponse) {
              Timber.d("Received weather!");
              view.showForecast(weatherForecastResponse);
//              view.hideLoadingIndicator();
          }

          @Override
          public void onError(@NonNull Throwable e) {
              Timber.d( e);
              view.hideLoadingIndicator();
          }

          @Override
          public void onComplete() {
              Timber.d("Observable is finished!");
              view.hideLoadingIndicator();
          }
      });

    }

    private Observable<WeatherForecastResponse>getObservable(long cityId){
        return weatherInfoRepo.getWeatherForecastForCity(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public void unsubscribe() {
        disposable.clear();
    }

}
