package firstapp.heyci.com.firstapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CityAdapter  extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private ArrayList<City> cityList;

    public CityAdapter(ArrayList<City> cityList) {
        this.cityList = cityList;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCode;
        TextView tvCity;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvCity = itemView.findViewById(R.id.tvCity);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup vg, int viewType) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.row_city, vg, false);
        return new CityAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.tvCode.setText(city.getCp());
        holder.tvCity.setText(city.getVille());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }
}