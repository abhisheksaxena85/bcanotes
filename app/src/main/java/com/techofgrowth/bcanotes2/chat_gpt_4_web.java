package com.techofgrowth.bcanotes2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class chat_gpt_4_web extends AppCompatActivity {
    InterstitialAd inter_ad;
    allFunctions allfunctions_obj = new allFunctions();
    @SuppressLint({"MissingInflatedId", "MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_gpt4_web);


        //Check -> Internet State
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//checking wifi connection
        android.net.NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//checking Internet connection


        //Checking If Internet is ON
        if((wifi != null&datac != null) && (wifi.isConnected() | datac.isConnected())){ // on successful connection

            //Loading Dialog Box
            Dialog dialog = new Dialog(chat_gpt_4_web.this);
            dialog.setContentView(R.layout.loding_dialog);
            dialog.setCancelable(false); // Set whether the dialog can be canceled by pressing the back button


            //WebView for website
            WebView webView = findViewById(R.id.chat_gpt_webView);

            //Showing Loading Dialog on activity start
            dialog.show();
            webView.setWebViewClient(new WebViewClient(){

                //This method works to work with request and recieve
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    return super.shouldOverrideUrlLoading(view, request);
                }

                //When a page starts loding
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);

                }
                @Override
                public void onPageFinished(WebView view, String url) {

                    //Removing the dialog box after loading the page
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    },2000);
                    super.onPageFinished(view, url);
                }
            });
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.loadUrl("https://chat.openai.com/");//Loading website


        }else{
            //ON Internet Off
            ImageView error_image = findViewById(R.id.error_image_chatgpt);

            //Putting image on internet connection error
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.error_service_unavailable_v1,null);
            error_image.setImageDrawable(drawable);
            Toast.makeText(this, "Internet Connection Failed", Toast.LENGTH_SHORT).show();

            //Putting error text
            TextView error_text = findViewById(R.id.error_text_chatgpt);
            error_text.setText("Internet Connection Failed!");
        }

        // Setting banner adview here
        AdView banner_ad = findViewById(R.id.chatgpt_banner_ad);
        MobileAds.initialize(this);
        AdRequest ad_request = new AdRequest.Builder().build();
        banner_ad.loadAd(ad_request);


        //Intertitial AD here
        InterstitialAd.load(this, getString(R.string.inertial_ad_unit_id), ad_request, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                inter_ad = interstitialAd;

                inter_ad.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                        inter_ad = null;
                    }
                });
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(inter_ad!=null)
                    inter_ad.show(chat_gpt_4_web.this);
            }
        },2500);
    }
}