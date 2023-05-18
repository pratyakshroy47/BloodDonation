package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditRecords extends AppCompatActivity {

    Button update;
    EditText dname, rname, eid, ebloodtype, ddate, rdate;
    Intent intent;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_records);

        update=findViewById(R.id.button14);
        dname=findViewById(R.id.editTextTextPersonName15);
        rname=findViewById(R.id.editTextTextPersonName16);
        eid=findViewById(R.id.editTextTextPersonName14);
        ebloodtype=findViewById(R.id.editTextTextPersonName17);
        ddate=findViewById(R.id.editTextTextPersonName18);
        rdate=findViewById(R.id.editTextTextPersonName19);
        db=new DBHandler(this);
        eid.requestFocus();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dononame=dname.getText().toString();
                String recname=rname.getText().toString();
                String id=eid.getText().toString();
                String bloodtype=ebloodtype.getText().toString();
                String donodate=ddate.getText().toString();
                String recdate=rdate.getText().toString();

                Boolean updaterecords=db.updaterecords(dononame, recname, id, bloodtype, donodate, recdate);
                if(updaterecords==true){
                    Toast.makeText(EditRecords.this, "Details updated successfully",Toast.LENGTH_SHORT).show();
                    intent=new Intent(EditRecords.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(EditRecords.this, "Can't update record to table.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}