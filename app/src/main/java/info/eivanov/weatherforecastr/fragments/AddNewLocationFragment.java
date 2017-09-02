package info.eivanov.weatherforecastr.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.view.AddNewLocationContract;

public class AddNewLocationFragment extends Fragment implements AddNewLocationContract.View{

    @Inject
    AddNewLocationContract.Presenter presenter;

    public AddNewLocationFragment() {
        // Required empty public constructor
    }

    public static AddNewLocationFragment newInstance() {
        AddNewLocationFragment fragment = new AddNewLocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_location, container, false);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
