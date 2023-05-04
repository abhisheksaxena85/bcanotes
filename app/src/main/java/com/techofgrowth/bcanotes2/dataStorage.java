package com.techofgrowth.bcanotes2;

import androidx.annotation.Keep;

import com.google.firebase.database.IgnoreExtraProperties;

@Keep
@IgnoreExtraProperties
public class dataStorage {
    String name,username,emailId,phoneNumber,password,timeofregister;
    public dataStorage() {
    }
    public dataStorage(String name, String username, String emailId, String phoneNumber, String password,String timeofregister) {
        this.name = name;
        this.username = username;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.timeofregister = timeofregister;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getTime(){
        return timeofregister;
    }
    public void setTime(String timeofregister){
        this.timeofregister = timeofregister;
    }
}
