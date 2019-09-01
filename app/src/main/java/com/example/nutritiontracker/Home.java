package com.example.nutritiontracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private Button logout;
    private Button list;
    private Button summary;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        startandexit.getInstance().addActivity(this);

        /*
         *Set the function of logout button
         */
        logout = findViewById(R.id.homelogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutmethod();
            }
        });
        /*
         *Set the function of list button
         */
        list = findViewById(R.id.homelist);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() ,"Entry to list", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Home.this, ProductList.class);
                startActivity(intent);
            }
        });

        /*
         *Set the function of summary button
         */
        summary = findViewById(R.id.homesummary);
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Summary.class);
                startActivity(intent);

            }
        });
        /*
         *Set the function of exit button
         */
        exit = findViewById(R.id.homeexit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startandexit.getInstance().exit();

            }
        });

    }

    /**
     * log out function
     */
    private void logoutmethod() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(Home.this,"Successfully log out", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Home.this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//clear the information on the login page
        startActivity(intent);
    }
}
