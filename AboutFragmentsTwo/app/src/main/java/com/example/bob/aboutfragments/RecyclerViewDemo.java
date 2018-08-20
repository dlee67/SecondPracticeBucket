package com.example.bob.aboutfragments;
//If I am using a support library in the MainActivity, then I have use the
//support library in the RecyclerViewDemo.

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;

public class RecyclerViewDemo extends ListFragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter; // How the elements are binded and displayed in the RecyclerView.
    private RecyclerView.LayoutManager mLayoutManager; //Responsible for measuring and positioning items.
    ArrayList<View> listOfCheckBoxes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        mRecyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        mRecyclerView.setHasFixedSize(true); // Betters the performance, apparently.
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new MyAdapter(listOfCheckBoxes);
        mRecyclerView.setAdapter(mAdapter);
        return mRecyclerView;
    }

    @SuppressLint("ResourceType")
    public void populateList(){
        View newCheckBox = null;
        LayoutInflater inflater = getLayoutInflater();
        for(int counter = 0; counter < listOfCheckBoxes.size(); counter++){
            newCheckBox = inflater.inflate(R.layout.check_box, null, false);
            listOfCheckBoxes.add(newCheckBox);
        }
    }
}
