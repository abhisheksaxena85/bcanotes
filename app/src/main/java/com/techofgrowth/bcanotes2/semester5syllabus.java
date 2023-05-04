package com.techofgrowth.bcanotes2;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.google.android.gms.ads.AdView;


public class semester5syllabus extends AppCompatActivity {
    Toolbar toolbar;
    allFunctions allfunctions_obj = new allFunctions();
    CardView computer_graphic_animation, computer_network, internet_programming, software_engineering, computer_architecture;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester5syllabus);

//Toolbar
        toolbar = findViewById(R.id.sem5_toolbar);
        setSupportActionBar(toolbar);//Setting toolbar as default toolbar
        getSupportActionBar().setTitle("BCA Semester 5");//Toolbar Title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Setting the toolbar title color
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.toolbarTitleColor));//Setting color of back button of toolbar of activity

//Adding CLick function on card for intent passing
        computer_graphic_animation = findViewById(R.id.sem5_computer_graphics_animation);
        computer_network = findViewById(R.id.sem5_computer_network);
        internet_programming = findViewById(R.id.sem5_internet_programming);
        software_engineering = findViewById(R.id.sem5_software_engineering);
        computer_architecture = findViewById(R.id.sem5_computer_architecture);

        // first card click
        computer_graphic_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester5syllabus.this,sem5_comp_graphic_animation.class));
            }
        });

        //second card click
        computer_network.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester5syllabus.this,sem5_computer_network.class));
            }
        });

        //third card click
        internet_programming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester5syllabus.this,sem5_internet_programming.class));
            }
        });

        //fourth card click
        software_engineering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester5syllabus.this,sem5_software_engineering.class));
            }
        });

        //fifth card click
        computer_architecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester5syllabus.this,sem5_computer_architecture.class));
            }
        });

//Putting banner adview here
        AdView banner_ad = findViewById(R.id.sem5_banner_id);
        allfunctions_obj.bannerAd(banner_ad);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}