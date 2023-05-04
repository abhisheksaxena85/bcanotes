package com.techofgrowth.bcanotes2;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class allFunctions {

    //Function for opening pdf
    public void pdfView(@NonNull PDFView pdfview, String file_name){
        pdfview.fromAsset(file_name)
                .defaultPage(0)
                .enableAnnotationRendering(true)
                .spacing(2)
                .enableDoubletap(true)
                .onRender(new OnRenderListener() {
                    @Override
                    public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                        pdfview.fitToWidth();
                    }
                })
                .scrollHandle(new DefaultScrollHandle(pdfview.getContext()))
                .load();
    }
    //Function for banner ad view
    public void bannerAd(AdView bannerad_obj){
        MobileAds.initialize(bannerad_obj.getContext());
        AdRequest banner_ad_request = new AdRequest.Builder().build();
        bannerad_obj.loadAd(banner_ad_request);
    }

    //Function for removing the status bar of the pdf activity
    public void removeStatusBar(Activity myActivity){
        myActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
