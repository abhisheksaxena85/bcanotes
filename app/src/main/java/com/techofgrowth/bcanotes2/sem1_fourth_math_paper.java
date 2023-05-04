package com.techofgrowth.bcanotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.techofgrowth.bcanotes2.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdView;

public class sem1_fourth_math_paper extends AppCompatActivity {
    allFunctions allfunctions_obj = new allFunctions();
    AdView banner_ad;
    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem1_fourth_fom_paper);


        banner_ad = findViewById(R.id.sem1_fourthPaper_banner_id);
        PDFView pdfView = findViewById(R.id.sem1_fourthPaper_pdf);


        allfunctions_obj.pdfView(pdfView,"math 2022.pdf");
        allfunctions_obj.removeStatusBar(this);

        allfunctions_obj.bannerAd(banner_ad);
    }
}