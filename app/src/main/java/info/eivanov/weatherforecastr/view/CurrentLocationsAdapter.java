package info.eivanov.weatherforecastr.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import info.eivanov.weatherforecastr.R;
import info.eivanov.weatherforecastr.model.City;
import info.eivanov.weatherforecastr.repository.CurrentLocationsRepo;

/**
 * Created by killer on 9/2/17.
 */

public class CurrentLocationsAdapter extends RecyclerView.Adapter<CurrentLocationsAdapter.CityViewHolder>{

    private final List<City> cities;
    private final LocationsListContract.Presenter presenter;

    public CurrentLocationsAdapter(LocationsListContract.Presenter presenter) {
        this.presenter = presenter;
        this.cities = presenter.getLocations();
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_location_list_item
                                                                  , parent, false);
        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bindModel(cities.get(position), position);
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder{

        private final TextView cityAndCountryName;
        private final Button delBtn;
        private int position;

        public CityViewHolder(View itemView) {
            super(itemView);
            cityAndCountryName = (TextView) itemView.findViewById(R.id.cityAndCountryName);
            delBtn = (Button)itemView.findViewById(R.id.deleteLocation);
            delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.deleteCityById(cities.get(position).getId());
                    cities.remove(position);
                    notifyItemRemoved(position);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.showCurrentWeatherForLocation(cities.get(position).getId());
                }
            });
        }

        public void bindModel(City city, int position){
            this.position = position;
            StringBuilder builder = new StringBuilder();
            builder.append(city.getName());
            builder.append(", ");
            builder.append(city.getSys().getCountry());
            cityAndCountryName.setText(builder.toString());
        }
    }
}
