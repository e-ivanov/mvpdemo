package info.eivanov.weatherforecastr.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.eivanov.weatherforecastr.R;


public class ViewCurrentWeatherFragment extends Fragment {


    public ViewCurrentWeatherFragment() {
        // Required empty public constructor
    }

    public static ViewCurrentWeatherFragment newInstance() {
        ViewCurrentWeatherFragment fragment = new ViewCurrentWeatherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_current_weather, container, false);
    }




    @Override
    public void onDetach() {
        super.onDetach();
    }

}