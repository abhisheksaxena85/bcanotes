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

public class semester4syllabus extends AppCompatActivity {
    Toolbar toolbar;
    allFunctions allfunctions_obj = new allFunctions();
    CardView operating_system, dbms_sql, management_info_system,visual_basic_language, system_analysis_design;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester4syllabus);

//Toolbar
        toolbar = findViewById(R.id.sem4_toolbar);
        setSupportActionBar(toolbar);//Setting toolbar as default toolbar
        getSupportActionBar().setTitle("BCA Semester 4");//Toolbar Title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Setting the toolbar title color
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.toolbarTitleColor));//Setting color of back button of toolbar of activity

//Adding CLick function on card for intent passing
        operating_system = findViewById(R.id.sem4_operating_system);
        dbms_sql = findViewById(R.id.sem4_dbms_sql);
        management_info_system = findViewById(R.id.sem4_management_info_system);
        visual_basic_language = findViewById(R.id.sem4_visual_basic);
        system_analysis_design = findViewById(R.id.sem4_system_analysis_design);

        // first card click
        operating_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester4syllabus.this,sem4_operating_system.class));
            }
        });

        //second card click
        dbms_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester4syllabus.this,sem4_dbms_sql.class));
            }
        });

        //third card click
        management_info_system.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester4syllabus.this,sem4_management_info_system.class));
            }
        });

        //fourth card click
        visual_basic_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester4syllabus.this,sem4_visual_basic.class));
            }
        });

        //fifth card click
        system_analysis_design.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester4syllabus.this,sem4_system_analysis.class));
            }
        });

//Putting banner adview here
        AdView banner_ad = findViewById(R.id.sem4_banner_id);
        allfunctions_obj.bannerAd(banner_ad);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}