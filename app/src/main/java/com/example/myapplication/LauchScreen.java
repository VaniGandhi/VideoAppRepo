package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;

public class LauchScreen extends AppCompatActivity {
    Button login,Signup;
    ImageView img;
    public static  final String MYPREFERENCES="MyPrefs";
    SharedPreferences sharedPreferences;
    Intent intent;

    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_lauch_screen);
        sharedPreferences=this.getSharedPreferences("MyPrefs",MODE_PRIVATE);
        RotateAnimation animation=new RotateAnimation(0f, 360f, 10f, 10f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(700);

        login=findViewById(R.id.llogin_button);
        Signup=findViewById(R.id.lsignup_button);
        img=findViewById(R.id.img);
        shimmerFrameLayout=findViewById(R.id.shimmerlayout);
        shimmerFrameLayout.startShimmerAnimation();
        //img.setAnimation(animation);
        if(sharedPreferences.getBoolean("islogin",false))
        {


                    Intent intent=new Intent(LauchScreen.this,Main_Screen.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_in_right);


        }
        else
        {
           // Intent intent=new Intent(LauchScreen.this, LoginActivity.class);
          //  startActivity(intent);
            getApplicationContext();

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LauchScreen.this,LoginActivity.class);
                startActivity(intent);

            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LauchScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
