package com.techofgrowth.bcanotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdView;

public class sem2_dismathQpaper extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem2_dismath_qpaper);

        allFunctions allfunctions_obj = new allFunctions();
        AdView banner_ad;

        banner_ad = findViewById(R.id.sem2_dismathPaper_banner_id);
        PDFView pdfView = findViewById(R.id.sem2_dismathPaper_pdf);

        allfunctions_obj.pdfView(pdfView,"discrete_mathematics_sem2_paper.pdf");
        allfunctions_obj.removeStatusBar(this);

        allfunctions_obj.bannerAd(banner_ad);
    }
}