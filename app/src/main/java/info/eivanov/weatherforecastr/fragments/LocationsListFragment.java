package info.eivanov.weatherforecastr.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.view.CurrentLocationsAdapter;
import info.eivanov.weatherforecastr.view.LocationsListContract;

public class LocationsListFragment extends BaseFragment implements LocationsListContract.View {


    public static final String TAG = "locations_list_fragment";

    @Inject
    LocationsListContract.Presenter presenter;

    private RecyclerView recyclerView;

    private Button btnAddNewLocation;

    public LocationsListFragment() {
    }

    public static Fragment newInstance() {
        LocationsListFragment fragment = new LocationsListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeatherForecastrApp app = WeatherForecastrApp.getApp(getActivity());
        app.getPresenterComponent((Navigator)getActivity()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_locations_list2, container, false);
        btnAddNewLocation = (Button)root.findViewById(R.id.btnAddNewLocation);
        btnAddNewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.addNewLocation();
            }
        });
        setUpRecyclerView(root);
        return root;
    }

    private void setUpRecyclerView(View root) {
        recyclerView = (RecyclerView)root.findViewById(R.id.currentCitiesList);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        CurrentLocationsAdapter adapter = new CurrentLocationsAdapter(presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void showLoadingIndicator() {
        super.showLoadingIndicator();
    }

    @Override
    public void hideLoadingIndicator() {
        super.hideLoadingIndicator();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

}
