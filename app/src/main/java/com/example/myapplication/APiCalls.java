package com.example.myapplication;


import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.network.Api;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APiCalls {

    static APiCalls instance = null;

    private APiCalls() {
    }


    public static APiCalls getInstance() {
        if (instance == null) {
            instance = new APiCalls();
        }
        return instance;
    }

    void register(HashMap params, CallbackListener listener) {
        Call<LoginModel> call = RetrofitClient.getInstance().getapi().register(params);
        initiateCall(call, listener);
    }
    void login(HashMap params,CallbackListener listener1)
    {
        Call<LoginModel1> call1=RetrofitClient.getInstance().getapi().login(params);
        initiateCall(call1, listener1);
    }
    void forgotPassword(HashMap params, CallbackListener listener2){
        Call<Loginmodel2> call2=RetrofitClient.getInstance().getapi().forgotpassword(params);
        initiateCall(call2,listener2);
    }
    void getfamousspots(CallbackListener listener) {
        Call<Getpojo> call = RetrofitClient.getInstance().getapi().famousspots();
        initiateCall(call,listener);
    }



    <T> void initiateCall(Call<T> call, final CallbackListener listener) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                switch (response.code()) {
                    case 200:
                        listener.onSuccess(response.body());
                        break;
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                listener.onFailure(t.getLocalizedMessage());
            }
        });
    }


    public interface CallbackListener<T> {
        void onSuccess(T model);

        void onFailure(String message);
    }


}
