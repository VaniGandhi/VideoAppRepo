package com.example.myapplication.VideoPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.myapplication.R;

public class CameraPlayer extends AppCompatActivity {

    VideoView videoView;
    Intent intent;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_player);

        videoView=findViewById(R.id.videoview2);
        mediaController=new MediaController(this);
        intent=getIntent();
        String path=intent.getStringExtra("cameravideo");
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
