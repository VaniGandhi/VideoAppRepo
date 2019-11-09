package com.example.myapplication.ChatApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myapplication.LauchScreen;
import com.example.myapplication.Main_Screen;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Start_Activity extends AppCompatActivity {
    public static  final String MYPREFERENCES="MyPrefs";
    SharedPreferences sharedPreferences;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);
        sharedPreferences=this.getSharedPreferences("MyPrefs",MODE_PRIVATE);

        if(sharedPreferences.getBoolean("islogin",false))
        {
            Intent intent=new Intent(Start_Activity.this,ChattingActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent=new Intent(Start_Activity.this,Register_Activity.class);
            startActivity(intent);
        }
       /* firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
//        System.out.println("uid-------------->"+firebaseUser.getUid());
        databaseReference= FirebaseDatabase.getInstance().getReference("users/");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue()==null)
                    {
                        Intent intent=new Intent(Start_Activity.this,Register_Activity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent=new Intent(Start_Activity.this,ChattingActivity.class);
                        startActivity(intent);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
*/
    }
}
