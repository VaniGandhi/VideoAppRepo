package com.example.myapplication.VideoPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.myapplication.R;

public class Play_with_url extends AppCompatActivity {


    private VideoView vv;
    private MediaController mediacontroller;
    private Uri uri;

    ProgressDialog progressDialog;
    EditText enterurl;
    ImageView send_button;
    //String url ="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_with_url);

        vv = findViewById(R.id.vv);
        enterurl = findViewById(R.id.enter_url);
        send_button = findViewById(R.id.enter_button);

        mediacontroller = new MediaController(this);
        progressDialog = new ProgressDialog(this);
     /*   progressDialog.setMessage("buffering");
        progressDialog.show();*/
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String url1=enterurl.getText().toString();
                if(!TextUtils.isEmpty(url1)) {
                    System.out.println("url-------->" + url1);
                    uri = Uri.parse(url1);
                    vv.setMediaController(mediacontroller);
                    mediacontroller.setAnchorView(vv);
                    vv.setVideoURI(uri);
                    vv.start();
                    vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            enterurl.setText("");

                        }
                    });
                }
                else

                {
                    Toast.makeText(getApplicationContext(), "Please enter url", Toast.LENGTH_SHORT).show();
                }


            }
        }

        );


     /*   uri = Uri.parse(url);
        vv.setMediaController(mediacontroller);
        mediacontroller.setAnchorView(vv);
        vv.setVideoURI(uri);
        vv.start();*/
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
            }
        });
    }
}






