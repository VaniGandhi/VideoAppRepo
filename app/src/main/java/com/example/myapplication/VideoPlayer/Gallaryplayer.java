package com.example.myapplication.VideoPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.myapplication.R;

public class Gallaryplayer extends AppCompatActivity {

    VideoView videoView;
    Intent intent;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallaryplayer);

        videoView=findViewById(R.id.videoview3);
        mediaController=new MediaController(this);
        intent=getIntent();
        String path=intent.getStringExtra("gallaryvideo");
        videoView.setVideoPath(path);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.requestFocus();
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.setVisibility(View.GONE);
            }
        });

    }
}
