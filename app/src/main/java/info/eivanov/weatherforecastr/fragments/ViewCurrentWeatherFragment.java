package info.eivanov.weatherforecastr.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.di.components.DaggerPresenterComponent;
import info.eivanov.weatherforecastr.model.WeatherForecastResponse;
import info.eivanov.weatherforecastr.view.ShowCurrentWeatherContract;


public class ViewCurrentWeatherFragment extends Fragment implements ShowCurrentWeatherContract.View {

    @Inject
    ShowCurrentWeatherContract.Presenter presenter;
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
                .build().inject(this);
        presenter.setView(this);
        if (getArguments() != null) {
            cityId = getArguments().getLong("city_id", -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_current_weather, container, false);
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

    }
}
