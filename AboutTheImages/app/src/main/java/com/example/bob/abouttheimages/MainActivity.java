package com.example.bob.abouttheimages;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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
    private ImageView view;
    private CheckBox checkBox;
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
        roll.addView(toRelativeLayout());
        roll.addView(toRelativeLayoutSecond());
    }

    // A new technique? Well, I could make an object which holds ImageView and CheckBox, where
    // if I am initializing those two objects, waiting to be appended to the RelativeLayout,
    // I could call them from the View object that is passed through onClick().
    public RelativeLayout toRelativeLayout(){
        RelativeLayout someView = (RelativeLayout) inflater.inflate(R.layout.image_and_stuff, null);
        view = (ImageView) someView.getChildAt(0);
        checkBox = (CheckBox) someView.getChildAt(1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isSelected() == false){
                    v.setSelected(true);
                    checkBox.setChecked(true);
                }else if(v.isSelected() == true){
                    v.setSelected(false);
                    checkBox.setChecked(false);
                }
            }
        });
        view.setImageDrawable(listOfImgs.get(0));
        return someView;
    }

    public RelativeLayout toRelativeLayoutSecond(){
        someView = (RelativeLayout) inflater.inflate(R.layout.image_and_stuff, null);
        view = (ImageView) someView.getChildAt(0);
        checkBox = (CheckBox) someView.getChildAt(1);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.isSelected() == false){
                    v.setSelected(true);
                    checkBox.setChecked(true);
                }else if(v.isSelected() == true){
                    v.setSelected(false);
                    checkBox.setChecked(false);
                }
            }
        });
        view.setImageDrawable(listOfImgs.get(1));
        return someView;
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
