package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckAppointment extends AppCompatActivity {

    Button view, back;
    DBbook db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_appointment);

        db=new DBbook(this);

        view=findViewById(R.id.button21);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<HashMap<String, String>> AL=db.viewbookings();
                ListView lv=(ListView) findViewById(R.id.appointment_list);
                ListAdapter adapter=new SimpleAdapter(CheckAppointment.this, AL, R.layout.list_row_appointment,new String[]{"dononame", "id", "donodate", "email"}, new int[]{R.id.dononame, R.id.id, R.id.ddate, R.id.mail});
                lv.setAdapter(adapter);
            }
        });

        back=findViewById(R.id.button22);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b=new Intent(CheckAppointment.this, BookAppointment.class);
                startActivity(b);
            }
        });

    }

}