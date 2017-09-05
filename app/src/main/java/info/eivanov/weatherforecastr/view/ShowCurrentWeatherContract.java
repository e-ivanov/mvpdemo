package info.eivanov.weatherforecastr.view;

import info.eivanov.weatherforecastr.model.WeatherForecastResponse;

/**
 * Created by killer on 9/2/17.
 */

public class ShowCurrentWeatherContract {

    public interface View extends BaseView {
        void showForecast(WeatherForecastResponse response);
    }

    public interface Presenter{
        void setView(ShowCurrentWeatherContract.View view);
        void loadForecast(long cityId);
        void unsubscribe();
    }
}
