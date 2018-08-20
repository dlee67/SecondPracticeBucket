package com.example.bob.aboutfragments;

//If I am using a support library in the MainActivity, then I have use the
//support library in the ExampleFragment.
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExampleFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState){
        return inflater.inflate(R.layout.example_layout, container, false);
    }
}
