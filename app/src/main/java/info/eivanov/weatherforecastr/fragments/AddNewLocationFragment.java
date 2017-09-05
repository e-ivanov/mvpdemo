package info.eivanov.weatherforecastr.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.WeatherForecastrApp;
import info.eivanov.weatherforecastr.activities.Navigator;
import info.eivanov.weatherforecastr.di.components.DaggerPresenterComponent;
import info.eivanov.weatherforecastr.di.modules.PresenterModule;
import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.view.AddNewLocationContract;
import info.eivanov.weatherforecastr.view.AutoCompleteAdaper;

public class AddNewLocationFragment extends BaseFragment implements AddNewLocationContract.View, TextWatcher{

    public static final String TAG = "add_new_location_fragment";
    @Inject
    AddNewLocationContract.Presenter presenter;

    private AutoCompleteTextView autoComplete;
    private AutoCompleteAdaper autoCompleteAdapter;
    private TextView cityDetails;
    private Button saveLocationBtn;
    private Button cleanLocationBtn;
    private Button btnCancel;
    private ProgressBar progressBar;


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
        DaggerPresenterComponent.builder()
                .applicationComponent(WeatherForecastrApp.getApp(getActivity()).getApplicationComponent())
                .presenterModule(new PresenterModule((Navigator)getActivity()))
                .build().inject(this);
        autoCompleteAdapter = new AutoCompleteAdaper(getActivity(),
                android.R.layout.simple_dropdown_item_1line);
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_new_location, container, false);
        cityDetails = (TextView)root.findViewById(R.id.cityAndCountryName);
        progressBar = (ProgressBar)root.findViewById(R.id.progresBar);
        progressBar.setVisibility(View.INVISIBLE);
        autoComplete = (AutoCompleteTextView)root.findViewById(R.id.searchCityTxt);
        autoComplete.setAdapter(autoCompleteAdapter);
        autoComplete.addTextChangedListener(this);
        autoComplete.setThreshold(3);
        autoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City city = autoCompleteAdapter.getCurrentItems().get(position);
                Toast.makeText(getActivity(), autoCompleteAdapter.getCurrentItems().get(position).toString(), Toast.LENGTH_LONG).show();
                autoComplete.setText("");
                setCityDetails(city);
                presenter.setCurrentSelection(city);

            }
        });

        saveLocationBtn = (Button)root.findViewById(R.id.btnSaveNewLocation);
        cleanLocationBtn = (Button)root.findViewById(R.id.btnClearCurrentSelection);
        btnCancel = (Button)root.findViewById(R.id.btnCancel);
        saveLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveLocation();
            }
        });
        cleanLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearCurrentSelection();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cancel();
            }
        });
        if(savedInstanceState != null){
            presenter.setCurrentSelection((City)savedInstanceState.getParcelable("currentSelection"));
        }
        return root;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("currentSelection", presenter.getCurrentSelection());
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s.length() < autoComplete.getThreshold())return;
        presenter.onTextChanged(s);
    }

    public void setCityDetails(City city){
        cityDetails.setText(city.getName() +", "+city.getSys().getCountry());
    }

    public void clearCityDetails(){
        autoComplete.setText("");
        cityDetails.setText("");
    }

    @Override
    public AutoCompleteAdaper getAutoCompleteAdaper() {
        return autoCompleteAdapter;
    }

    @Override
    public AutoCompleteTextView getAutocompleteView() {
        return autoComplete;
    }

    @Override
    public void showErrorDialog(String errorMsg) {
        new DialogFragment().show(getActivity().getFragmentManager(), "My Dialog");
    }

    @Override
    public void toggleAutocompleteInput(boolean enabled) {
        autoComplete.setEnabled(enabled);
    }

    @Override
    public void showLoadingIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
