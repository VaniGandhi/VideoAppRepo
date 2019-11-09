package com.example.myapplication.VideoPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.myapplication.R;
import com.example.myapplication.Roomdatabase.User;

public class VideoPlayerScreen extends AppCompatActivity {
    VideoView videoView;
    Intent  intent;
    private MediaController mediaController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player_screen);
        videoView=findViewById(R.id.videoview1);
        mediaController=new MediaController(this);
        intent=getIntent();
       String videopath= intent.getStringExtra("video");
     //  String path=intent.getStringExtra("videopath1");
        //System.out.println("videopath5---------->"+path);

        Uri contentURI = Uri.parse(videopath);

        String selectedVideoPath = getPath(contentURI);
        videoView.setVideoPath(videopath);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.requestFocus();
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.setVisibility(View.GONE);
                finish();
            }
        });




    }


    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {

            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
}
