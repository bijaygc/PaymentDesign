package com.android.confirmationapplication;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.smarteist.autoimageslider.SliderView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

 public class MainActivity extends AppCompatActivity implements LocationListener
{
     // private String stringDate;
    private TextView tVDate, tVTime, tVLocationArea;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setDateAndTime();
        slider();
        grantPermission();
        checkLocationIsEnabledOrNot();
        getLocation();
//            SliderView imageSlider = findViewById(R.id.imageslider);
//
//            List<Integer> slideModels = new ArrayList<>();
//            slideModels.add(R.drawable.image3);
//            slideModels.add(R.drawable.image4);
//            MyAdapter myAdapter = new MyAdapter(slideModels);
//            imageSlider.setSliderAdapter(myAdapter);
        }
    private void getLocation(){
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500,5,(LocationListener) this);
        }catch (SecurityException e){
                e.printStackTrace();
        }
    }


    private void checkLocationIsEnabledOrNot() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = false;
        boolean networkEnabled = false;

        try{
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(!gpsEnabled && !networkEnabled){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Enable Gps Service")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    }).setNegativeButton("Cancel",null)
                    .show();
        }
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
        tVLocationArea = findViewById(R.id.tVLocationArea);
    }

    private void setDateAndTime(){
        String currentDate = new SimpleDateFormat("EE, dd MMMM  ", Locale.getDefault()).format(new Date());
        System.out.println("date:: "+ currentDate);
        String currentTime = new SimpleDateFormat(" HH:mm", Locale.getDefault()).format(new Date());
        System.out.println("time::: " + currentTime);

        tVDate.setText(currentDate);
        tVTime.setText(currentTime);

    }
    public void grantPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION},100);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
            System.out.println("address::: " + addresses.get(0).getAddressLine(0));
            tVLocationArea.setText(addresses.get(0).getAddressLine(0));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}