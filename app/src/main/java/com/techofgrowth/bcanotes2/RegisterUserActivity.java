package com.techofgrowth.bcanotes2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class RegisterUserActivity extends AppCompatActivity {
TextInputLayout name_input_text_layout,username_input_text_layout,email_input_text_layout,phone_number_input_text_layout,password_input_text_layout;
Button register_btn;
FirebaseDatabase firebasedatabase;
DatabaseReference databasereference;
ProgressBar registerprogressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);


        //ProgressBar
        registerprogressBar = findViewById(R.id.registerProgressBar);


        //Register Button
        register_btn = findViewById(R.id.registerUser_btn);


        //Connecting to input layouts
        name_input_text_layout = findViewById(R.id.nameRegister_text_input_layout);
        username_input_text_layout = findViewById(R.id.usernameRegister_text_input_layout);
        email_input_text_layout = findViewById(R.id.emailRegister_text_input_layout);
        phone_number_input_text_layout = findViewById(R.id.phoneNRegister_text_input_layout);
        password_input_text_layout = findViewById(R.id.passwordRegister_text_input_layout);


        //Register Submit onclick
        register_btn.setOnClickListener(v -> {


            //Checking Internet Connection of device
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//checking wifi connection
            android.net.NetworkInfo datac = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//checking Internet connection


            //When Internet is available
            if((wifi != null&datac != null) && (wifi.isConnected() | datac.isConnected())) {


                //Converting Input layout into String
                String name_input_data = name_input_text_layout.getEditText().getText().toString();
                String username_input_data = username_input_text_layout.getEditText().getText().toString();
                String email_input_data = email_input_text_layout.getEditText().getText().toString();
                String phone_num_input_data = phone_number_input_text_layout.getEditText().getText().toString();
                String password_input_data = password_input_text_layout.getEditText().getText().toString();


                //Making ProgressBar Visible
                registerprogressBar.setVisibility(View.VISIBLE);


                //Checking if the boxes are empty
                if(!name_input_data.isEmpty()){
                    //Name input Box
                    name_input_text_layout.setError(null);
                    name_input_text_layout.setErrorEnabled(false);


                    if(!username_input_data.isEmpty()) {
                        //Username Input Box
                        username_input_text_layout.setError(null);
                        username_input_text_layout.setErrorEnabled(false);


                        if (!email_input_data.isEmpty() && email_input_data.matches("(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})")) { //Email Regex Code
                            //Email Input Box
                            email_input_text_layout.setError(null);
                            email_input_text_layout.setErrorEnabled(false);


                            if (!phone_num_input_data.isEmpty() && phone_num_input_data.matches("(0|91)?[6-9]\\d{9}")) { //Phone NUmber Regex code
                                //Phone Number INput Box
                                phone_number_input_text_layout.setError(null);
                                phone_number_input_text_layout.setErrorEnabled(false);


                                if (!password_input_data.isEmpty() && password_input_data.length() >= 4) {
                                    //Password Input Box
                                    password_input_text_layout.setError(null);
                                    password_input_text_layout.setErrorEnabled(false);


                                    //Assigning register time date in YEAR-MONTH-DAY-HOUR-MINUTES-SECOND Format
                                    Date currentTime = new Date();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String timeOnregister = sdf.format(currentTime);


                                    //Getting Instance and Reference of Datauser in firebase
                                    firebasedatabase = FirebaseDatabase.getInstance();
                                    databasereference = firebasedatabase.getReference("datauser");


                                    //Converting Userdate into String
                                    String name_input_data_DB = name_input_text_layout.getEditText().getText().toString();
                                    String username_input_data_DB = username_input_text_layout.getEditText().getText().toString();
                                    String email_input_data_DB = email_input_text_layout.getEditText().getText().toString();
                                    String phone_num_input_data_DB = phone_number_input_text_layout.getEditText().getText().toString();
                                    String password_input_data_DB = password_input_text_layout.getEditText().getText().toString();


                                    //Checking if the Username already present in firebase
                                    Query checkusername_database = databasereference.orderByChild("username").equalTo(username_input_data_DB);
                                    checkusername_database.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                                            //If Username exists in firebase
                                            if(snapshot.exists()){
                                                //Then Through Error
                                                username_input_text_layout.setError("Username already taken!");
                                                registerprogressBar.setVisibility(View.GONE);


                                            //Otherwise
                                            }else{
                                                //Remove error if exist
                                                username_input_text_layout.setError(null);
                                                username_input_text_layout.setErrorEnabled(false);


                                                //Checking same email address -> In Firebase
                                                Query checkuseremail_database = databasereference.orderByChild("emailId").equalTo(email_input_data_DB);
                                                checkuseremail_database.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                                                        //IF Email already exist then through error
                                                        if(snapshot.exists()){
                                                            email_input_text_layout.setError("This email id already registered with another account");
                                                            registerprogressBar.setVisibility(View.GONE);


                                                        //Otherwise
                                                        }else{
                                                            //Remove error if exists
                                                            email_input_text_layout.setError(null);
                                                            email_input_text_layout.setErrorEnabled(false);


                                                            //Sending User's Data into Getter Setter Methods
                                                            dataStorage user_datastorage_obj = new dataStorage(name_input_data_DB,username_input_data_DB,email_input_data_DB,phone_num_input_data_DB,password_input_data_DB,timeOnregister);


                                                            //Sending Complete user's data to firebase
                                                            databasereference.child(username_input_data_DB).setValue(user_datastorage_obj);


                                                            //Making ProgressBar Visible
                                                            registerprogressBar.setVisibility(View.GONE);


                                                            //Showing Toast of Successfull Register
                                                            Toast.makeText(RegisterUserActivity.this, "Registered", Toast.LENGTH_SHORT).show();


                                                            //Redirecting to Home Screen
                                                            Intent registerInent = new Intent(RegisterUserActivity.this,MainActivity.class);


                                                            //Sending User's data to Home screen too
                                                            registerInent.putExtra("name",name_input_data_DB);
                                                            registerInent.putExtra("usernameid",username_input_data_DB);
                                                            registerInent.putExtra("email",email_input_data_DB);
                                                            registerInent.putExtra("phoneNumber",phone_num_input_data_DB);
                                                            registerInent.putExtra("password",password_input_data_DB);


                                                            //Making Logged in Value true in Shared Preferences
                                                            SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                                                            SharedPreferences.Editor editor = preferences.edit();
                                                            editor.putBoolean("login_flag", true);


                                                            //Storing Userdata in Shared Preference in Splash
                                                            editor.putString("name",name_input_data_DB);
                                                            editor.putString("usernameid",username_input_data_DB);
                                                            editor.putString("email",email_input_data_DB);
                                                            editor.putString("phoneNumber",phone_num_input_data_DB);
                                                            editor.putString("password",password_input_data_DB);
                                                            editor.apply();


                                                            //Applying Intent
                                                            startActivity(registerInent);
                                                            finishAffinity();
                                                        }
                                                    }
                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                            }
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


                                //Giving error to password layout box
                                } else {
                                    password_input_text_layout.setError("Minimum 4 characters are required in password!");
                                    registerprogressBar.setVisibility(View.GONE);
                                }


                            //Giving error to phonenumber layout Box
                            } else {
                                phone_number_input_text_layout.setError("Enter valid phone number");
                                registerprogressBar.setVisibility(View.GONE);
                            }


                        //Giving error to Email Layout Box
                        } else {
                            email_input_text_layout.setError("Enter valid email");
                            registerprogressBar.setVisibility(View.GONE);
                        }


                    //Giving error to username layout Box
                    }else{
                        username_input_text_layout.setError("Enter username");
                        registerprogressBar.setVisibility(View.GONE);
                    }


                //Giving error to name layout Box
                }else{
                    name_input_text_layout.setError("Enter your name");
                    registerprogressBar.setVisibility(View.GONE);
                }


            //When Internet is not available Show Toast
            }else{
                Toast.makeText(this, "Failed to connect internet!", Toast.LENGTH_SHORT).show();
            }
        });


        //Coming back to login Screen on Text CLick
        TextView loginTextRegisterActivity = findViewById(R.id.loginTextRegisterActivity);
        loginTextRegisterActivity.setOnClickListener(v -> {
            super.onBackPressed();
        });
    }
}