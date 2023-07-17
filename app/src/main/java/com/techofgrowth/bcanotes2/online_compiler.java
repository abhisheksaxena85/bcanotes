package com.techofgrowth.bcanotes2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
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

import com.techofgrowth.bcanotes2.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class online_compiler extends AppCompatActivity {
    InterstitialAd inter_ad;
    WebView webView;
    allFunctions allfunctions_obj = new allFunctions();
    @SuppressLint({"MissingInflatedId", "MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_compiler);


        //Checking Internet Connection of device
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//checking wifi connection
        android.net.NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//checking Internet connection


        // on successful connection
        if((wifi != null&datac != null) && (wifi.isConnected() | datac.isConnected())){

            //WebView
            webView = findViewById(R.id.online_compiler);

            //Loading Alert Dialog box
            Dialog dialog = new Dialog(online_compiler.this);
            dialog.setContentView(R.layout.loding_dialog);
            dialog.setCancelable(false);
            dialog.show();

            webView.setWebViewClient(new WebViewClient(){

                //This Method works with request and recieve
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {//adding webresources request for website
                    return super.shouldOverrideUrlLoading(view, request);
                }

                //On page started loading
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                }

                //When pages finish loading
                @Override
                public void onPageFinished(WebView view, String url) {

                    //Removing dialog box on Page loaded
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    },3000);
                    super.onPageFinished(view, url);
                }
            });
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.loadUrl("https://rextester.com/l/c_online_compiler_gcc");//Loading website

        }else{
            //On failed connection
            ImageView error_image = findViewById(R.id.error_image);

            //Putting error image
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.error_service_unavailable_v1,null);
            error_image.setImageDrawable(drawable);

            //Putting error text
            Toast.makeText(this, "Internet Connnection Failed !!", Toast.LENGTH_LONG).show();
            TextView error_text = findViewById(R.id.error_text);
            error_text.setText("Check Your Internet Connection !");
        }
        // Setting banner adview here
        AdView banner_ad = findViewById(R.id.online_compiler_banner_ad);
        MobileAds.initialize(this);
        AdRequest ad_request = new AdRequest.Builder().build();
        banner_ad.loadAd(ad_request);


        //Intertitial Ad
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
                    inter_ad.show(online_compiler.this);
            }
        },8000);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}