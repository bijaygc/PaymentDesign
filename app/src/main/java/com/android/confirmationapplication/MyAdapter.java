package com.android.confirmationapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class MyAdapter extends SliderViewAdapter<MyAdapter.MyViewHolder> {
    List<Integer> imageList;

    MyAdapter(List<Integer> list) {
        this.imageList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
viewHolder.imageView.setImageResource(imageList.get(position));
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    class MyViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
