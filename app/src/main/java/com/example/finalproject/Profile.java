package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sp = getSharedPreferences("Data", MODE_PRIVATE);

        TextView name = findViewById(R.id.profileName);
        TextView email = findViewById(R.id.profileEmail);

        String nameStr = sp.getString("name", "User");
        String emailStr = sp.getString("email", "email");

        name.setText("Name: " + nameStr);
        email.setText("Email: " + emailStr);

        ImageView arrow = findViewById(R.id.arrow);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toHome = new Intent(Profile.this, Home.class);
                startActivity(toHome);
            }
        });

        Button logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logingOut = new Intent(Profile.this, MainActivity.class);
                startActivity(logingOut);
            }
        });
    }
}