package com.techofgrowth.bcanotes2;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdView;

public class sem3_comp_org extends AppCompatActivity {
allFunctions allfunctions_obj = new allFunctions();
AdView banner_ad;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem3_org);

        //Removing the Status bar
        allfunctions_obj.removeStatusBar(this);

        //Opening the pdf
        PDFView pdfView = findViewById(R.id.computer_org_pdf);
        allfunctions_obj.pdfView(pdfView,"computer_organization_sem_3.pdf");

        //Bnner adview of this activity
        banner_ad = findViewById(R.id.computer_org_banner_id);
        allfunctions_obj.bannerAd(banner_ad);

    }
}