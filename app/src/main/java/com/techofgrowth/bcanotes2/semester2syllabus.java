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

public class semester2syllabus extends AppCompatActivity {
Toolbar toolbar;
allFunctions allfunctions_obj = new allFunctions();
CardView digital_elec,discrete_math,second_math,c_programming,managerial_eco;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester2syllabus);

//Toolbar
        toolbar = findViewById(R.id.sem2_toolbar);
        setSupportActionBar(toolbar);//Setting toolbar as default toolbar
        getSupportActionBar().setTitle("BCA Semester 2");//Toolbar Title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Setting the toolbar title color
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.toolbarTitleColor));//Setting color of back button of toolbar of activity

//Adding CLick function on card for intent passing
        digital_elec = findViewById(R.id.sem2_digital_elec);
        discrete_math = findViewById(R.id.sem2_discrete_math);
        second_math = findViewById(R.id.sem2_second_math);
        c_programming = findViewById(R.id.sem2_c_programming);
        managerial_eco = findViewById(R.id.sem2_managerial_eco);

        // first card click
        digital_elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester2syllabus.this,sem2_digital_electronic.class));
            }
        });

        //second card click
        discrete_math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester2syllabus.this,sem2_discrete_mathematics.class));
            }
        });

        //third card click
        second_math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester2syllabus.this,sem2_second_mathematics.class));
            }
        });

        //fourth card click
        c_programming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester2syllabus.this,sem2_programming_in_c.class));
            }
        });

        //fifth card click
        managerial_eco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester2syllabus.this,sem2_managerial_economics.class));
            }
        });

//Putting banner adview here
        AdView banner_ad = findViewById(R.id.sem2_banner_id);
        allfunctions_obj.bannerAd(banner_ad);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}