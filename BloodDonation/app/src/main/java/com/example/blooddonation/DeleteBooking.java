package com.example.blooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteBooking extends AppCompatActivity {

    EditText eid, mail;
    Button delete;
    DBbook db;
    TextView warning;
    Switch s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_booking);

        db=new DBbook(this);

        s=findViewById(R.id.switch3);
        warning=findViewById(R.id.textView69);
        eid=findViewById(R.id.editTextTextPersonName25);
        mail=findViewById(R.id.editTextTextPersonName26);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(DeleteBooking.this);
                builder.setCancelable(true);
                builder.setTitle("Alert!");
                builder.setMessage("This confirms blood was donated or booking was cancelled.");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        s.setChecked(false);
                        Intent intn=new Intent(DeleteBooking.this, MainActivity.class);
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

        delete=findViewById(R.id.button25);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=eid.getText().toString();
                if(s.isChecked()){
                    Integer deleterows=db.deletebooking(eid.getText().toString());
                    Intent i=new Intent(DeleteBooking.this, MainActivity.class);
                    startActivity(i);
                    if (deleterows > 0) {
                        Toast.makeText(DeleteBooking.this, "Done.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DeleteBooking.this, "Booking record not deleted.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(DeleteBooking.this, "Please click on confirmation switch", Toast.LENGTH_SHORT).show();
                }

                String emailsend=mail.getText().toString();
                String emailsubject="Booking confirmed and finished.";
                String emailbody="Your booking for blood donation with ID: "+id+" is done. \n For more details please contact +919303961043. \n Please do not reply to this email, it is sent by an automated service.";
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { emailsend });
                intent.putExtra(Intent.EXTRA_SUBJECT, emailsubject);
                intent.putExtra(Intent.EXTRA_TEXT, emailbody);
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

                //Intent ab=new Intent(DeleteBooking.this, BookAppointment.class);
                //startActivity(ab);

            }
        });

    }

}