package com.example.bob.abouttheimages;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<ImageView> imgView;

    public MyAdapter(ArrayList<ImageView> imgList){
        imgView = imgList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imgView = (ImageView) LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.image_and_stuff, parent, false);
        ViewHolder vh = new ViewHolder(imgView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgView = imgView.get(position);
    }

    @Override
    public int getItemCount() {
        return imgView.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgView;
        public ViewHolder(View itemView) {
            super(itemView);
            imgView = (ImageView) itemView;
        }
    }
}
