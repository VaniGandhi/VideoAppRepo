package com.example.myapplication.ChatApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_Profile extends AppCompatActivity {
    CircleImageView circularimage;
    TextView name, email;
    ChatAppUserSharedPrefrences chatAppUserSharedPrefrences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profile);
        chatAppUserSharedPrefrences=ChatAppUserSharedPrefrences.getInstance();
        Log.e("name",""+chatAppUserSharedPrefrences.getname());
        circularimage=findViewById(R.id.user_image);
        name=findViewById(R.id.user_name);
        email=findViewById(R.id.user_email);
        name.setText(chatAppUserSharedPrefrences.getname());
        email.setText(chatAppUserSharedPrefrences.getemail());
       String url=chatAppUserSharedPrefrences.getProfileUrl();

    Picasso.with(getApplicationContext()).load(url).transform(new RoundedCornersTransformation(10,10)).into(circularimage);
    circularimage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(User_Profile.this,ChattingActivity.class);
            startActivity(intent);
        }
    });

    }


}
