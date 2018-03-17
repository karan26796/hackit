package app.hackit.hackamigo.data;

import org.json.JSONObject;

/**
 * Created by karan on 3/13/2018.
 */

public class Link implements JsonPopulator {

    private String date;

    public String getDate() {
        return date;
    }

    @Override
    public void populate(JSONObject data) {
        date=data.optString("lastBuildDate");
    }
}
