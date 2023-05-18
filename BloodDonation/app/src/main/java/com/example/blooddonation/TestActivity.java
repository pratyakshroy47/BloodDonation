package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map1;
    Button back;
    Double lati, longi;
    FusedLocationProviderClient fusedLocationProviderClient;
    Map<String, String> numDict=new HashMap<String, String>();
    public String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        back=findViewById(R.id.button501);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ab=new Intent(TestActivity.this, CheckAvail.class);
                startActivity(ab);
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(TestActivity.this);
        if (ActivityCompat.checkSelfPermission(TestActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(TestActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            getCurrentLocation();
        }
        else{
            ActivityCompat.requestPermissions(TestActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION} , 100);
        }

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map1);
        fm.getMapAsync(TestActivity.this);

    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if(location!=null){
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        st= "https://maps.google.com/?q="+latitude+","+longitude;
                    }
                }
            });
        }
        else{
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map1 = googleMap;
        CameraUpdate center= CameraUpdateFactory.newLatLngZoom(new LatLng(23.077562442394438,76.85171833105998),10);
        map1.moveCamera(center);

        LatLng asta = new LatLng(23.077, 76.851);
        map1.addMarker(new MarkerOptions().position(asta).title("Current location").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag)));
        map1.moveCamera(CameraUpdateFactory.newLatLng(asta));

        LatLng hosp1 = new LatLng(23.040545903309102, 76.84875946560999);
        map1.addMarker(new MarkerOptions().position(hosp1).title("Marker on nearest hospitals").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag_dest)));
        //map.moveCamera(CameraUpdateFactory.newLatLng(hosp1));

        LatLng hosp2 = new LatLng(23.04587360905058, 76.84627480445943);
        map1.addMarker(new MarkerOptions().position(hosp2).title("Marker on nearest hospitals").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag_dest)));
        //map.moveCamera(CameraUpdateFactory.newLatLng(hosp2));

        LatLng hosp3 = new LatLng(23.03752057070799, 76.85180156719113);
        map1.addMarker(new MarkerOptions().position(hosp3).title("Marker on nearest hospitals").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag_dest)));
        //map.moveCamera(CameraUpdateFactory.newLatLng(hosp3));

        LatLng hosp4 = new LatLng(23.078601296264093, 76.83470709302887);
        map1.addMarker(new MarkerOptions().position(hosp4).title("Marker on nearest hospitals").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag_dest)));
        //map.moveCamera(CameraUpdateFactory.newLatLng(hosp4));

        LatLng bbank1 = new LatLng(23.199215352611322, 77.08303052912595);
        map1.addMarker(new MarkerOptions().position(bbank1).title("Marker on nearest blood bank").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag_bbank)));
        //map.moveCamera(CameraUpdateFactory.newLatLng(bbank1));

    }

    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                }
                else {
                    Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

}