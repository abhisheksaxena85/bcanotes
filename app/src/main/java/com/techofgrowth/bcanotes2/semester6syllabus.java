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

public class semester6syllabus extends AppCompatActivity {
Toolbar toolbar;
allFunctions allfunctions_obj = new allFunctions();
CardView multimedia_concept, artificial_intelli, web_technology,intro_to_net;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester6syllabus);

//Toolbar
        toolbar = findViewById(R.id.sem6_toolbar);
        setSupportActionBar(toolbar);//Setting toolbar as default toolbar
        getSupportActionBar().setTitle("BCA Semester 6");//Toolbar Title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Setting the toolbar title color
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.toolbarTitleColor));//Setting color of back button of toolbar of activity

//Adding CLick function on card for intent passing
        multimedia_concept = findViewById(R.id.sem6_multimedia_concepts);
        artificial_intelli = findViewById(R.id.sem6_ai);
        web_technology = findViewById(R.id.sem6_web_tech);
        intro_to_net = findViewById(R.id.sem6_intro_to__net);
//        computer_architecture = findViewById(R.id.sem6_computer_architecture);

        // first card click
        multimedia_concept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester6syllabus.this,sem6_multimedia.class));
            }
        });

        //second card click
        artificial_intelli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester6syllabus.this,sem6_artificial_intelligence.class));
            }
        });

        //third card click
        web_technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester6syllabus.this,sem6_web_technology.class));
            }
        });

        //fourth card click
        intro_to_net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester6syllabus.this,sem6_net_framework.class));
            }
        });

//Putting banner adview here
        AdView banner_ad = findViewById(R.id.sem6_banner_id);
        allfunctions_obj.bannerAd(banner_ad);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}