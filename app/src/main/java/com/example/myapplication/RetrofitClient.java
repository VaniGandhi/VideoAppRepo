package com.example.myapplication;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.myapplication.network.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String Base_url = "http://1.6.98.141/Mobile/beaver/api/";

    private static RetrofitClient instance;
    private Retrofit retrofit;

    private RetrofitClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request;
                String token = UserSharedPrefernce.getInstance().getTOKEN();
                if (!TextUtils.isEmpty(token)) {
                    request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token)
                            .addHeader("Accept", "application/json").build();
                } else {
                    request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                }

                return chain.proceed(request);
            }
        });
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
               .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();

        }
        return instance;
    }

    public Api getapi() {
        return retrofit.create(Api.class);

    }


}