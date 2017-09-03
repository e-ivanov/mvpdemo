package info.eivanov.weatherforecastr.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import info.eivanov.weatherforecastr.model.City;

/**
 * Created by killer on 9/2/17.
 */

public class AutoCompleteAdaper extends ArrayAdapter<City>{
    private List<City> currentItems;
    private List<City> currentSuggestions;
    public AutoCompleteAdaper(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.currentItems = new ArrayList<>();
        this.currentSuggestions = new ArrayList<>();
    }

    public List<City> getCurrentItems(){
        return currentItems;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    private final Filter nameFilter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((City)resultValue).getName()+", "+((City)resultValue).getCountry();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null){
                currentSuggestions.clear();
                for(City city : currentItems){
                    if(city.getName().toLowerCase().contains(constraint.toString())){
                        currentSuggestions.add(city);
                    }
                }
                FilterResults results = new FilterResults();
                results.values = currentSuggestions;
                results.count = currentSuggestions.size();
                return results;
            }else{
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<City> filterList = (ArrayList<City>)results.values;
            if(results != null && results.count > 0){
                clear();
                for(City city : filterList){
                    add(city);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
