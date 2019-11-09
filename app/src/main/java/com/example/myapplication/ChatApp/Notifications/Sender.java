package com.example.myapplication.ChatApp.Notifications;

import android.provider.ContactsContract;

import com.example.myapplication.LoginModel;
import com.google.gson.annotations.SerializedName;

public class Sender {
    @SerializedName("data")
    public Data_model data;
    @SerializedName("to")
    private  String to;



    public Sender(Data_model data, String to) {
        this.data = data;
        this.to = to;
    }
    public Sender()
    {

    }


    public Data_model getData() {
        return data;
    }

    public void setData(Data_model data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
