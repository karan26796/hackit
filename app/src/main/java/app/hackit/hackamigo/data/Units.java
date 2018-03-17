package app.hackit.hackamigo.data;

import org.json.JSONObject;

/**
 * Created by karan on 3/13/2018.
 */

public class Units implements JsonPopulator{

    private String temperature;

    public String getTemperature() {
        return temperature;
    }


    @Override
    public void populate(JSONObject data) {
        temperature=data.optString("temperature");

    }
}
