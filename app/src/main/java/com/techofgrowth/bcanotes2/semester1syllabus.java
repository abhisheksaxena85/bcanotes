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

public class semester1syllabus extends AppCompatActivity {
Toolbar toolbar;
allFunctions allfunctions_obj = new allFunctions();
CardView sem1_foc_card,sem1_fom_card,sem1_pcs_card,sem1_lang_and_comm_card,sem1_math_card;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester1syllabus);

//Toolbar
        toolbar = findViewById(R.id.sem1_toolbar);
        setSupportActionBar(toolbar);//Setting toolbar as default toolbar
        getSupportActionBar().setTitle("BCA Semester 1");//Toolbar Title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Setting the toolbar title color
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.toolbarTitleColor));//Setting color of back button of toolbar of activity

//Setting CardView onclick here

        sem1_foc_card = findViewById(R.id.sem1_foc);
        sem1_fom_card = findViewById(R.id.sem1_fom);
        sem1_pcs_card = findViewById(R.id.sem1_c_lang);
        sem1_lang_and_comm_card = findViewById(R.id.sem1_lang_and_comm);
        sem1_math_card = findViewById(R.id.sem1_math);
        //first card
        sem1_foc_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester1syllabus.this, sem1_foc.class));
            }
        });
        //Second Card
        sem1_fom_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester1syllabus.this, sem1_fom.class));
            }
        });
        //Third Card
        sem1_pcs_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester1syllabus.this, sem1_c_lang.class));
            }
        });
        //Fourth Card
        sem1_lang_and_comm_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester1syllabus.this, sem1_lang_and_comm.class));
            }
        });
        //Fifth Card
        sem1_math_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(semester1syllabus.this,sem1_math.class));
            }
        });

//Setting Banner Adview to this activity
        AdView banner_ad = findViewById(R.id.sem1_banner_id);
        allfunctions_obj.bannerAd(banner_ad);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}