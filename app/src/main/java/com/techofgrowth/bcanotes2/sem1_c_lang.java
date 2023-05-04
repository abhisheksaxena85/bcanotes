package com.techofgrowth.bcanotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.techofgrowth.bcanotes2.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdView;

public class sem1_c_lang extends AppCompatActivity {
    allFunctions allfunctions_obj = new allFunctions();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem1_clang);

        //Removing the Status bar
        allfunctions_obj.removeStatusBar(this);

        //Opening the pdf
        PDFView pdfView_sem1_c_lang = findViewById(R.id.sem1_c_lang_pdf);
        allfunctions_obj.pdfView(pdfView_sem1_c_lang,"c_lang_sem_1.pdf");

        //Bnner adview of this activity
        AdView banner_ad = findViewById(R.id.sem1_c_lang_banner_id);
        allfunctions_obj.bannerAd(banner_ad);

    }
}