package com.example.blooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteRecords extends AppCompatActivity {

    EditText eid;
    Button delete;
    DBHandler db;
    TextView warning;
    Switch s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_records);

        db=new DBHandler(DeleteRecords.this);

        s=findViewById(R.id.switch1);
        warning=findViewById(R.id.textView3);
        eid=findViewById(R.id.editTextTextPersonName3);
        String id=eid.getText().toString();

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(DeleteRecords.this);
                builder.setCancelable(true);
                builder.setTitle("Alert!");
                builder.setMessage("Once deleted, records will be lost forever.");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        s.setChecked(false);
                        Intent intn=new Intent(DeleteRecords.this, MainActivity.class);
                        startActivity(intn);
                    }
                });

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        warning.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();
            }
        });

        delete=findViewById(R.id.button7);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s.isChecked()){
                    Integer deleterows=db.deletedetails(eid.getText().toString());
                    Intent i=new Intent(DeleteRecords.this, MainActivity.class);
                    startActivity(i);
                    if (deleterows > 0) {
                        Toast.makeText(DeleteRecords.this, "Data deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DeleteRecords.this, "Data not deleted", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(DeleteRecords.this, "Please click on confirmation switch", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}