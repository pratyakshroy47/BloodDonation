package com.example.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    EditText un, pass, repass;
    Button signup, back;
    DBhelper db;

    private static final Pattern passmin=Pattern.compile("^"+"(?=.*[@#$%^&+=])"+"(?=\\S+$)"+".{4,}"+"$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup=findViewById(R.id.button9);
        back=findViewById(R.id.button13);
        un=findViewById(R.id.editTextTextPersonName9);
        pass=findViewById(R.id.editTextTextPersonName10);
        repass=findViewById(R.id.editTextTextPersonName11);
        db=new DBhelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=un.getText().toString();
                String password=pass.getText().toString();
                String repassword=repass.getText().toString();

                if(username.equals("")||password.equals(""))
                    Toast.makeText(SignupActivity.this, "Enter all credentials.", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(repassword)){
                        Boolean checkuser=db.check(username);
                        if(checkuser==false){
                            Boolean insert=db.insert(username, password);
                            if(insert==true){
                                Toast.makeText(SignupActivity.this, "Registered.", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(SignupActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignupActivity.this, "User exists. Please login.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignupActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }
                }
                enterallfields();
                validatePassword();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginActivity();
            }
        });

    }

    private boolean validatePassword() {
        String password=pass.getText().toString().trim();
        if (password.isEmpty()) {
            pass.setError("Field can not be empty");
            return false;
        }
        else if (!passmin.matcher(password).matches()) {
            pass.setError("Password does not meet minimum requirements");
            return false;
        } else {
            pass.setError(null);
            return true;
        }
    }

    public void enterallfields(){
        String a=un.getText().toString();
        String b=pass.getText().toString();
        String c=repass.getText().toString();
        if(a.equals(null)||b.equals(null)||c.equals(null)){
            signup.setClickable(false);
        }
    }

    public void loginActivity(){
        Intent i=new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}