package app.hackit.hackamigo.utils;

import android.os.Bundle;

/**
 * Created by karan on 3/17/2018.
 */

public interface OnRecyclerViewItemClickListener {

    /**
     * @param bundle to hold the data in item of RecyclerView
     */
    void onItemClicked(Bundle bundle);
}