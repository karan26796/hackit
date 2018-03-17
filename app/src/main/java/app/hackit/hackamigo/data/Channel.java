package app.hackit.hackamigo.data;

import org.json.JSONObject;

/**
 * Created by karan on 3/13/2018.
 */

public class Channel implements JsonPopulator {

    private Units units;
    private Item item;

    public Units getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }


    @Override
    public void populate(JSONObject data) {
        units = new Units();
        units.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));
    }
}
