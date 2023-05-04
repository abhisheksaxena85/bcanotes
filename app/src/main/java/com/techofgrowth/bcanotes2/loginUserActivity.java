package com.techofgrowth.bcanotes2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.techofgrowth.bcanotes2.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
public class loginUserActivity extends AppCompatActivity {
    TextInputLayout username_input_layout,password_input_layout;
    Button login_btn;
    TextView forgetPassword_activity,registerUser_activity;
    ProgressBar loginprogressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        loginprogressbar = findViewById(R.id.loginProgressBar);

        login_btn = findViewById(R.id.loginUser_btn);

        username_input_layout = findViewById(R.id.username_text_input_layout);
        password_input_layout = findViewById(R.id.userpassword_text_input_layout);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Checking Internet Connection of device
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//checking wifi connection
                android.net.NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//checking Internet connection

                if((wifi != null&datac != null) && (wifi.isConnected() | datac.isConnected())){ // on successful connection
                    String username_input_data = username_input_layout.getEditText().getText().toString();
                    String password_input_data = password_input_layout.getEditText().getText().toString();

                    loginprogressbar.setVisibility(View.VISIBLE);
                    if(!username_input_data.isEmpty()){ //Checking valid email
                        username_input_layout.setError(null);
                        username_input_layout.setErrorEnabled(false);
                        if(!password_input_data.isEmpty()){         //Checking password
                            password_input_layout.setError(null);
                            password_input_layout.setErrorEnabled(false);

                            //Main login part after data input
                            final String username_entered = username_input_layout.getEditText().getText().toString();
                            final String user_password_entered = password_input_layout.getEditText().getText().toString();

                            //Getting instence from firebase
                            final FirebaseDatabase firebasedatabase = FirebaseDatabase.getInstance();
                            DatabaseReference databasereference = firebasedatabase.getReference("datauser");

                            //Checking user in db
                            Query checkusername_database = databasereference.orderByChild("username").equalTo(username_entered);
                            checkusername_database.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        username_input_layout.setError(null);
                                        username_input_layout.setErrorEnabled(false);

                                        //Getting User information
                                        String user_password = snapshot.child(username_entered).child("password").getValue(String.class);
                                        String user_email = snapshot.child(username_entered).child("emailId").getValue(String.class);
                                        String user_phoneNumber = snapshot.child(username_entered).child("phoneNumber").getValue(String.class);
                                        String usernameId = snapshot.child(username_entered).child("username").getValue(String.class);

                                        if(user_password.equals(user_password_entered)){
                                            password_input_layout.setError(null);
                                            password_input_layout.setErrorEnabled(true);

                                            String name = snapshot.child(username_entered).child("name").getValue(String.class);

                                            Intent login_intent = new Intent(loginUserActivity.this,MainActivity.class);

                                            //passing userinformation to mainactivity
                                            login_intent.putExtra("name",name);
                                            login_intent.putExtra("email",user_email);
                                            login_intent.putExtra("usernameid",usernameId);
                                            login_intent.putExtra("phoneNumber",user_phoneNumber);
                                            login_intent.putExtra("password",user_password);

                                            SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = preferences.edit();
                                            editor.putBoolean("login_flag", true);

                                            //Storing user information in shared preference
                                            editor.putString("name",name);
                                            editor.putString("usernameid",usernameId);
                                            editor.putString("email",user_email);
                                            editor.putString("phoneNumber",user_phoneNumber);
                                            editor.putString("password",user_password);
                                            editor.apply();

                                            loginprogressbar.setVisibility(View.GONE);
                                            Toast.makeText(loginUserActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(login_intent);
                                            finish();

                                        }else{
                                            password_input_layout.setError("Wrong password");
                                            loginprogressbar.setVisibility(View.GONE);
                                        }
                                    }else {
                                        username_input_layout.setError("user does not exists ");
                                        loginprogressbar.setVisibility(View.GONE);
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }else{
                            password_input_layout.setError("please enter password");
                            loginprogressbar.setVisibility(View.GONE);
                        }
                    }else {
                        username_input_layout.setError("Enter valid username ");
                        loginprogressbar.setVisibility(View.GONE);
                    }
                }else{
                    Toast.makeText(loginUserActivity.this, "Failed to connect to internet!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        forgetPassword_activity = findViewById(R.id.forgetPasswordClick);
        registerUser_activity = findViewById(R.id.register_new_user_onclick);

        //passing intent to forgetpassword activity
        forgetPassword_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginUserActivity.this,forgetPasswordActivity.class));
            }
        });

        //passing intent to register activity
        registerUser_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginUserActivity.this,RegisterUserActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {

        //Alert Dialog Box on exiting the application
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle("Are you sure want to exit?");
        alertDialog.setIcon(R.drawable.ic_baseline_exit_to_app_24);

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    loginUserActivity.super.onBackPressed();
            }
        });
        alertDialog.show();
    }
}