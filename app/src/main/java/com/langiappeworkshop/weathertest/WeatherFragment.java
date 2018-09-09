package com.langiappeworkshop.weathertest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

public class WeatherFragment extends Fragment {

    private RecyclerView dayListRecyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        dayListRecyclerView = rootView.findViewById(R.id.day_list_recycler);
        dayListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    /**
     *  DayAdapter connects our DayViewHolder and our RecyclerView.
     */
    private class DayAdapter extends RecyclerView.Adapter<DayViewHolder>{

        @NonNull
        @Override
        public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    /**
     *  Our ViewHolder for view items associated with our 10 days of weather.
     */
    private class DayViewHolder extends RecyclerView.ViewHolder {
        DayViewHolder(View dayView) {
            super(dayView);
        }
    }
}
