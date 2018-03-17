package app.hackit.hackamigo.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.hackit.hackamigo.R;
import app.hackit.hackamigo.data.Channel;
import app.hackit.hackamigo.data.Item;
import app.hackit.hackamigo.service.WeatherServiceCallback;
import app.hackit.hackamigo.service.YahooWeatherService;


/**
 * Created by karan on 3/13/2018.
 */

public class WeatherFragment extends Fragment implements WeatherServiceCallback {

    private ImageView weatherIconImage;
    TextView temperatureTv,conditionTv,locationTv;
    private YahooWeatherService service;
    ProgressDialog progressDialog;

    public static WeatherFragment newInstance() {

        Bundle args = new Bundle();

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service=new YahooWeatherService(this);
        service.refreshWeather("New Delhi, Delhi");

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading");
        progressDialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_weather,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        temperatureTv=view.findViewById(R.id.temperatureTextView);
        conditionTv=view.findViewById(R.id.conditionTextView);
        locationTv=view.findViewById(R.id.locationTextView);

        weatherIconImage=view.findViewById(R.id.weatherIconImageView);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void serviceSuccess(Channel channel) {
        progressDialog.dismiss();

        Item item = channel.getItem();

        /*int resource=getContext().getResources().getIdentifier("drawable/icon"+
                item.getCondition().getCode(),null,getActivity().getPackageName());
        Drawable weather=getContext().getResources().getDrawable(resource);*/
        weatherIconImage.setImageResource(R.drawable.cloud);

        temperatureTv.setText(item.getCondition().getTemperature()+"\u00B0"
                +channel.getUnits().getTemperature());
        conditionTv.setText(item.getCondition().getDescription());
        locationTv.setText(service.getLocation());
    }

    @Override
    public void serviceFailure(Exception e) {
        progressDialog.dismiss();
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
