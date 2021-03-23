package com.android.confirmationapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.smarteist.autoimageslider.SliderView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
   // private String stringDate;
    private TextView tVDate, tVTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setDateAndTime();
        slider();
//            SliderView imageSlider = findViewById(R.id.imageslider);
//
//            List<Integer> slideModels = new ArrayList<>();
//            slideModels.add(R.drawable.image3);
//            slideModels.add(R.drawable.image4);
//            MyAdapter myAdapter = new MyAdapter(slideModels);
//            imageSlider.setSliderAdapter(myAdapter);
        }


    private void slider() {
        SliderView imageSlider = findViewById(R.id.imageslider);

        List<Integer> slideModels = new ArrayList<>();
        slideModels.add(R.drawable.image4);
        slideModels.add(R.drawable.image3);
        MyAdapter myAdapter = new MyAdapter(slideModels);
        imageSlider.setSliderAdapter(myAdapter);
    }
    private void init(){
        tVDate = findViewById(R.id.tVDate);
        tVTime = findViewById(R.id.tVTime);
    }

    private void setDateAndTime(){
        String currentDate = new SimpleDateFormat("EE, dd MMMM  ", Locale.getDefault()).format(new Date());
        System.out.println("date:: "+ currentDate);
        String currentTime = new SimpleDateFormat(" HH:mm", Locale.getDefault()).format(new Date());
        System.out.println("time::: " + currentTime);

        tVDate.setText(currentDate);
        tVTime.setText(currentTime);

    }
}