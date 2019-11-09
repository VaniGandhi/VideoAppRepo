package com.example.myapplication.Roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.VideoPlayer.MyAppVideoDatabase;

public class Main2Activity extends AppCompatActivity {
public  static   MyAppDatabase myAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myAppDatabase=Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class,"videodb")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        if(findViewById(R.id.frame1)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.frame1,new HomeFragment()).commit();
        }
    }

}
