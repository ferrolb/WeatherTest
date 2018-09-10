package com.langiappeworkshop.weathertest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WeatherFragment extends Fragment {

    private RecyclerView dayListRecyclerView;
    private DaysAdapter daysAdapter;
    private List<Day> tenDaysWeather;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        dayListRecyclerView = rootView.findViewById(R.id.day_list_recycler);
        dayListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        new DownloadDaysTask().execute();

        return rootView;
    }

    /**
     *  DayAdapter connects our DayViewHolder and our RecyclerView.
     */
    private class DaysAdapter extends RecyclerView.Adapter<DayViewHolder>{

        private List<Day> tenDaysWeather;

        public DaysAdapter(@NonNull List<Day> tenDaysWeather ) {
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
            Day todaysWeather = tenDaysWeather.get(position);
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

        private ImageView ivIcon;
        private TextView tvDate;
        private TextView tvSummary;
        private TextView tvPrecip;
        private TextView tvLo;
        private TextView tvHi;
        private TextView tvHumid;
        private TextView tvWind;

        public Day todaysWeather;

        public DayViewHolder(View dayView) {
            super(dayView);
            ivIcon = dayView.findViewById(R.id.ivIcon);
            tvDate = dayView.findViewById(R.id.tvDate);
            tvSummary = dayView.findViewById(R.id.tvSummary);
            tvPrecip = dayView.findViewById(R.id.tvPrecip);
            tvLo = dayView.findViewById(R.id.tvLo);
            tvHi = dayView.findViewById(R.id.tvHi);
            tvHumid = dayView.findViewById(R.id.tvHumid);
            tvWind = dayView.findViewById(R.id.tvWind);
        }

        public void bind(Day todaysWeather) {
            this.todaysWeather = todaysWeather;

            tvSummary.setText(todaysWeather.summary);
            tvDate.setText(todaysWeather.date);
            tvPrecip.setText(todaysWeather.precip);
            tvLo.setText(todaysWeather.lo);
            tvHi.setText(todaysWeather.hi);
            tvHumid.setText(todaysWeather.humid);
            tvWind.setText(todaysWeather.wind);
        }
    }

    private void updateUI() {

        if (tenDaysWeather!=null && !tenDaysWeather.isEmpty()) {
            // check if fragment has been added to Activity before we setup the RecyclerView
            if (isAdded()) {
                daysAdapter = new DaysAdapter(tenDaysWeather);
                dayListRecyclerView.setAdapter(daysAdapter);
            }
        }
    }

    /**
     *  This class {@link DownloadDaysTask} takes our networking work off the main thread.
     *  It returns a list of daily weather conditions {@link Day} and sets up the RecyclerView on completion.
     */
    private class DownloadDaysTask extends AsyncTask<Void, Void, List<Day>> {

        @Override
        protected List<Day> doInBackground(Void... voids) {
            return Downloader.fetchDays();
        }

        @Override
        protected void onPostExecute(List<Day> days) {
            tenDaysWeather = days;
            updateUI();
        }
    }
}
