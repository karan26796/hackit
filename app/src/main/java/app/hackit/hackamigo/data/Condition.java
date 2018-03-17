package app.hackit.hackamigo.data;

import org.json.JSONObject;

/**
 * Created by karan on 3/13/2018.
 */

public class Condition implements JsonPopulator{

    private int code;
    private int temperature;
    String date;

    private String description;

    public int getCode() {
        return code;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public void populate(JSONObject data) {

        code=data.optInt("code");
        temperature=data.optInt("temp");
        date=data.optString("date");

        description=data.optString("text");

    }
}
