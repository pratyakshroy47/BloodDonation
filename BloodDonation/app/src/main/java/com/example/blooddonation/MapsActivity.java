package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    Button back;
    Double lati, longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        back=findViewById(R.id.button26);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ab=new Intent(MapsActivity.this, CheckAvail.class);
                startActivity(ab);
            }
        });

        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fm.getMapAsync(MapsActivity.this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        map = googleMap;
        CameraUpdate center= CameraUpdateFactory.newLatLngZoom(new LatLng(23.077562442394438,76.85171833105998),10);
        map.moveCamera(center);

        LatLng asta = new LatLng(23.077, 76.851);
        map.addMarker(new MarkerOptions().position(asta).title("Current location").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag)));
        map.moveCamera(CameraUpdateFactory.newLatLng(asta));

        LatLng hosp1 = new LatLng(23.040545903309102, 76.84875946560999);
        map.addMarker(new MarkerOptions().position(hosp1).title("Marker on nearest hospitals").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag_dest)));
        //map.moveCamera(CameraUpdateFactory.newLatLng(hosp1));

        LatLng hosp2 = new LatLng(23.04587360905058, 76.84627480445943);
        map.addMarker(new MarkerOptions().position(hosp2).title("Marker on nearest hospitals").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag_dest)));
        //map.moveCamera(CameraUpdateFactory.newLatLng(hosp2));

        LatLng hosp3 = new LatLng(23.03752057070799, 76.85180156719113);
        map.addMarker(new MarkerOptions().position(hosp3).title("Marker on nearest hospitals").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag_dest)));
        //map.moveCamera(CameraUpdateFactory.newLatLng(hosp3));

        LatLng hosp4 = new LatLng(23.078601296264093, 76.83470709302887);
        map.addMarker(new MarkerOptions().position(hosp4).title("Marker on nearest hospitals").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag_dest)));
        //map.moveCamera(CameraUpdateFactory.newLatLng(hosp4));

        LatLng bbank1 = new LatLng(23.199215352611322, 77.08303052912595);
        map.addMarker(new MarkerOptions().position(bbank1).title("Marker on nearest blood bank").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_flag_bbank)));
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

}