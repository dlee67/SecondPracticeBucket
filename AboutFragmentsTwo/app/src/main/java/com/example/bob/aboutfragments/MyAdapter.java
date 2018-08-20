package com.example.bob.aboutfragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<View> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View checkBox;
        public ViewHolder(CheckBox v) {
            super(v);
            checkBox = v;
        }

        public void setCheckBox(View checkBox){
            this.checkBox = checkBox;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<View> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CheckBox cb = (CheckBox) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.check_box, parent, false);
        ViewHolder vh = new ViewHolder(cb);
        return vh;
    }

    @Override
    //This is where the swapping of the elements happen.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setCheckBox(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size(); //The self explanatory material.
    }

}
