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

public class semester3syllabus extends AppCompatActivity {
Toolbar toolbar;
allFunctions allfunctions_obj = new allFunctions();
CardView computer_oriented_num_analysis, computer_org, data_structure, object_oriented_programming,org_behave;
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester3syllabus);

//Toolbar
        toolbar = findViewById(R.id.sem3_toolbar);
        setSupportActionBar(toolbar);//Setting toolbar as default toolbar
        getSupportActionBar().setTitle("BCA Semester 3");//Toolbar Title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Setting the toolbar title color
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.toolbarTitleColor));//Setting color of back button of toolbar of activity

//Adding CLick function on card for intent passing
        computer_oriented_num_analysis = findViewById(R.id.sem3_computer_oriented_numerical_analysis);
        computer_org =                   findViewById(R.id.sem3_computer_organization);
        data_structure =                 findViewById(R.id.sem3_data_structure);
        object_oriented_programming =    findViewById(R.id.sem3_object_oriented_programming);
        org_behave =                     findViewById(R.id.sem3_organizational_behave);

        // first card click
        computer_oriented_num_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester3syllabus.this,sem3_CONA.class));
            }
        });

        //second card click
        computer_org.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester3syllabus.this,sem3_comp_org.class));
            }
        });

        //third card click
        data_structure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester3syllabus.this,sem3_data_struc.class));
            }
        });

        //fourth card click
        object_oriented_programming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester3syllabus.this,sem3_oop_using_c_plus.class));
            }
        });

        //fifth card click
        org_behave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester3syllabus.this,sem3_org_behave.class));
            }
        });

//Putting banner adview here
        AdView banner_ad = findViewById(R.id.sem3_banner_id);
        allfunctions_obj.bannerAd(banner_ad);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}