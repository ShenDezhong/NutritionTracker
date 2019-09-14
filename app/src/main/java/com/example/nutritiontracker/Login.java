package com.example.nutritiontracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.*;
import java.io.*;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {

    public static String user;
    EditText email,password;
    Button login,signup;
    FirebaseAuth mAuth =FirebaseAuth.getInstance();
    public static String un;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        startandexit.getInstance().addActivity(this);

        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpassword);
        login=findViewById(R.id.loginsignin);
        signup=findViewById(R.id.loginsignup);

        /*
         *Set the function of login button
         */
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                user =email.getText().toString();
                if(email.getText().toString().trim().isEmpty()||password.getText().toString().trim().isEmpty()){
                    Toast.makeText(Login.this,"No username or no password", Toast.LENGTH_SHORT).show();
                }
                else{
                    loginEvent();
                    //startActivity(new Intent(Login.this,Home.class));

                }

            }
        });

        /*
         *Set the function of signup button
         */
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Login.this,Register.class));
            }
        });

    }

    /**
     * Verify if user's email and password are valid
     * Compared with authentication
     */
    private void loginEvent(){

        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())

                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        un=email.getText().toString();
                        email.setText("");
                        password.setText("");
                        startActivity(new Intent(Login.this,Home.class));
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Login.this,"Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
