package info.eivanov.weatherforecastr.view;

import info.eivanov.weatherforecastr.model.City;

/**
 * Created by killer on 9/2/17.
 */

public class AddNewLocationContract {

    public interface View{
        public void setCityDetails(City city);
        public void clearCityDetails();
        public AutoCompleteAdaper getAutoCompleteAdaper();
        public void showErrorDialog(String errorMsg);
    }

    public interface Presenter{
        public City getCurrentSelection();
        public void setCurrentSelection(City currentSelection);
        public void saveLocation();
        public void clearCurrentSelection();
        public void onTextChanged(CharSequence s);
        public void setView(AddNewLocationContract.View view);
        public void cancel();
    }
}
