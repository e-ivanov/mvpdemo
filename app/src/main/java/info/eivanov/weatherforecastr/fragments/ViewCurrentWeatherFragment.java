package info.eivanov.weatherforecastr.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.di.components.DaggerPresenterComponent;
import info.eivanov.weatherforecastr.di.modules.PresenterModule;
import info.eivanov.weatherforecastr.model.Weather;
import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import info.eivanov.weatherforecastr.view.ShowCurrentWeatherContract;


public class ViewCurrentWeatherFragment extends Fragment implements ShowCurrentWeatherContract.View {

    public static final String TAG = "view_current_weather_fragment";

    @Inject
    ShowCurrentWeatherContract.Presenter presenter;

    private TextView currentWeatherIc;
    private TextView currentLocation;
    private TextView currentConditionsDesc;
    private TextView currentTemperature;

    private long cityId;

    public ViewCurrentWeatherFragment() {
        // Required empty public constructor
    }

    public static ViewCurrentWeatherFragment newInstance(long cityId) {
        ViewCurrentWeatherFragment fragment = new ViewCurrentWeatherFragment();
        Bundle args = new Bundle();
        args.putLong("city_id", cityId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerPresenterComponent.builder()
                .applicationComponent(WeatherForecastrApp.getApp(getActivity()).getApplicationComponent())
                .presenterModule(new PresenterModule((Navigator)getActivity()))
                .build().inject(this);
        presenter.setView(this);
        if (getArguments() != null) {
            cityId = getArguments().getLong("city_id", -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_view_current_weather, container, false);
        currentWeatherIc = (TextView)root.findViewById(R.id.currentWeatherIc);
        currentConditionsDesc = (TextView)root.findViewById(R.id.currentConditionsDesc);
        currentLocation = (TextView)root.findViewById(R.id.currentLocation);
        currentTemperature = (TextView)root.findViewById(R.id.currentTemperature);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(cityId != -1) {
            presenter.loadForecast(cityId);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showForecast(WeatherForecastResponse response) {
        currentLocation.setText(response.getName()+", "+response.getSys().getCountry());
        Weather weather = response.getWeather().get(0);
        currentConditionsDesc.setText(weather.getMain()+", "+weather.getDescription());
        currentTemperature.setText(String.valueOf(response.getMain().getTemp()));
        currentWeatherIc.setText(weather.getIcon());

    }
}
