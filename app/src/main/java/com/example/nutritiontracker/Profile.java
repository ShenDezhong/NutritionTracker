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

public class Profile extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Profile";
    private String username = Login.un;

    private Button proedit, proback;
    private TextView prousername;
    private TextView prousersex;
    private TextView prouserbir;
    private TextView prousercountry;

    private String useremail;
    private String usersex;
    private String userbirth;
    private String usercounty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        startandexit.getInstance().addActivity(this);

        db.collection("Users").document(username).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        useremail = documentSnapshot.getString("Username/Useremail");
                        usersex = documentSnapshot.getString("Sex");
                        userbirth = documentSnapshot.getString("Birthday");
                        usercounty = documentSnapshot.getString("Country");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Profile.this,"Problem ---1---", Toast.LENGTH_SHORT).show();
                        Log.v("---1---",e.getMessage());
                    }
                });

        prousername = findViewById(R.id.profileusername);
        prousersex = findViewById(R.id.profileusersex);
        prouserbir = findViewById(R.id.profileuserbirthday);
        prousercountry = findViewById(R.id.profileusercountry);

        prousername.setText(useremail);
        prousersex.setText(usersex);
        prouserbir.setText(userbirth);
        prousercountry.setText(usercounty);
        
        /*
         * Set functions for edit and back
         */
        proedit = findViewById(R.id.profileedit);
        proedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Editprofile.class);
                startActivity(intent);
                finish();
            }
        });
        proback = findViewById(R.id.profileback);
        proback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
