package app.hackit.hackamigo.service;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import app.hackit.hackamigo.data.Channel;

/**
 * Created by karan on 3/13/2018.
 */

public class YahooWeatherService {

    private WeatherServiceCallback callback;
    private String location;
    private Exception error;

    public String getLocation() {
        return location;
    }

    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    @SuppressLint("StaticFieldLeak")
    public void refreshWeather(String l) {
        this.location=l;

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... strings) {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")", strings[0]);
                String endPoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endPoint);
                    URLConnection urlConnection = url.openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();

                } catch (Exception e) {
                    error = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {

                if (s == null && error == null) {
                    callback.serviceFailure(error);
                    return;
                }
                try {
                    JSONObject data = new JSONObject(s);
                    JSONObject queryResult = data.optJSONObject("query");

                    String location=queryResult.optString("created");



                    int count = queryResult.optInt("count");
                    String date=queryResult.optString("created");

                    if (count == 0||date ==null) {
                        callback.serviceFailure(new LocationWeatherException("No weather info found for" + location));
                        return;
                    }

                    Channel channel=new Channel();
                    channel.populate(queryResult.optJSONObject("results")
                            .optJSONObject("channel"));

                    callback.serviceSuccess(channel);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(location);

    }

    public class LocationWeatherException extends Exception {

        public LocationWeatherException(String message) {
            super(message);
        }
    }
}
