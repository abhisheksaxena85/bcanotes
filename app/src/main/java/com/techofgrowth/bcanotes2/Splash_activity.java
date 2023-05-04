package com.techofgrowth.bcanotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class Splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView second_logo = findViewById(R.id.second_logo);

        Animation logo_anim = AnimationUtils.loadAnimation(this,R.anim.second_logo_animation);
        second_logo.setAnimation(logo_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("login",MODE_PRIVATE);
                boolean value_check = preferences.getBoolean("login_flag",false);
                String userName = preferences.getString("name"," ");
                String userNameid =preferences.getString("usernameid","");
                String emailid = preferences.getString("email"," ");
                String phoneNumber = preferences.getString("phoneNumber","");
                String password = preferences.getString("password","");


                Intent home_or_login_intent;

                if(value_check){
                    home_or_login_intent = new Intent(Splash_activity.this,MainActivity.class);
                    home_or_login_intent.putExtra("name",userName);
                    home_or_login_intent.putExtra("email",emailid);
                    home_or_login_intent.putExtra("usernameid",userNameid);
                    home_or_login_intent.putExtra("phoneNumber",phoneNumber);
                    home_or_login_intent.putExtra("password",password);

                }else{
                    home_or_login_intent = new Intent(Splash_activity.this,loginUserActivity.class);
                }

                startActivity(home_or_login_intent);
                finish();
            }
        },3000);
    }
}