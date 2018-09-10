package com.langiappeworkshop.weathertest;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *  This class {@link Downloader} is used to download the JSON weather data from the network and create and
 *  return a list of daily weather conditions.  The main entry point is {@link #fetchDays()}
 */
 public class Downloader {

    /**
     * Takes a URL from where to fetch the JSON and returns that JSON as a byte array.
     *
     * @param urlString a string containing the JSON URL
     * @return byte array containing the resulting JSON or null, if error
     */
    @Nullable
    static private byte[] getBytes(@NonNull String urlString) {
        HttpURLConnection connection = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(urlString);

            connection = (HttpURLConnection) url.openConnection();
            baos = new ByteArrayOutputStream();
            InputStream is = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.i("getBytes", "Connection failed");
                throw new Exception("Reponse not okay");
            }

            int numBytes;
            byte[] buffer = new byte[1024];

            while ((numBytes = is.read(buffer)) > 0) {
                baos.write(buffer, 0, numBytes);
            }

            baos.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }

        if (baos != null) {
            return baos.toByteArray();
        } else {
            return null;
        }
    }


    /**
     * Takes a URL from where to fetch the JSON and returns that JSON as String.
     *
     * @return String containing the resulting JSON or "", if error
     */
    @NonNull
    static private String getString() {
        String urlString = "http://api.wunderground.com/api/d471ef2674c79e20/forecast10day/q/US/GA/Atlanta.json";
        byte[] bytes = getBytes(urlString);
        if (bytes == null) {
            return "";
        } else {
            return new String(bytes);
        }
    }

    /**
     * Fetches JSON of daily weather and returns as a list of days.
     *
     * @return List of day {@link Day} or empty list, if error
     */
    @NonNull
    static List<Day> fetchDays() {
        List<Day> days = new ArrayList<>();
        String jsonString = getString();
        try {
            parseDays(days, jsonString);
        } catch (JSONException e) {
            Log.e("Downloader", "Could not parse JSON: " + e);
        }
        return days;
    }

    /**
     * Parses JSON of days list and a fills a list of day {@link Day}.
     *
     * @param days       empty list of daily weather {@link Day} that will be filled on return.
     * @param jsonString String contianing the JSON to be parsed.
     */
    static private void parseDays(@NonNull List<Day> days, @NonNull String jsonString) throws JSONException {
        if (TextUtils.isEmpty(jsonString)) {
            return;
        }

        JSONArray jsonArray = new JSONObject(jsonString).getJSONObject("forecast").getJSONObject("simpleforecast").getJSONArray("forecastday");

        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            // Fill Date ========
            String strDate = "";
            if (!jsonObject.isNull("date")) {
                JSONObject dateObject = jsonObject.getJSONObject("date");

                String month = dateObject.isNull("month") ? "" : "" + dateObject.getInt("month");
                String day = dateObject.isNull("day") ? "" : "" + dateObject.getInt("day");
                String year = dateObject.isNull("year") ? "" : "" + dateObject.getInt("year");
                String weekday = dateObject.isNull("weekday") ? "" : dateObject.getString("weekday");
                if (!TextUtils.isEmpty(month) && !TextUtils.isEmpty(day) && !TextUtils.isEmpty(year)) {
                    strDate = weekday + "  " + month + "/" + day + "/" + year;
                }
            }

            // Fill Summary ========
            String summary = jsonObject.isNull("conditions") ? "" : jsonObject.getString("conditions");

            // Fill Precip ========
            String precip = jsonObject.isNull("pop") ? "Precip:" : "Precip: " + jsonObject.getInt("pop") + "%";
            String lo = "Lo:";

            // Fill Lo ========
            if (!jsonObject.isNull("low")) {
                JSONObject lowJSON = jsonObject.getJSONObject("low");
                lo = lowJSON.isNull("fahrenheit") ? "Lo:" : "Lo: " + lowJSON.getInt("fahrenheit") + "\u00b0";
            }
            String hi = "Hi:";

            // Fill Hi ========
            if (!jsonObject.isNull("high")) {
                JSONObject highJSON = jsonObject.getJSONObject("high");
                hi = highJSON.isNull("fahrenheit") ? "Hi:" : "Hi: " + highJSON.getInt("fahrenheit") + "\u00b0";
            }

            // Fill Humid ========
            String humid = jsonObject.isNull("avehumidity") ? "Humid:" : "Humid: " + jsonObject.getInt("avehumidity") + "%";

            // Fill Wind ========
            String wind = "Wind:";
            if (!jsonObject.isNull("avewind")) {
                if (!jsonObject.getJSONObject("avewind").isNull("mph") && !jsonObject.getJSONObject("avewind").isNull("dir")) {
                    wind = "Wind: " + jsonObject.getJSONObject("avewind").getInt("mph") + " " + jsonObject.getJSONObject("avewind").getString("dir");
                }
            }

            // Fill imageURL ========
            String imageURL = jsonObject.isNull("icon_url") ? "" : jsonObject.getString("icon_url");

            // create Day object
            Day day = new Day(R.mipmap.ic_launcher, imageURL, strDate, summary, precip, lo, hi, humid, wind);

            // add Day to list
            days.add(day);
        }
    }
}
