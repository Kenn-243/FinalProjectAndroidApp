package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sp = getSharedPreferences("Data", MODE_PRIVATE);

        TextView textName = findViewById(R.id.textName);

        String name = sp.getString("name", "User");

        textName.setText("Welcome, " + name);

        ImageView profileIcon = findViewById(R.id.imageProfile);
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toProfile = new Intent(Home.this, Profile.class);
                startActivity(toProfile);
            }
        });

        ImageView appearDesc = findViewById(R.id.china);
        Glide.with(Home.this).load("https://d3hne3c382ip58.cloudfront.net/files/uploads/bookmundi/resized/cmsfeatured/visit-china-feature-image-1557459487-785X440.jpg").into(appearDesc);

        appearDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new DescriptionFragment());
            }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.descFrame, fragment);
        fragmentTransaction.commit();
    }
}