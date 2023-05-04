package com.techofgrowth.bcanotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdView;

public class sem6_web_technology extends AppCompatActivity {
allFunctions allfunctions_obj = new allFunctions();
AdView banner_ad;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem6_web_technology);

        //Removing the Status bar
        allfunctions_obj.removeStatusBar(this);

        //Opening the pdf
        PDFView pdfView = findViewById(R.id.web_tech_pdf);
        allfunctions_obj.pdfView(pdfView,"web_technology_sem_6.pdf");

        //Bnner adview of this activity
        banner_ad = findViewById(R.id.web_tech_banner_id);
        allfunctions_obj.bannerAd(banner_ad);
    }
}