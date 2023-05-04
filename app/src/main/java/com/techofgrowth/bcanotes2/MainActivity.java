package com.techofgrowth.bcanotes2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.techofgrowth.bcanotes2.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;
CardView semester1card,semester2card,semester3card,semester4card,semester5card,semester6card;
InterstitialAd inter_ad;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_drawer);
        toolbar = findViewById(R.id.toolbar_main);
//toolbar for home acitvity
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BCA NOTES");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Changing the color of toolbar title
//Navigation Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer);//Creating the actionbar drawer toggle
        drawerLayout.addDrawerListener(toggle);//Adding drawer listener
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.toolbar_navigation_icon));//Changing the color of Hemberger of navigation
        toggle.syncState();//adding syncstate

      //Adding click funciton to navigation items
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.menu_home){// home
                    Intent Userdetails = getIntent();
                    String name = Userdetails.getStringExtra("name");
                    Intent intent_home = new Intent(getApplicationContext(),MainActivity.class);
                    intent_home.putExtra("name",name);
                    startActivity(intent_home);
                    finish();

                }else if(id==R.id.mjpru_syllabus){//syllabus pdf

                    Intent intent_syllabus = new Intent(MainActivity.this,syllabus_pdf.class);
                    startActivity(intent_syllabus);

                } else if(id==R.id.menu_share){ //Sharing

                    Intent intent_share = new Intent(Intent.ACTION_SEND);
                    intent_share.setType("text/plain");
                    intent_share.putExtra(Intent.EXTRA_SUBJECT,"Download This App Which Has Complete Notes of BCA");
                    intent_share.putExtra(Intent.EXTRA_TEXT,"Hi! Check out this awesome BCA Notes Application for BCA students. \nGet it from for free\n https://play.google.com/store/apps/details?id=com.techofgrowth.bcanotes2");
                    startActivity(Intent.createChooser(intent_share,"Share"));

                }else if(id==R.id.menu_rating){//Rating

                    Uri uri = Uri.parse("market://details?id=" + "com.techofgrowth.bcanotes2");//here will be the package name of live app
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.techofgrowth.bcanotes2")));
                                // here above the package name is mentioned in link of app
                    }

                }else if(id==R.id.menu_more_apps){  //more apps

                    Toast.makeText(MainActivity.this, "More Apps Coming Soon...", Toast.LENGTH_LONG).show();

                }else if(id==R.id.menu_contact){//contact

                    Intent email_intent = new Intent(Intent.ACTION_SEND);
                    email_intent.setType("message/rfc822");
                    email_intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"abhisheksaxena904411@gmail.com","abhisheksaxena852828@gamil.com"});
                    email_intent.putExtra(Intent.EXTRA_SUBJECT,"BCA Notes Application Related Query...");
                    email_intent.putExtra(Intent.EXTRA_TEXT,"Please! write here your message..");
                    startActivity(email_intent);

                }else{ //Logout user

                    SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("login_flag",false);
                    editor.apply();

//                  Toast.makeText(MainActivity.this, "Log out successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,loginUserActivity.class));
                    finishAffinity();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
//Navigation Drawer Completes Here!


//Syllabus CardViews Onclick here

        semester1card = findViewById(R.id.semester1card);
        semester2card = findViewById(R.id.semester2card);
        semester3card = findViewById(R.id.semester3card);
        semester4card = findViewById(R.id.semester4card);
        semester5card = findViewById(R.id.semester5card);
        semester6card = findViewById(R.id.semester6card);

        //first semesterCard click
        semester1card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,semester1syllabus.class));
            }
        });

        //second semesterCard click
        semester2card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,semester2syllabus.class));
            }
        });
        //thrid semesterCard click
        semester3card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,semester3syllabus.class));
            }
        });
        //fourth semesterCard click
        semester4card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,semester4syllabus.class));
            }
        });
        //fifth semesterCard click
        semester5card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,semester5syllabus.class));
            }
        });
        //sixth semesterCard click
        semester6card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,semester6syllabus.class));
            }
        });

//Banner Adview of this activity
        AdView banner_ad = findViewById(R.id.home_banner_id);
        MobileAds.initialize(this);
        AdRequest ad_request = new AdRequest.Builder().build();
        banner_ad.loadAd(ad_request);

//Adding Inertial ad in this activity
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
                    inter_ad.show(MainActivity.this);
            }
        },30000);



// Oher Section click action

        //University syllabus pdf intent
        LinearLayout syllabus_pdf_mjpru = findViewById(R.id.syllabus_pdf_redirect);
        syllabus_pdf_mjpru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,syllabus_pdf.class));
            }
        });

        //previous year question pepar intent passing
        LinearLayout previous_year_pepar = findViewById(R.id.previous_year_pepar);
        previous_year_pepar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Passing the intent of previous year question pepar
                Intent intent_qp = new Intent(MainActivity.this,previous_year_question.class);
                startActivity(intent_qp);
            }
        });

        // online compiler intent passing
        LinearLayout online_compiler = findViewById(R.id.online_compiler);
        online_compiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Passing the intent of previous year question pepar
                Intent intent_compiler = new Intent(MainActivity.this,online_compiler.class);
                startActivity(intent_compiler);
            }
        });

//      //Getting data from firebase realtime database
        Intent Userdetails = getIntent();

        //Getting user information from Splash activity
        String name = Userdetails.getStringExtra("name");
        String usernameId = Userdetails.getStringExtra("usernameid");
        String emailid = Userdetails.getStringExtra("email");
        String phoneNumber = Userdetails.getStringExtra("phoneNumber");
        String password = Userdetails.getStringExtra("password");

        //Giving name to main activity
        TextView usernametext = findViewById(R.id.username_firebase);
        usernametext.setText(name);

        ImageView userprofileImage = findViewById(R.id.user_profile);
        userprofileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(MainActivity.this,userProfile.class);

                profileIntent.putExtra("name",name);
                profileIntent.putExtra("usernameid",usernameId);
                profileIntent.putExtra("email",emailid);
                profileIntent.putExtra("phoneNumber",phoneNumber);
                profileIntent.putExtra("password",password);
                startActivity(profileIntent);
            }
        });










    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            AdRequest ad_request = new AdRequest.Builder().build();

//Adding Inertial ad in this activity
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
                            finishAffinity();
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


//Alert Dialog Box on exiting the application
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            alertDialog.setTitle("Are you sure want to exit?");
            alertDialog.setIcon(R.drawable.ic_baseline_exit_to_app_24);

            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (inter_ad != null)
                        inter_ad.show(MainActivity.this);
                    else
                        MainActivity.super.onBackPressed();
                }
            });
            alertDialog.show();
        }
    }

}