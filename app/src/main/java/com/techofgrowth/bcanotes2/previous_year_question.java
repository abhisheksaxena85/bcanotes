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
import android.widget.Button;
import android.widget.Toast;

import com.techofgrowth.bcanotes2.R;
import com.google.android.gms.ads.AdView;

public class previous_year_question extends AppCompatActivity {
allFunctions allfunctions_obj = new allFunctions();
Toolbar toolbar;
CardView sem1_first,sem1_second,sem1_third,sem1_fourth,sem1_fifth;
CardView sem2_depaper, sem2_dismathpaper,sem2_secondMathpaper,sem2_cpropaper,sem2_manageecopaper;
CardView sem3_first,sem3_second,sem3_third,sem3_fourth,sem3_fifth;
CardView sem4_first,sem4_second,sem4_third,sem4_fourth,sem4_fifth;
CardView sem5_first,sem5_second,sem5_third,sem5_fourth,sem5_fifth;
CardView sem6_first,sem6_second,sem6_third,sem6_fourth;
Button previous_year_paper_appbtn;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_year_question);

//Adding toolbar
        toolbar = findViewById(R.id.prev_paper_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Previous Year Papers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Setting the toolbar title color
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.toolbarTitleColor));//Setting color of back button of toolbar of activity



        //First Semester objects
        sem1_first = findViewById(R.id.sem1_pcs_paper);
        sem1_second = findViewById(R.id.sem1_foc_paper);
        sem1_third = findViewById(R.id.sem1_fom_paper);
        sem1_fourth = findViewById(R.id.sem1_math_paper);
        sem1_fifth = findViewById(R.id.sem1_lc_paper);

        sem2_depaper = findViewById(R.id.sem2_de_paper);
        sem2_dismathpaper = findViewById(R.id.sem2_dismath_paper);
        sem2_secondMathpaper = findViewById(R.id.sem2_math_second_paper);
        sem2_cpropaper = findViewById(R.id.sem2_cPro_paper);
        sem2_manageecopaper = findViewById(R.id.sem2_manage_eco_paper);

        sem3_first = findViewById(R.id.sem3_first_paper);
        sem3_second = findViewById(R.id.sem3_second_paper);
        sem3_third = findViewById(R.id.sem3_third_paper);
        sem3_fourth = findViewById(R.id.sem3_fourth_paper);
        sem3_fifth = findViewById(R.id.sem3_fifth_paper);

        sem4_first = findViewById(R.id.sem4_first_paper);
        sem4_second = findViewById(R.id.sem4_second_paper);
        sem4_third = findViewById(R.id.sem4_third_paper);
        sem4_fourth = findViewById(R.id.sem4_fourth_paper);
        sem4_fifth = findViewById(R.id.sem4_fifth_paper);

        sem5_first = findViewById(R.id.sem5_first_paper);
        sem5_second = findViewById(R.id.sem5_second_paper);
        sem5_third = findViewById(R.id.sem5_third_paper);
        sem5_fourth = findViewById(R.id.sem5_fourth_paper);
        sem5_fifth = findViewById(R.id.sem5_fifth_paper);

        sem6_first = findViewById(R.id.sem6_first_paper);
        sem6_second = findViewById(R.id.sem6_second_paper);
        sem6_third = findViewById(R.id.sem6_third_paper);
        sem6_fourth = findViewById(R.id.sem6_fourth_paper);

//Passing Intent to paper's pdfs

        sem1_first.setOnClickListener(v ->startActivity(new Intent(previous_year_question.this,sem1_first_pcs_paper.class)));
        sem1_second.setOnClickListener(v -> startActivity(new Intent(previous_year_question.this,sem1_second_foc_paper.class)));
        sem1_third.setOnClickListener(v -> startActivity(new Intent(previous_year_question.this,sem1_third_fom_paper.class)));
        sem1_fourth.setOnClickListener(v -> startActivity(new Intent(previous_year_question.this,sem1_fourth_math_paper.class)));
        sem1_fifth.setOnClickListener(v -> startActivity(new Intent(previous_year_question.this,sem1_fifth_lc_paper.class)));

        sem2_depaper.setOnClickListener(v -> startActivity(new Intent(previous_year_question.this, sem2_deQpaper.class)));
        sem2_dismathpaper.setOnClickListener(v->startActivity(new Intent(previous_year_question.this,sem2_dismathQpaper.class)));
        sem2_secondMathpaper.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem2_math_secondQpaper.class)));
        sem2_cpropaper.setOnClickListener(v -> startActivity(new Intent(previous_year_question.this,sem2_cproQpaper.class)));
        sem2_manageecopaper.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem2_manageecoQpaper.class)));

        sem3_first.setOnClickListener(v -> startActivity(new Intent(previous_year_question.this,sem3_first_cona_paper.class)));
        sem3_second.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem3_second_comporg_paper.class)));
        sem3_third.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem3_third_datastrct_paper.class)));
        sem3_fourth.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem3_fourth_oopl_paper.class)));
        sem3_fifth.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem3_fifth_orgbehav_paper.class)));

        sem4_first.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem4_first_os_paper.class)));
        sem4_second.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem4_second_dbmssql_paper.class)));
        sem4_third.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem4_third_manageinfo_paper.class)));
        sem4_fourth.setOnClickListener(v-> {
//            startActivity(new Intent(previous_year_question.this,sem4_fourth_visualbasic_paper.class));
            Toast.makeText(this, "Sorry! This question paper is not available", Toast.LENGTH_SHORT).show();
        });
        sem4_fifth.setOnClickListener(v-> {
//            startActivity(new Intent(previous_year_question.this, sem4_fifth_systemanalysis_paper.class));
            Toast.makeText(this, "Sorry! This question paper is not available", Toast.LENGTH_SHORT).show();
        });

        sem5_first.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem5_first_cgaa_paper.class)));
        sem5_second.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this, sem5_second_cn_paper.class)));
        sem5_third.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem5_third_itip_paper.class)));
        sem5_fourth.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this, sem5_fourth_softengin_paper.class)));
        sem5_fifth.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this, sem5_fifth_aca_paper.class)));

        sem6_first.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem6_first_mcaa_paper.class)));
        sem6_second.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem6_second_ai_paper.class)));
        sem6_third.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem6_third_webtech_paper.class)));
        sem6_fourth.setOnClickListener(v-> startActivity(new Intent(previous_year_question.this,sem6_fourth_net_paper.class)));




//Setting banner adview here
        AdView banner_ad = findViewById(R.id.previous_year_pepar_banner_ad);
        allfunctions_obj.bannerAd(banner_ad);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}