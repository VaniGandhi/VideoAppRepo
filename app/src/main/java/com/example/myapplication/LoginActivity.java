package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements APiCalls.CallbackListener<LoginModel1> {
    EditText luserdevice_type, luserpassword, luserlogin_type,lname,lemail;
    Button Login_button;
    TelephonyManager telephonyManager;
    String deviceid;
    TextView t1, t2;
    public static  final String MYPREFERENCES="MyPrefs";

    SharedPreferences sharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        setContentView(R.layout.activity_login);
        luserdevice_type = findViewById(R.id.device_type);

       luserpassword = findViewById(R.id.password);
        Login_button = findViewById(R.id.login_button);
        lname=findViewById(R.id.name);
        lemail=findViewById(R.id.email);
        t2 = findViewById(R.id.t2);
        t1 = findViewById(R.id.t1);
        sharedPreferences=getApplicationContext().getSharedPreferences(MYPREFERENCES,MODE_PRIVATE);

        telephonyManager = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions

            return;
        }
        deviceid = telephonyManager.getDeviceId();
        final HashMap<String, String> lhashMap = new HashMap<>();
        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String luserdevice_type1 = luserdevice_type.getText().toString().trim();

                String luserpassword1 = luserpassword.getText().toString().trim();
                String lname1=lname.getText().toString().trim();
                String lemail1=lemail.getText().toString().trim();
                Log.e("click", " " + luserdevice_type1  +  " " +deviceid);

                String response1 = Validations.getInstance().Validateloginfields(luserdevice_type1);
                if (!TextUtils.isEmpty(response1)) {
                    Toast.makeText(LoginActivity.this, response1, Toast.LENGTH_LONG).show();
                } else {
                    lhashMap.put("device_type", luserdevice_type1);
                    lhashMap.put("device_id", deviceid);

                    lhashMap.put("name", lname1);
                    lhashMap.put("email",lemail1);
                    lhashMap.put("password",luserpassword1);


                    APiCalls.getInstance().login(lhashMap, LoginActivity.this);
                    sharedPreferences.edit().putBoolean("islogin",true).commit();

             }
        }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ForgotpasswordActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void onSuccess(LoginModel1 model) {
        Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_LONG).show();
      /*  String token=model.getData().getToken();

        UserSharedPrefernce.getInstance().setTOKEN(token);
        Log.e("token",""+token+""+UserSharedPrefernce.getInstance().getTOKEN());*/
        Intent intent=new Intent(LoginActivity.this,Main_Screen.class);
        startActivity(intent);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

    }
}


