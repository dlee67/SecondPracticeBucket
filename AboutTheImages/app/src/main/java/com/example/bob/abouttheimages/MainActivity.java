package com.example.bob.abouttheimages;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import static com.example.bob.abouttheimages.R.drawable.cave_one;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Drawable> listOfImgs = new ArrayList<Drawable>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LinearLayout roll;
    private LayoutInflater inflater;
    private RelativeLayout someView; // Will be used as a global scope to reference the newly
                                     // initialized object, which will be added to an ArrayList of
                                     // RelativeLayout, representing ImageView "holding" a
                                     // check box.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPictures();
        roll = findViewById(R.id.imgs);
        inflater = LayoutInflater.from(getApplicationContext());
        toRelativeLayout();
    }

    public void toRelativeLayout(){
        someView = (RelativeLayout) inflater.inflate(R.layout.image_and_stuff, null);
        ImageView view = (ImageView) someView.getChildAt(0);
        CheckBox checkBox = (CheckBox) someView.getChildAt(1);
        view.setImageDrawable(listOfImgs.get(0));
        roll.addView(someView);
    }

    protected void initPictures(){
        Drawable imgOne = getResources().getDrawable(R.drawable.cave_one);
        listOfImgs.add(imgOne);
        //ImageView actualOne = new ImageView(this);
        //actualOne.setImageDrawable(imgOne);
        //listOfImgs.add(actualOne);

        Drawable imgTwo = getResources().getDrawable(R.drawable.deep_sea);
        listOfImgs.add(imgTwo);
        //ImageView actualTwo = new ImageView(this);
        //actualTwo.setImageDrawable(imgTwo);
        //listOfImgs.add(actualTwo);

        Drawable imgThree = getResources().getDrawable(R.drawable.floating_city);
        listOfImgs.add(imgThree);
        //ImageView actualThree = new ImageView(this);
        //actualThree.setImageDrawable(imgThree);
        //listOfImgs.add(actualThree);

        Drawable imgFour = getResources().getDrawable(R.drawable.space_one);
        listOfImgs.add(imgFour);
        //ImageView actualFour = new ImageView(this);
        //actualFour.setImageDrawable(imgFour);
        //listOfImgs.add(actualThree);
    }
}
