package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText un, pass;
    Button login, signup;
    DBhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        un=findViewById(R.id.editTextTextPersonName12);
        pass=findViewById(R.id.editTextTextPersonName13);
        login=findViewById(R.id.button10);
        signup=findViewById(R.id.button11);
        db=new DBhelper(this);
        un.requestFocus();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=un.getText().toString();
                String password=pass.getText().toString();
                if(username.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass=db.checkboth(username, password);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Sign in successful. Welcome.", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignup();
            }
        });
    }

    public void openSignup(){
        Intent i=new Intent(this, SignupActivity.class);
        startActivity(i);
    }

}