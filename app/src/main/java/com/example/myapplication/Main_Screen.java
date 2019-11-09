package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.network.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Screen extends AppCompatActivity  implements  APiCalls.CallbackListener<Getpojo>{
    Button logout;
    SharedPreferences sharedPreferences;
    CustomAdapter adapter;
    RecyclerView recyclerView;
    List<Getpojo.DataBean> famousSpot=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        logout = findViewById(R.id.logout_btn);
        APiCalls.getInstance().getfamousspots(Main_Screen.this);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("MyPrefs", 0);
                sharedPreferences.edit().clear().commit();
                Intent intent = new Intent(Main_Screen.this, LauchScreen.class);
                startActivity(intent);
            }
        });




    }
   

    @Override
    public void onSuccess(Getpojo model) {
        Toast.makeText(this,"sucess get ",Toast.LENGTH_LONG).show();
        generateDataList(model.getSpots().getData());



    }


    @Override
    public void onFailure(String message) {
        Log.e("error",""+message);
        Toast.makeText(this,"failed"+message,Toast.LENGTH_LONG).show();

    }
    private void generateDataList( List<Getpojo.DataBean> famousSpot) {
        recyclerView=findViewById(R.id.recycler_view);
        adapter=new CustomAdapter(this,famousSpot);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
    }


