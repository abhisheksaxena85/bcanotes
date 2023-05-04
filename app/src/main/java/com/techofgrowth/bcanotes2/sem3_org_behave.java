package com.techofgrowth.bcanotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdView;

public class sem3_org_behave extends AppCompatActivity {
allFunctions allfunctions_obj = new allFunctions();
AdView banner_ad;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem3_org_behave);

        //Removing the Status bar
        allfunctions_obj.removeStatusBar(this);

        //Opening the pdf
        PDFView pdfView = findViewById(R.id.org_behave_pdf);
        allfunctions_obj.pdfView(pdfView,"organizational_behaviour_sem_3.pdf");

        //Bnner adview of this activity
        banner_ad = findViewById(R.id.org_behave_banner_id);
        allfunctions_obj.bannerAd(banner_ad);
    }
}