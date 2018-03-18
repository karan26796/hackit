package app.hackit.hackamigo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.hackit.hackamigo.R;
import app.hackit.hackamigo.activities.DetectDiseaseActivity;
import app.hackit.hackamigo.activities.SoilDetectionActivity;
import app.hackit.hackamigo.adapters.HomeAdapter;
import app.hackit.hackamigo.model.Options;

/**
 * Created by karan on 3/12/2018.
 */

public class HomeFragment extends Fragment implements
        HomeAdapter.onListItemClickListener {
    String title[], subtitle[];
    ArrayList<Options> mOptionsList = new ArrayList<>();

    RecyclerView recyclerView;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getContext().getResources().getStringArray(R.array.names);
        subtitle = getContext().getResources().getStringArray(R.array.names_1);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_home);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        setmOptionsList();
        recyclerView.setAdapter(new HomeAdapter(mOptionsList, this));
    }

    private void setmOptionsList() {
        for (int i = 0; i < title.length; i++)
            mOptionsList.add(new Options(title[i], subtitle[i]));
    }

    @Override
    public void onHomeListItemClicked(View v, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(getContext(), DetectDiseaseActivity.class));
                break;
            case 1:
                startActivity(new Intent(getContext(), SoilDetectionActivity.class));
                break;
        }
    }
}
