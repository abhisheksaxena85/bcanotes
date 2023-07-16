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


        //Setting Toolbar
        toolbar = findViewById(R.id.toolbarforget);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Customizing Toolbar
        getSupportActionBar().setTitle("Forget Password");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbarTitleColor));//Setting the toolbar title color
        toolbar.getNavigationIcon().setTint(getResources().getColor(R.color.toolbarTitleColor));//Setting color of back button of toolbar of activity


        //Input Layouts
        username_input_layout = findViewById(R.id.usernameforget_text_input_layout);
        phoneNumber_input_layout = findViewById(R.id.phoneNforget_text_input_layout);
        password_input_layout = findViewById(R.id.passwordforget_text_input_layout);

        //Submit Button
        resetPassword_btn = findViewById(R.id.resetpassword_btn);


        //ProgressBar while submitting
        forgetpassProgressbar = findViewById(R.id.forgetpasswordProgressBar);


        //Submit Button Click
        resetPassword_btn.setOnClickListener(v -> {


            //ProgressBar Visible
            forgetpassProgressbar.setVisibility(View.VISIBLE);


            //Checking Internet connection
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//checking wifi connection
            android.net.NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//checking Internet connection


            //When Connection is available
            if((wifi != null&datac != null) && (wifi.isConnected() | datac.isConnected())) {


                //Converting input from layout Into Text
                String usernameText = username_input_layout.getEditText().getText().toString();
                String phonenumberText = phoneNumber_input_layout.getEditText().getText().toString();
                String passwordText = password_input_layout.getEditText().getText().toString();


                //Check Empty Box
                if (!usernameText.isEmpty()) {
                    //If usernamebox empty then give error
                    username_input_layout.setError(null);
                    username_input_layout.setErrorEnabled(false);


                    if(!phonenumberText.isEmpty() && phonenumberText.matches("(0|91)?[6-9]\\d{9}")){
                        //If PhoneNumber empty or does not match with regex code then give error
                        phoneNumber_input_layout.setError(null);
                        phoneNumber_input_layout.setErrorEnabled(false);


                        if(!passwordText.isEmpty() && passwordText.length() >= 5){
                            //If password lenght is less than 5 char or is empty then through error
                            password_input_layout.setError(null);
                            password_input_layout.setErrorEnabled(false);


                            //Making entered data into final
                            final String username_entered = username_input_layout.getEditText().getText().toString();
                            final String phonenumber_entered = phoneNumber_input_layout.getEditText().getText().toString();


                            //Getting Firebase Instance -> Firebase Connection
                            final FirebaseDatabase firebasedatabase = FirebaseDatabase.getInstance();
                            //Getting Reference of "datauser" in DB
                            DatabaseReference databasereference = firebasedatabase.getReference("datauser");


                            //Checking Entered usename exists in firebase
                            Query checkusername_database = databasereference.orderByChild("username").equalTo(username_entered);
                            checkusername_database.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {


                                    //When Username exists in firebase
                                    if(snapshot.exists()){
                                        username_input_layout.setError(null);//Removing errors if exist
                                        username_input_layout.setErrorEnabled(false);


                                        //Now Authenticating PhoneNumber by matching
                                        Query checkphoneNumber_database = databasereference.orderByChild("phoneNumber").equalTo(phonenumber_entered);
                                        checkphoneNumber_database.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {


                                                //When PhoneNumber matches from Firebase
                                                if(snapshot.exists()){
                                                    phoneNumber_input_layout.setError(null);//Removing Errors if Exists
                                                    phoneNumber_input_layout.setErrorEnabled(false);


                                                    //Showing ALert Box to Tell that it will require login
                                                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(forgetPasswordActivity.this);
                                                    alertDialog.setTitle("Password reset will require login!");//Title of Box
                                                    alertDialog.setIcon(R.drawable.baseline_update_24);//Icon on dialog box


                                                    //When "NO" clicked
                                                    alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            forgetpassProgressbar.setVisibility(View.GONE);//ProgressBar Visibility GONE
                                                        }
                                                    });


                                                    //When "Yes" Clicked
                                                    alertDialog.setPositiveButton("OK, got it!", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {


                                                            //Getting Instance and Reference of Firebase Again
                                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                                            DatabaseReference databasereference = database.getReference("datauser").child(username_entered);


                                                            //Pusing New Password into userProfile using HashMap
                                                            Map<String, Object> updates = new HashMap<>();
                                                            updates.put("password", passwordText);
                                                            databasereference.updateChildren(updates);


                                                            //Showing Toast on Successful password change
                                                            Toast.makeText(forgetPasswordActivity.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();


                                                            //Making Progressbar Invisible
                                                            forgetpassProgressbar.setVisibility(View.GONE);


                                                            //Redirecting to login screen
                                                            startActivity(new Intent(forgetPasswordActivity.this,loginUserActivity.class));
                                                        }
                                                    });
                                                    alertDialog.setCancelable(false);
                                                    alertDialog.show();


                                                }else {
                                                    //Throughing error to phoneNumber input
                                                    phoneNumber_input_layout.setError("Entered Phone Number does not match!!");
                                                    forgetpassProgressbar.setVisibility(View.GONE);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });

                                    }else {


                                        //When username does not exist
                                        username_input_layout.setError("user does not exist");
                                        forgetpassProgressbar.setVisibility(View.GONE);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        }else {
                            //Showing error in password section
                            password_input_layout.setError("Minimum 5 characters are required in password");
                            forgetpassProgressbar.setVisibility(View.GONE);
                        }


                    }else {
                        //Showing error in phonenumber section
                        phoneNumber_input_layout.setError("Enter Registered Phone Number");
                        forgetpassProgressbar.setVisibility(View.GONE);
                    }


                }else {
                    //Showing error when usename is empty
                    username_input_layout.setError("Enter username");
                    forgetpassProgressbar.setVisibility(View.GONE);
                }


            }else {
                //Giving Toast when Internet Connection is OFF
                Toast.makeText(this, "Internet Connection Failed!!", Toast.LENGTH_SHORT).show();
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