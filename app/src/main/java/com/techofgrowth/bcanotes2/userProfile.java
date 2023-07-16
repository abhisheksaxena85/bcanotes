package com.techofgrowth.bcanotes2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class userProfile extends AppCompatActivity {
Toolbar toolbar;
TextInputLayout name_input_text_layout,username_input_text_layout,email_input_text_layout,phone_number_input_text_layout,password_input_text_layout;
EditText nameProfile,usernameProfile,emailProfile,phonenumberProfile,passwordProfile;
InterstitialAd inter_ad;
allFunctions allfunctions_obj = new allFunctions();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        AdView banner_ad = findViewById(R.id.userProfile_banner_id);
        allfunctions_obj.bannerAd(banner_ad);

        //Adding Inertial ad in this activity
        AdRequest ad_request = new AdRequest.Builder().build();
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
                    inter_ad.show(userProfile.this);
            }
        },4000);









        FirebaseApp.initializeApp(this);

        ProgressBar profileprogressBar = findViewById(R.id.profileProgressBar);

        //Connecting
        name_input_text_layout = findViewById(R.id.nameprofile_text_input_layout);
        username_input_text_layout = findViewById(R.id.usernameprofile_text_input_layout);
        email_input_text_layout = findViewById(R.id.emailprofile_text_input_layout);
        phone_number_input_text_layout = findViewById(R.id.phoneNprofile_text_input_layout);
        password_input_text_layout = findViewById(R.id.passwordprofile_text_input_layout);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");//Toolbar Title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Setting the toolbar title color
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.toolbarTitleColor));//Setting color of back button of toolbar of activity

        //Connecting to XML
        nameProfile = findViewById(R.id.nameprofile_edit_text);
        usernameProfile = findViewById(R.id.usernameprofile_edit_text);
        emailProfile = findViewById(R.id.emailprofile_edit_text);
        phonenumberProfile = findViewById(R.id.phoneNprofile_edit_text);
        passwordProfile = findViewById(R.id.passwordprofile_edit_text);

        //Getting user details from MainActivity
        Intent userDetails = getIntent();

        String name = userDetails.getStringExtra("name");
        String usernameId = userDetails.getStringExtra("usernameid");
        String emailid = userDetails.getStringExtra("email");
        String phoneNumber = userDetails.getStringExtra("phoneNumber");
        String password = userDetails.getStringExtra("password");

        //Setting user data to edit Text
        nameProfile.setText(name);
        usernameProfile.setText(usernameId);
        emailProfile.setText(emailid);
        phonenumberProfile.setText(phoneNumber);
        passwordProfile.setText(password);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);


        Button updateinfoBtn = findViewById(R.id.updateInforbtn);
        updateinfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//checking wifi connection
                android.net.NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//checking Internet connection
                if((wifi != null&datac != null) && (wifi.isConnected() | datac.isConnected())) { // on successful connection

                    //converting into string of user data
                    String name_input_data = name_input_text_layout.getEditText().getText().toString();
                    String username_input_data = username_input_text_layout.getEditText().getText().toString();
                    String email_input_data = email_input_text_layout.getEditText().getText().toString();
                    String phone_num_input_data = phone_number_input_text_layout.getEditText().getText().toString();
                    String password_input_data = password_input_text_layout.getEditText().getText().toString();

                    if (!name_input_data.isEmpty()) {      //Name box
                        name_input_text_layout.setError(null);
                        name_input_text_layout.setErrorEnabled(false);
                        if (!username_input_data.isEmpty()) {
                            username_input_text_layout.setError(null);
                            username_input_text_layout.setErrorEnabled(false);
                            if (!email_input_data.isEmpty() && email_input_data.matches("(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})")) {        //Email box
                                email_input_text_layout.setError(null);
                                email_input_text_layout.setErrorEnabled(false);
                                if (!phone_num_input_data.isEmpty() && phone_num_input_data.matches("(0|91)?[6-9]\\d{9}")) {        //Phone number box
                                    phone_number_input_text_layout.setError(null);
                                    phone_number_input_text_layout.setErrorEnabled(false);
                                    if (!password_input_data.isEmpty()) {         //Password box
                                        password_input_text_layout.setError(null);
                                        password_input_text_layout.setErrorEnabled(false);


                                        profileprogressBar.setVisibility(View.VISIBLE);

                                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                                        DatabaseReference databasereference = database.getReference("datauser");

                                        Query checkuseremail_database = databasereference.orderByChild("emailId").equalTo(email_input_data);

                                        checkuseremail_database.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){

                                                    if(emailid.equals(checkuseremail_database)){
                                                        email_input_text_layout.setError("This email id already registered with another account");
                                                        profileprogressBar.setVisibility(View.GONE);
                                                    }else{
                                                        email_input_text_layout.setError(null);
                                                        email_input_text_layout.setErrorEnabled(true);

                                                        alertDialog.setTitle("This will Update Your Previous Data");
                                                        alertDialog.setIcon(R.drawable.baseline_update_24);



                                                        alertDialog.setNegativeButton("NO, Stop it!", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                profileprogressBar.setVisibility(View.GONE);
                                                            }
                                                        });
                                                        alertDialog.setPositiveButton("Ok, I Know", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {


                                                                DatabaseReference databasereference = database.getReference("datauser").child(username_input_data);

                                                                databasereference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                        if(snapshot.exists()){

                                                                            String namep = nameProfile.getText().toString();
                                                                            String usernameP = usernameProfile.getText().toString();
                                                                            String emailP = emailProfile.getText().toString();
                                                                            String phoneNumP = phonenumberProfile.getText().toString();
                                                                            String passwordP = passwordProfile.getText().toString();

                                                                            Map<String, Object> updates = new HashMap<>();
                                                                            updates.put("name", namep);
                                                                            updates.put("phoneNumber", phoneNumP);
                                                                            updates.put("username", usernameP);
                                                                            updates.put("emailId", emailP);
                                                                            updates.put("password", passwordP);
                                                                            databasereference.updateChildren(updates);

                                                                            Toast.makeText(userProfile.this, "Information Updated", Toast.LENGTH_SHORT).show();
                                                                            profileprogressBar.setVisibility(View.GONE);

                                                                            SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                                                                            SharedPreferences.Editor editor = preferences.edit();
                                                                            editor.putBoolean("login_flag", false);

                                                                            editor.putString("name","");
                                                                            editor.putString("usernameid","");
                                                                            editor.putString("email","");
                                                                            editor.putString("phoneNumber","");
                                                                            editor.putString("password","");
                                                                            editor.apply();
                                                                            profileprogressBar.setVisibility(View.GONE);

                                                                            Intent loginProfile_intent = new Intent(userProfile.this,loginUserActivity.class);
                                                                            startActivity(loginProfile_intent);
                                                                            finishAffinity();
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                                    }
                                                                });
                                                            }
                                                        });
                                                        alertDialog.setCancelable(false);
                                                        alertDialog.show();
                                                    }

                                                }else{
                                                    email_input_text_layout.setError(null);
                                                    email_input_text_layout.setErrorEnabled(true);

                                                    alertDialog.setTitle("This will Update Your Previous Data");
                                                    alertDialog.setIcon(R.drawable.baseline_update_24);

                                                    alertDialog.setNegativeButton("NO, Stop it!", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            profileprogressBar.setVisibility(View.GONE);
                                                        }
                                                    });
                                                    alertDialog.setPositiveButton("Ok, I Know", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                            DatabaseReference databasereference = database.getReference("datauser").child(username_input_data);

                                                            databasereference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    if(snapshot.exists()){

                                                                        String namep = nameProfile.getText().toString();
                                                                        String usernameP = usernameProfile.getText().toString();
                                                                        String emailP = emailProfile.getText().toString();
                                                                        String phoneNumP = phonenumberProfile.getText().toString();
                                                                        String passwordP = passwordProfile.getText().toString();

                                                                        Map<String, Object> updates = new HashMap<>();
                                                                        updates.put("name", namep);
                                                                        updates.put("phoneNumber", phoneNumP);
                                                                        updates.put("username", usernameP);
                                                                        updates.put("emailId", emailP);
                                                                        updates.put("password", passwordP);
                                                                        databasereference.updateChildren(updates);

                                                                        Toast.makeText(userProfile.this, "Information Updated", Toast.LENGTH_SHORT).show();
                                                                        profileprogressBar.setVisibility(View.GONE);

                                                                        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                                                                        SharedPreferences.Editor editor = preferences.edit();
                                                                        editor.putBoolean("login_flag", false);

                                                                        editor.putString("name","");
                                                                        editor.putString("usernameid","");
                                                                        editor.putString("email","");
                                                                        editor.putString("phoneNumber","");
                                                                        editor.putString("password","");
                                                                        editor.apply();
                                                                        profileprogressBar.setVisibility(View.GONE);

                                                                        Intent loginProfile_intent = new Intent(userProfile.this,loginUserActivity.class);
                                                                        startActivity(loginProfile_intent);
                                                                        finishAffinity();
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });
                                                        }
                                                    });
                                                    alertDialog.setCancelable(false);
                                                    alertDialog.show();
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                    } else {
                                        password_input_text_layout.setError("Create your password");
                                        profileprogressBar.setVisibility(View.GONE);
                                    }
                                } else {
                                    phone_number_input_text_layout.setError("Enter valid phone number");
                                    profileprogressBar.setVisibility(View.GONE);
                                }
                            } else {
                                email_input_text_layout.setError("Enter valid email");
                                profileprogressBar.setVisibility(View.GONE);
                            }
                        }else{
                            username_input_text_layout.setError("Enter username");
                            profileprogressBar.setVisibility(View.GONE);
                        }
                    }else{
                        name_input_text_layout.setError("Enter your name");
                        profileprogressBar.setVisibility(View.GONE);
                    }
                }else{
                    Toast.makeText(userProfile.this, "Failed to connect to internet!", Toast.LENGTH_SHORT).show();
                    profileprogressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}