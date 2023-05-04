package com.techofgrowth.bcanotes2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.techofgrowth.bcanotes2.R;

import java.util.HashMap;
import java.util.Map;

public class forgetPasswordActivity extends AppCompatActivity {
TextInputLayout username_input_layout, phoneNumber_input_layout, password_input_layout;
Button resetPassword_btn;
ProgressBar forgetpassProgressbar;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        toolbar = findViewById(R.id.toolbarforget);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forget Password");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Setting the toolbar title color
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.toolbarTitleColor));//Setting color of back button of toolbar of activity

        username_input_layout = findViewById(R.id.usernameforget_text_input_layout);
        phoneNumber_input_layout = findViewById(R.id.phoneNforget_text_input_layout);
        password_input_layout = findViewById(R.id.passwordforget_text_input_layout);

        resetPassword_btn = findViewById(R.id.resetpassword_btn);

        forgetpassProgressbar = findViewById(R.id.forgetpasswordProgressBar);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        resetPassword_btn.setOnClickListener(v -> {

            forgetpassProgressbar.setVisibility(View.VISIBLE);

            //Checking Internet connection
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//checking wifi connection
            android.net.NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//checking Internet connection
            if((wifi != null&datac != null) && (wifi.isConnected() | datac.isConnected())) { // on successful connection

                String usernameText = username_input_layout.getEditText().getText().toString();
                String phonenumberText = phoneNumber_input_layout.getEditText().getText().toString();
                String passwordText = password_input_layout.getEditText().getText().toString();

                //Checking for empty boxes
                if (!usernameText.isEmpty()) {
                    username_input_layout.setError(null);
                    username_input_layout.setErrorEnabled(false);
                    if(!phonenumberText.isEmpty() && phonenumberText.matches("(0|91)?[6-9]\\d{9}")){
                        phoneNumber_input_layout.setError(null);
                        phoneNumber_input_layout.setErrorEnabled(false);
                        if(!passwordText.isEmpty() && passwordText.length() >= 5){
                            password_input_layout.setError(null);
                            password_input_layout.setErrorEnabled(false);

                            final String username_entered = username_input_layout.getEditText().getText().toString();
                            final String phonenumber_entered = phoneNumber_input_layout.getEditText().getText().toString();

                            //Connecting to Firebase
                            //Getting instence from firebase
                            final FirebaseDatabase firebasedatabase = FirebaseDatabase.getInstance();
                            DatabaseReference databasereference = firebasedatabase.getReference("datauser");

                            Query checkusername_database = databasereference.orderByChild("username").equalTo(username_entered);

                            checkusername_database.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        username_input_layout.setError(null);
                                        username_input_layout.setErrorEnabled(false);

                                        Query checkphoneNumber_database = databasereference.orderByChild("phoneNumber").equalTo(phonenumber_entered);

                                        checkphoneNumber_database.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    phoneNumber_input_layout.setError(null);
                                                    phoneNumber_input_layout.setErrorEnabled(false);


                                                    //Alert Dialog Box on exiting the application
                                                    alertDialog.setTitle("Password reset will require login!");
                                                    alertDialog.setIcon(R.drawable.baseline_update_24);

                                                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            forgetpassProgressbar.setVisibility(View.GONE);
                                                        }
                                                    });
                                                    alertDialog.setPositiveButton("OK, got it!", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                            DatabaseReference databasereference = database.getReference("datauser").child(username_entered);
                                                            Map<String, Object> updates = new HashMap<>();
                                                            updates.put("password", passwordText);
                                                            databasereference.updateChildren(updates);
                                                            Toast.makeText(forgetPasswordActivity.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                                                            forgetpassProgressbar.setVisibility(View.GONE);
                                                            startActivity(new Intent(forgetPasswordActivity.this,loginUserActivity.class));

                                                        }
                                                    });
                                                    alertDialog.setCancelable(false);
                                                    alertDialog.show();

                                                }else {
                                                    phoneNumber_input_layout.setError("Entered Phone Number does not match!!");
                                                    forgetpassProgressbar.setVisibility(View.GONE);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                    }else {
                                        username_input_layout.setError("user does not exist");
                                        forgetpassProgressbar.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });



                        }else {
                            password_input_layout.setError("Minimum 5 characters are required in password");
                            forgetpassProgressbar.setVisibility(View.GONE);
                        }
                    }else {
                        phoneNumber_input_layout.setError("Enter Registered Phone Number");
                        forgetpassProgressbar.setVisibility(View.GONE);
                    }
                }else {
                    username_input_layout.setError("Enter username");
                    forgetpassProgressbar.setVisibility(View.GONE);
                }
            }else {
                Toast.makeText(this, "Failed to connect to internet", Toast.LENGTH_SHORT).show();
                forgetpassProgressbar.setVisibility(View.GONE);
            }
        });



    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}