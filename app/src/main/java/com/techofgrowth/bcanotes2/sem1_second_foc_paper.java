package com.techofgrowth.bcanotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.techofgrowth.bcanotes2.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdView;

public class sem1_second_foc_paper extends AppCompatActivity {
    allFunctions allfunctions_obj = new allFunctions();
    AdView banner_ad;
    PDFView pdfView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem1_second_foc_paper);

        banner_ad = findViewById(R.id.sem1_secondPaper_banner_id);
        allfunctions_obj.bannerAd(banner_ad);

        pdfView = findViewById(R.id.sem1_secondPaper_pdf);
        allfunctions_obj.pdfView(pdfView,"computer fundamental sem 1 2022.pdf");

        allfunctions_obj.removeStatusBar(this);

    }
}