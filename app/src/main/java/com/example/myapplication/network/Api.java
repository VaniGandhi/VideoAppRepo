package com.example.myapplication.network;

import com.example.myapplication.Getpojo;
import com.example.myapplication.LoginModel;
import com.example.myapplication.LoginModel1;
import com.example.myapplication.Loginmodel2;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


  @FormUrlEncoded
  @POST("register")
  Call<LoginModel> register(@FieldMap HashMap<String,String> hashMap);



  @FormUrlEncoded
  @POST("login")
  Call<LoginModel1> login(@FieldMap HashMap<String, String> lhashmap);

  @FormUrlEncoded
  @POST("forgotPassword")
  Call<Loginmodel2> forgotpassword(@FieldMap HashMap<String ,String> fhashmap);


  @GET("famousSpot")
  Call<Getpojo> famousspots();


}
