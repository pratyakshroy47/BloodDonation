package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewRecords extends AppCompatActivity {

    Button singlerec, allrecs, back;
    EditText eid;
    //String id=eid.getText().toString();
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        db=new DBHandler(this);

        eid=findViewById(R.id.editTextTextPersonName);
        allrecs=findViewById(R.id.button5);
        allrecs.requestFocus();
        allrecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<HashMap<String, String>> AL=db.getdetails();
                ListView lv=(ListView) findViewById(R.id.user_list);
                ListAdapter adapter=new SimpleAdapter(ViewRecords.this, AL, R.layout.list_row,new String[]{"dononame", "id", "bloodtype", "donodate"}, new int[]{R.id.dononame, R.id.id, R.id.bloodtype, R.id.ddate});
                lv.setAdapter(adapter);
            }
        });

        //remove following code in case of error
        singlerec=findViewById(R.id.button4);
        singlerec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<HashMap<String, String>> AL=db.getrecordbyid(eid.getText().toString());
                ListView lv=(ListView) findViewById(R.id.user_list);
                ListAdapter adapter=new SimpleAdapter(ViewRecords.this, AL, R.layout.list_row,new String[]{"dononame","id","bloodtype"}, new int[]{R.id.dononame, R.id.id, R.id.bloodtype});
                lv.setAdapter(adapter);
            }
        });

        back=findViewById(R.id.button8);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ViewRecords.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

}