package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class CheckAvail extends AppCompatActivity  {

    TextView anim;
    Button check, call, book, back, test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_avail);

        check=findViewById(R.id.button28);
        book=findViewById(R.id.button18);
        call=findViewById(R.id.button17);
        anim=findViewById(R.id.textView4);
        back=findViewById(R.id.button27);

        test=findViewById(R.id.button500);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abc=new Intent(CheckAvail.this, TestActivity.class);
                startActivity(abc);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent s=new Intent(CheckAvail.this, MapsActivity.class);
                startActivity(s);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(CheckAvail.this, BookAppointment.class);
                startActivity(a);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:104"));
                startActivity(intent);
            }
        });

        anim.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        anim.setSelected(true);
        anim.setSingleLine(true);
        anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri=Uri.parse("http://nbtc.naco.gov.in/");
                Intent intent=new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ab=new Intent(CheckAvail.this, MainActivity.class);
                startActivity(ab);
            }
        });

    }

}