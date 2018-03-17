package app.hackit.hackamigo.service;


import app.hackit.hackamigo.data.Channel;

/**
 * Created by karan on 3/13/2018.
 */

public interface WeatherServiceCallback {

    void serviceSuccess(Channel channel);
    void serviceFailure(Exception e);
}
