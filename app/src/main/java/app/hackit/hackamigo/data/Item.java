package app.hackit.hackamigo.data;

import org.json.JSONObject;

/**
 * Created by karan on 3/13/2018.
 */

public class Item implements JsonPopulator {

    public Condition getCondition() {
        return condition;
    }

    private Condition condition;
    private Link link;

    @Override
    public void populate(JSONObject data) {

        condition=new Condition();
        condition.populate(data.optJSONObject("condition"));

        link=new Link();
        link.populate(data.optJSONObject("link"));

    }
}
