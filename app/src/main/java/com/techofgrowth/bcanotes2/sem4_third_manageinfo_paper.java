package com.techofgrowth.bcanotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdView;

public class sem4_third_manageinfo_paper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem4_third_manageinfo_paper);


        allFunctions allfunctions_obj = new allFunctions();
        AdView banner_ad;

        banner_ad = findViewById(R.id.sem4_manageinfo_Paper_banner_id);
        PDFView pdfView = findViewById(R.id.sem4_manageinfo_Paper_pdf);

        allfunctions_obj.pdfView(pdfView, "sem4_mangeinfo_paper_2019.pdf");
        allfunctions_obj.removeStatusBar(this);

        allfunctions_obj.bannerAd(banner_ad);
    }
}