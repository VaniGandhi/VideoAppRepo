package com.example.myapplication.ChatApp;

import android.app.Notification;

import com.example.myapplication.ChatApp.Notifications.Myresponse;
import com.example.myapplication.ChatApp.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIservice {

    @Headers(

            {
                    "Content_Type:application/json",
                    "Authorization:key=AIzaSyAftgxPcHlEwP5gC_Ji1coD5rzFPoOd-Uc"}

    )

    @POST("fcm/send")
   Call<Myresponse> sendnotification(@Body Sender body );
}
