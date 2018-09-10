package com.langiappeworkshop.weathertest;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

/**
 *  This class {@link Downloader} is used to download the JSON weather data from the network and create and
 *  return a list of daily weather conditions.  The main entry point is {@link #fetchDays()}
 */
 public class Downloader {

    /**
     * Fetches JSON of daily weather and returns as a list of days.
     *
     * @return List of day {@link Day} or empty list, if error
     */
    @NonNull
    static List<Day> fetchDays() {
        List<Day> days = new ArrayList<>();

        parseDays(days);

        return days;
    }

    /**
     * Parses JSON of days list and a fills a list of day {@link Day}.
     */
    private static void parseDays(@NonNull List<Day> tenDaysWeatherList) {
        // TODO: remove this once we have our data loader class
        Day day = new Day(R.mipmap.ic_launcher, "", "Monday 9/10/2018",
                "Chance of a Thunderstorm", "Precip: 60%", "Lo: 69", "Hi: 84", "Humid: 76%", "Wind: 7 SW");
        tenDaysWeatherList.add(day);
        day = new Day(R.mipmap.ic_launcher, "", "Tuesday 9/11/2018",
                "Chance of a Thunderstorm", "Precip: 80%", "Lo: 71", "Hi: 87", "Humid: 72", "Wind: 4 WNW");
        tenDaysWeatherList.add(day);
        day = new Day(R.mipmap.ic_launcher, "", "Wednesday 9/12/2018",
                "Chance of a Thunderstorm", "Precip: 40%", "Lo: 71", "Hi: 88", "Humid: 72%", "Wind: 5 ENE");
        tenDaysWeatherList.add(day);
        day = new Day(R.mipmap.ic_launcher, "", "Thursday 9/13/2018",
                "Chance of a Thunderstorm", "Precip: 80%", "Lo: 71", "Hi: 87", "Humid: 72", "Wind: 4 WNW");
        tenDaysWeatherList.add(day);
        day = new Day(R.mipmap.ic_launcher, "", "Friday 9/14/2018",
                "Chance of a Thunderstorm", "Precip: 40%", "Lo: 71", "Hi: 88", "Humid: 72%", "Wind: 5 ENE");
        tenDaysWeatherList.add(day);
        day = new Day(R.mipmap.ic_launcher, "", "Saturday 9/15/2018",
                "Chance of a Thunderstorm", "Precip: 80%", "Lo: 71", "Hi: 87", "Humid: 72", "Wind: 4 WNW");
        tenDaysWeatherList.add(day);
        day = new Day(R.mipmap.ic_launcher, "", "Sunday 9/16/2018",
                "Chance of a Thunderstorm", "Precip: 40%", "Lo: 71", "Hi: 88", "Humid: 72%", "Wind: 5 ENE");
        tenDaysWeatherList.add(day);
        day = new Day(R.mipmap.ic_launcher, "", "Monday 9/17/2018",
                "Chance of a Thunderstorm", "Precip: 80%", "Lo: 71", "Hi: 87", "Humid: 72", "Wind: 4 WNW");
        tenDaysWeatherList.add(day);
        day = new Day(R.mipmap.ic_launcher, "", "Tuesday 9/18/2018",
                "Chance of a Thunderstorm", "Precip: 40%", "Lo: 71", "Hi: 88", "Humid: 72%", "Wind: 5 ENE");
        tenDaysWeatherList.add(day);
        day = new Day(R.mipmap.ic_launcher, "", "Wednesday 9/19/2018",
                "Chance of a Thunderstorm", "Precip: 80%", "Lo: 71", "Hi: 87", "Humid: 72", "Wind: 4 WNW");
        tenDaysWeatherList.add(day);
    }
}
