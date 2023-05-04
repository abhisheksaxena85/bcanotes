package com.techofgrowth.bcanotes2;

import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.ads.AdView;
import android.os.Bundle;

public class sem2_math_secondQpaper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem2_math_second_qpaper);

        allFunctions allfunctions_obj = new allFunctions();
        AdView banner_ad;

        banner_ad = findViewById(R.id.sem2_secondmathPaper_banner_id);
        PDFView pdfView = findViewById(R.id.sem2_secondmathPaper_pdf);

        allfunctions_obj.pdfView(pdfView,"second_mathematics_sem2_2022.pdf");
        allfunctions_obj.removeStatusBar(this);

        allfunctions_obj.bannerAd(banner_ad);
    }
}