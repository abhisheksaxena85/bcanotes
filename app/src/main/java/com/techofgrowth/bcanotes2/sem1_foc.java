package com.techofgrowth.bcanotes2;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.techofgrowth.bcanotes2.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdView;

public class sem1_foc extends AppCompatActivity {
    allFunctions allfunction_obj = new allFunctions();//OBJECT of all function class
    AdView banner_ad;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem1_foc);
//Removing the Status bar
        allfunction_obj.removeStatusBar(this);

//Opening Pdf of Fundamental of Computer
        PDFView pdfview = findViewById(R.id.sem1_foc_pdf);
        allfunction_obj.pdfView(pdfview,"fundamental_of_computer_sem_1.pdf");//using pdfviewer function from all function class

//Banner adview of this activity
        banner_ad = findViewById(R.id.sem1_foc_banner_id);
        allfunction_obj.bannerAd(banner_ad);
    }

}