package com.langiappeworkshop.weathertest;

import android.support.annotation.NonNull;

/**
 *  This class {@link Day} is used to hold daily weather data.
 */
public class Day {

    int imageResourceId;
    String imageURL;
    String date;
    String summary;
    String precip;
    String lo;
    String hi;
    String humid;
    String wind;

    Day(int imageResourceId,
        @NonNull String imageURL,
        @NonNull String date,
        @NonNull String summary,
        @NonNull String precip,
        @NonNull String lo,
        @NonNull String hi,
        @NonNull String humid,
        @NonNull String wind) {
        this.imageResourceId = imageResourceId;
        this.imageURL = imageURL;
        this.date = date;
        this.summary = summary;
        this.precip = precip;
        this.lo = lo;
        this.hi = hi;
        this.humid = humid;
        this.wind = wind;
    }
}
