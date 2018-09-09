package com.langiappeworkshop.weathertest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherFragment extends Fragment {

    private RecyclerView dayListRecyclerView;
    private DaysAdapter daysAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        dayListRecyclerView = rootView.findViewById(R.id.day_list_recycler);
        dayListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return rootView;
    }

    /**
     *  DayAdapter connects our DayViewHolder and our RecyclerView.
     */
    private class DaysAdapter extends RecyclerView.Adapter<DayViewHolder>{

        private List<String> tenDaysWeather;

        public DaysAdapter(@NonNull List<String> tenDaysWeather ) {
            this.tenDaysWeather = tenDaysWeather;
        }

        @NonNull
        @Override
        public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new DayViewHolder(layoutInflater.inflate(R.layout.day_list_content, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
            String todaysWeather = tenDaysWeather.get(position);
            holder.bind(todaysWeather);
        }

        @Override
        public int getItemCount() {
            return tenDaysWeather.size();
        }
    }

    /**
     *  Our ViewHolder for view items associated with our 10 days of weather.
     */
    private class DayViewHolder extends RecyclerView.ViewHolder {
        public TextView dayView;
        public String todaysWeather;
        public DayViewHolder(View dayView) {
                super(dayView);
                this.dayView = dayView.findViewById(R.id.day_list_tv);
        }

        public void bind(String todaysWeather) {
            this.todaysWeather = todaysWeather;
            dayView.setText(this.todaysWeather);
        }
    }

    private void updateUI() {
        List<String> tenDaysWeatherList = new ArrayList<>(Arrays.asList("Cloudy", "Cloudy","Rainy", "Clear","Clear","Cloudy", "Cloudy","Rainy", "Clear","Clear"));
        daysAdapter = new DaysAdapter(tenDaysWeatherList);
        dayListRecyclerView.setAdapter(daysAdapter);

    }
}
