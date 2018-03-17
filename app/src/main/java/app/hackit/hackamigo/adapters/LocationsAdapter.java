package app.hackit.hackamigo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import app.hackit.hackamigo.model.Location;
import app.hackit.hackamigo.utils.OnRecyclerViewItemClickListener;

/**
 * Created by karan on 3/14/2018.
 */

public class LocationsAdapter extends FirebaseRecyclerAdapter<Location,LocationsAdapter.LocationViewHolder> {

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecycleViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecycleViewItemClickListener;
    }


    public LocationsAdapter(Class<Location> modelClass, int modelLayout, Class<LocationViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(LocationViewHolder viewHolder, Location model, int position) {

    }

    public class LocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public LocationViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            /*Bundle bundle=new Bundle();
            bundle.putInt("adapterPos",getAdapterPosition());
            bundle.putParcelable("Location",);
            onRecyclerViewItemClickListener.onItemClicked(bundle);*/
        }
    }
}
