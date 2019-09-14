package com.example.nutritiontracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.*;
import java.io.*;

public class Editprofile extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Editprofile";
    private String username = Login.un;

    private Button editproconfirm, editproback;
    private TextView editprouseremail;
    private EditText editprousersex;
    private EditText editprouserbir;
    private EditText editprousercountry;

    private String newsex;
    private String newbir;
    private String newcountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        startandexit.getInstance().addActivity(this);

        editprouseremail = findViewById(R.id.editprofileusername);
        editprouseremail.setText(username);
        editprousersex = findViewById(R.id.editprofileusersex);
        editprouserbir = findViewById(R.id.editprofileuserbir);
        editprousercountry = findViewById(R.id.editprofileusercountry);

        /*
         * Set the functions for confirm button and back button
         */
        editproconfirm = findViewById(R.id.editprofileconfirm);
        editproconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                newsex = editprousersex.getText().toString();
                newbir = editprouserbir.getText().toString();
                newcountry = editprousercountry.getText().toString();
                changeprofile(username, newsex, newbir, newcountry);
                finish();
            }
        });
        editproback = findViewById(R.id.editprofileback);
        editproback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * @param email
     * @param sex
     * @param bir
     * @param country
     * This method is used to change the user profile
     */
    public void changeprofile(String email, String sex, String bir, String country){

    }
}
