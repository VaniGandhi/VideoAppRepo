package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ForgotpasswordActivity extends AppCompatActivity implements APiCalls.CallbackListener<Loginmodel2>{
    EditText useremail;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        useremail=(EditText)findViewById(R.id.edittxt);
        send=(Button)findViewById(R.id.send_button);
        final HashMap<String, String> fhashmap=new HashMap<>();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=useremail.getText().toString().trim();
                String response=Validations.getInstance().Validateemail(email);
                if(!TextUtils.isEmpty(response))
                {
                    Toast.makeText(ForgotpasswordActivity.this,response,Toast.LENGTH_LONG).show();
                }
                else
                {
                    fhashmap.put("email",email);
                    APiCalls.getInstance().forgotPassword(fhashmap,  ForgotpasswordActivity.this);
                }
            }
        });
    }




    @Override
    public void onSuccess(Loginmodel2 model) {
        Toast.makeText(ForgotpasswordActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(ForgotpasswordActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
