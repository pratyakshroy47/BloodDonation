package com.example.blooddonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.zip.Inflater;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    //Button viewrecords, addrecords, deleterecords, editrecords, checkavail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        viewrecords=findViewById(R.id.button);
        addrecords=findViewById(R.id.button2);
        deleterecords=findViewById(R.id.button3);
        editrecords=findViewById(R.id.button12);
        checkavail=findViewById(R.id.button15);

        editrecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(MainActivity.this, EditRecords.class);
                startActivity(a);
            }
        });

        viewrecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, ViewRecords.class);
                startActivity(i);
            }
        });

        addrecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, AddRecords.class);
                startActivity(i);
            }
        });

        deleterecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, DeleteRecords.class);
                startActivity(i);
            }
        });

        checkavail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ab=new Intent(MainActivity.this, CheckAvail.class);
                startActivity(ab);
            }
        });
        */
    }

    //menu code start
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent i=new Intent(MainActivity.this, AddRecords.class);
                startActivity(i);
                return true;
            case R.id.item2:
                Intent a=new Intent(MainActivity.this, EditRecords.class);
                startActivity(a);
                return true;
            case R.id.item3:
                Intent ib=new Intent(MainActivity.this, DeleteRecords.class);
                startActivity(ib);
                return true;
            case R.id.item4:
                Intent ia=new Intent(MainActivity.this, ViewRecords.class);
                startActivity(ia);
                return true;
            default:return super.onOptionsItemSelected(item);
        }
    }
    //menu code end

}