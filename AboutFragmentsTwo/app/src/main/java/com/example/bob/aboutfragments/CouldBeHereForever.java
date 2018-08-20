package com.example.bob.aboutfragments;
//If I am using a support library in the MainActivity, then I have use the
//support library in the CouldBeHereForever.

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CouldBeHereForever extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.you_are_a_lier, container, false);
    }
}
