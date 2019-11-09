package com.example.myapplication.QuizApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity   {

    EditText name, email, password;
    Button signup;
    TextView alreadyRegistered;
    String getname,getemail,getpassword;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.signupenter_name);
        email=findViewById(R.id.signupenter_email);
        password=findViewById(R.id.signupenter_password);
        signup=findViewById(R.id.signupbutton);
        alreadyRegistered=findViewById(R.id.signuptextnalready);
        getemail=email.getText().toString();
        getname=name.getText().toString();
        getpassword=password.getText().toString();
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);

                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signup();

            }
        });

    }

    private void Signup()
    {

    }


}
