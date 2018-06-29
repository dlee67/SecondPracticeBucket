package com.example.bob.abouttheimages;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

import static com.example.bob.abouttheimages.R.drawable.cave_one;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageView> listOfImgs;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawable imgOne = getResources().getDrawable(R.drawable.cave_one);
        ImageView actualOne = new ImageView(this);
        actualOne.setImageDrawable(imgOne);
        listOfImgs.add(actualOne);

        Drawable imgTwo = getResources().getDrawable(R.drawable.deep_sea);
        ImageView actualTwo = new ImageView(this);
        actualTwo.setImageDrawable(imgTwo);
        listOfImgs.add(actualTwo);

        Drawable imgThree = getResources().getDrawable(R.drawable.floating_city);
        ImageView actualThree = new ImageView(this);
        actualThree.setImageDrawable(imgThree);
        listOfImgs.add(actualThree);

        Drawable imgFour = getResources().getDrawable(R.drawable.space_one);
        ImageView actualFour = new ImageView(this);
        actualFour.setImageDrawable(imgFour);
        listOfImgs.add(actualThree);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(listOfImgs);
        mRecyclerView.setAdapter(mAdapter);
    }
}
