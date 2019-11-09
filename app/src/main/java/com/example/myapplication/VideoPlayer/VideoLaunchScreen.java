package com.example.myapplication.VideoPlayer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.PrimaryKey;

import com.example.myapplication.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class VideoLaunchScreen  extends AppCompatActivity {

    ImageView img;
    ShimmerFrameLayout shimmerFrameLayout;
    ProgressDialog progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingactivity);
       progressBar=new ProgressDialog(this);
       progressBar.setMessage("please wait");

        Thread loadingThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(3000);
                } catch (Exception e) {

                } finally {

                    Intent main = new Intent(VideoLaunchScreen.this,SongListScreen.class);
                    startActivity(main);
                    finish();
                }
            }
        };
        loadingThread.start();
       /* RotateAnimation animation=new RotateAnimation(0f, 360f, 10f, 10f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(700);
        shimmerFrameLayout=findViewById(R.id.shimmerlayout);
        shimmerFrameLayout.startShimmerAnimation();
        shimmerFrameLayout.postOnAnimation(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(VideoLaunchScreen.this,SongListScreen.class);
                startActivity(intent);

            }
        }
        );*/

    }
}
