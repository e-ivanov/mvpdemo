package info.eivanov.weatherforecastr.view;

import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.model.WeatherForecastResponse;

/**
 * Created by killer on 9/2/17.
 */

public class ShowCurrentWeatherContract {

    public interface View{
        public void showForecast(WeatherForecastResponse response);
    }

    public interface Presenter{
        public void showForecast(City city);
    }
}
