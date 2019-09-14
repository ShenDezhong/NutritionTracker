package com.example.nutritiontracker;

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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.*;
import java.io.*;

/**
 * This class is used to show the Register page
 * It contains all functions that the Register page needs to implement the feature
 */
public class Register extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Register";

    private Button submitbutton;
    private Button backbutton;
    private FirebaseAuth mAuth;
    private TextView passwordvalid;
    private EditText username;
    private EditText password;
    private EditText confirmpassword;

    //Add some features for the profile
    private String keyusername = "Username/Useremail";
    //private String keyuseraddress = "Address";
    private String keyusercountry = "Country";
    private String keyuserbirth = "Birthday";
    private String keyusersex = "Sex";


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        startandexit.getInstance().addActivity(this);

        mAuth = FirebaseAuth.getInstance();
        passwordvalid = findViewById(R.id.pValid);
        submitbutton = findViewById(R.id.Regsubmit);
        backbutton = findViewById(R.id.Regback);
        username = findViewById(R.id.RegnewUsername);
        password = findViewById(R.id.RegnewPassword);
        confirmpassword = findViewById(R.id.RegconfirmNewPassword);

        /*
         *Set the function of submit button
         */
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(username.getText().toString(),password.getText().toString(), confirmpassword.getText().toString());
                databaseaccount(username.getText().toString());
            }
        });
        /*
         *Set the function of back button
         */
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
    }

    /**
     * @param email was inputted by user
     * @param password was setted by user
     */
    public void createAccount(String email,String password,String confirmpassword){
        if(!password.equals(confirmpassword))
            Toast.makeText(Register.this,"Those 2 password are not compatible,please retry",Toast.LENGTH_SHORT).show();
        else if(password.length() < 8 )
            Toast.makeText(Register.this,"The password should be at least 8 digits",Toast.LENGTH_SHORT).show();
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("Register", "createUserWithEmail:success");
                                //FirebaseUser user = mAuth.getCurrentUser();

                            } else {
                                Log.w("Register", "createUserWithEmail:failed", task.getException());
                                passwordvalid.setText("Authentication failure");
                            }
                        }
                    });
            finish();
        }
    }


    /**
     * @param email Use this email to create document in the database
     */
    public void databaseaccount(String email){
        Map<String, Object> note = new HashMap<>();
        note.put(keyusername, email);
        note.put(keyusersex, "");
        note.put(keyuserbirth, "");
        note.put(keyusercountry, "");
        //note.put(keyuseraddress, "");

        db.collection("Users").document(email).set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Register.this, "Account saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
}
