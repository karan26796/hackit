package app.hackit.hackamigo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.hackit.hackamigo.R;
import app.hackit.hackamigo.model.Options;

/**
 * Created by karan on 3/12/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
    ArrayList<Options> mList=new ArrayList<>();
    private onListItemClickListener mClickListener;

    public HomeAdapter(ArrayList<Options> mList, onListItemClickListener mClickListener) {
        this.mList = mList;
        this.mClickListener = mClickListener;
    }

    public interface onListItemClickListener {
        void onHomeListItemClicked(View v, int position);
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new HomeAdapter.HomeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {

        Picasso.get()
                .load("http://images.indianexpress.com/2016/07/punjab-farmer-759.jpg")
                .resize(500, 500)
                .centerInside()
                .into(holder.imageView);

        holder.textView1.setText(mList.get(position).getTitle());
        holder.textView2.setText(mList.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView1, textView2;
        ImageView imageView;

        HomeHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_view);
            textView1 = itemView.findViewById(R.id.text_view_1);
            textView2 = itemView.findViewById(R.id.text_view_2);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onHomeListItemClicked(v, getAdapterPosition());
        }
    }
}
