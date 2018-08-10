package com.example.bob.aboutfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContentFragment extends Fragment {

    //onCreateView() is called when the system determines that it's time to draw out the Fragment.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstance){
        return inflater.inflate(R.layout.mainfrag, container, false);
    }
}
