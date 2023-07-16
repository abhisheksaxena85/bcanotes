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


        //Login ProgressBar
        loginprogressbar = findViewById(R.id.loginProgressBar);


        //Login Submit Button
        login_btn = findViewById(R.id.loginUser_btn);


        //Input Layouts of Login
        username_input_layout = findViewById(R.id.username_text_input_layout);
        password_input_layout = findViewById(R.id.userpassword_text_input_layout);


        //On Submit Button Click
        login_btn.setOnClickListener(v -> {


            //Checking Internet Connection of device
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//checking wifi connection
            android.net.NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//checking Internet connection


            //When Internet Connection is ON
            if ((wifi != null & datac != null) && (wifi.isConnected() | datac.isConnected())) {


                //Converting input data into String
                String username_input_data = username_input_layout.getEditText().getText().toString();
                String password_input_data = password_input_layout.getEditText().getText().toString();


                //Setting Visibility of ProgressBar
                loginprogressbar.setVisibility(View.VISIBLE);


                //Checking Empty Boxes
                if (!username_input_data.isEmpty()) {
                    //Checking Empty Username Box
                    username_input_layout.setError(null);
                    username_input_layout.setErrorEnabled(false);


                    if (!password_input_data.isEmpty()) {
                        //Checking Empty Password Box
                        password_input_layout.setError(null);
                        password_input_layout.setErrorEnabled(false);


                        //Making Entered data final
                        final String username_entered = username_input_layout.getEditText().getText().toString();
                        final String user_password_entered = password_input_layout.getEditText().getText().toString();


                        //Getting Instence and Reference of "datauser" from firebase
                        final FirebaseDatabase firebasedatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databasereference = firebasedatabase.getReference("datauser");


                        //Checking username exists in firebase
                        Query checkusername_database = databasereference.orderByChild("username").equalTo(username_entered);
                        checkusername_database.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                //When username Exists in Firebase
                                if (snapshot.exists()) {
                                    //Removing error if exists
                                    username_input_layout.setError(null);
                                    username_input_layout.setErrorEnabled(false);


                                    //Getting User Details from Firebase using snapshot with firebase reference
                                    String user_password = snapshot.child(username_entered).child("password").getValue(String.class);
                                    String user_email = snapshot.child(username_entered).child("emailId").getValue(String.class);
                                    String user_phoneNumber = snapshot.child(username_entered).child("phoneNumber").getValue(String.class);
                                    String usernameId = snapshot.child(username_entered).child("username").getValue(String.class);


                                    //Checking if password matches to firebase's password
                                    if (user_password.equals(user_password_entered)) {
                                        //Removing error if exists
                                        password_input_layout.setError(null);
                                        password_input_layout.setErrorEnabled(true);


                                        //Getting User's name value from FIrebase
                                        String name = snapshot.child(username_entered).child("name").getValue(String.class);


                                        //Redirecting to Home Screen
                                        Intent login_intent = new Intent(loginUserActivity.this, MainActivity.class);


                                        //Passing user data to Home screen with intent
                                        login_intent.putExtra("name", name);
                                        login_intent.putExtra("email", user_email);
                                        login_intent.putExtra("usernameid", usernameId);
                                        login_intent.putExtra("phoneNumber", user_phoneNumber);
                                        login_intent.putExtra("password", user_password);


                                        //Using Shared preference to store value
                                        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();


                                        //Making logged value true on successful login
                                        editor.putBoolean("login_flag", true);


                                        //Storing User data in Shared preference to show again and again in home screen and profile screen
                                        editor.putString("name", name);
                                        editor.putString("usernameid", usernameId);
                                        editor.putString("email", user_email);
                                        editor.putString("phoneNumber", user_phoneNumber);
                                        editor.putString("password", user_password);
                                        editor.apply();


                                        //Makng progressbar invisible
                                        loginprogressbar.setVisibility(View.GONE);


                                        //Giving toast on Successfull login
                                        Toast.makeText(loginUserActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();


                                        //Going to Home activity
                                        startActivity(login_intent);
                                        finish();//finishing all stack activities


                                    } else {
                                        //Showing error on wrong password
                                        password_input_layout.setError("Wrong password");
                                        loginprogressbar.setVisibility(View.GONE);
                                    }


                                } else {
                                    //Showing error when user does not exists
                                    username_input_layout.setError("user does not exists ");
                                    loginprogressbar.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });


                    } else {
                        //Password box error on empty box
                        password_input_layout.setError("please enter password");
                        loginprogressbar.setVisibility(View.GONE);
                    }


                } else {
                    //Username error on empty box
                    username_input_layout.setError("Enter valid username ");
                    loginprogressbar.setVisibility(View.GONE);
                }


            } else {
                //Giving Toast on failed connection
                Toast.makeText(loginUserActivity.this, "Failed to connect to internet!", Toast.LENGTH_SHORT).show();
            }
        });


        //Redirecting to ForgetPassword activity if user forgot the password
        forgetPassword_activity = findViewById(R.id.forgetPasswordClick);


        //Redirecting to Register activity if user is new
        registerUser_activity = findViewById(R.id.register_new_user_onclick);


        //passing intent to forgetpassword activity
        forgetPassword_activity.setOnClickListener(v -> startActivity(new Intent(loginUserActivity.this, forgetPasswordActivity.class)));


        //passing intent to register activity
        registerUser_activity.setOnClickListener(v -> startActivity(new Intent(loginUserActivity.this, RegisterUserActivity.class)));

    }
    @Override
    public void onBackPressed() {


        //Alert Dialog Box on exiting the application
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Are you sure want to exit?");
        alertDialog.setIcon(R.drawable.ic_baseline_exit_to_app_24);


        //On clicking "NO"
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });


        //On Clicking "YES"
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    loginUserActivity.super.onBackPressed();
            }
        });
        alertDialog.show();
    }
}