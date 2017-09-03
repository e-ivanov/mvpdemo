package info.eivanov.weatherforecastr.presenters;

import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import info.eivanov.weatherforecastr.repository.GetWeatherInfoRepo;
import info.eivanov.weatherforecastr.view.ShowCurrentWeatherContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by killer on 9/2/17.
 */

public class ShowCurrentWeatherPresenter implements ShowCurrentWeatherContract.Presenter {


    private final GetWeatherInfoRepo weatherInfoRepo;
    private final Navigator navigator;
    private ShowCurrentWeatherContract.View view;

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
        weatherInfoRepo.getWeatherForecastForCity(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherForecastResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull WeatherForecastResponse weatherForecastResponse) {
                        view.showForecast(weatherForecastResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
