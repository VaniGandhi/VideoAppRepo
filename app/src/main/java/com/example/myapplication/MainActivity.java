package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements APiCalls.CallbackListener<LoginModel>, View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "Activity";

    EditText useremail, userpassword, username, userconfirmpassword;
    TextView alreadyregistered, loginnow;
    Button login, facebooklogin, logout, fb, google, instagram;
    SignInButton googlelogin;
    TelephonyManager telephonyManager;
    String deviceid;
    private CallbackManager callbackManager;
    Context context;
    UserSharedPrefernce userSharedPrefernce;
    GoogleSignInOptions signInOptions;
    GoogleApiClient apiClient;
    public static final int REQ_CODE = 9001;
    public static String DEVICE_TYPE = "A";
    private InstagramApp mapp;
    private Handler handler;
    private HashMap<String, String> userInfoHashmap = new HashMap<String, String>();
    final HashMap<String, String> hashMap = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.activity_main);


        useremail = findViewById(R.id.edit_email);
        userpassword = findViewById(R.id.edit_password);
        userconfirmpassword = findViewById(R.id.edit_confirmpassword);
        username = findViewById(R.id.edit_name);

        alreadyregistered = findViewById(R.id.Text_view1);
        login = findViewById(R.id.login_button);
        facebooklogin = findViewById(R.id.facebooklogin_button);
        googlelogin = findViewById(R.id.google_button);
        callbackManager = CallbackManager.Factory.create();
        userSharedPrefernce = UserSharedPrefernce.getInstance();
        loginnow = findViewById(R.id.Text_view3);
        logout = findViewById(R.id.signout);
        fb = findViewById(R.id.fb);
        google = findViewById(R.id.google);
        instagram = findViewById(R.id.instagram);
        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        apiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).
                addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        mapp = new InstagramApp(this, AppConfig.CLIENT_ID, AppConfig.CLIENT_SCERET, AppConfig.CALLBACK_URL);
        mapp.setListener(new InstagramApp.OAuthAuthenticationListener() {
            @Override
            public void onSuccess() {
                instagram.setText("disconnect");
                mapp.fetchUserName(handler);
                Toast.makeText(getApplicationContext(), "sucess", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFail(String error) {
                Toast.makeText(getApplicationContext(), "failed" + error, Toast.LENGTH_LONG).show();

            }
        });
        telephonyManager = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        deviceid = telephonyManager.getDeviceId();
        Log.e("id",""+deviceid);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {

                if (message.what == InstagramApp.WHAT_FINALIZE) {
                    userInfoHashmap = mapp.getUserInfo();
                } else if (message.what == InstagramApp.WHAT_FINALIZE) {
                    Toast.makeText(MainActivity.this, "Check your network.",
                            Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


        facebooklogin.setOnClickListener(this);
        loginnow.setOnClickListener(this);
        login.setOnClickListener(this);
        googlelogin.setOnClickListener(this);
        logout.setOnClickListener(this);
        instagram.setOnClickListener(this);




        final HashMap<String, String> hashMap = new HashMap<>();


    }


    @Override
    public void onSuccess(LoginModel model) {
        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        String token=model.getData().getToken();
        UserSharedPrefernce.getInstance().setTOKEN(token);
        Intent intent = new Intent(MainActivity.this, Main_Screen.class);
        startActivity(intent);

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Handleresult(result);
        }
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if (currentAccessToken == null) {
                username.setText("");
                useremail.setText("");
                Toast.makeText(MainActivity.this, "User logged out", Toast.LENGTH_LONG).show();

            } else {
                loaduserprofile(currentAccessToken);
            }

        }
    };


    private void loaduserprofile(AccessToken accessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("name");
                    String email = object.getString("email");
                    String id = object.getString("id");
                    userSharedPrefernce.setname(name);
                    userSharedPrefernce.setemail(email);
                    userSharedPrefernce.setsocialid(id);
                    Log.e("name,email", "" + userSharedPrefernce.getname() + "" + userSharedPrefernce.getemail());
                    Intent intent = new Intent(MainActivity.this, Main_Screen.class);
                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,name,email");

        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    public void login() {


        String name1 = username.getText().toString().trim();
        String email1 = useremail.getText().toString().trim();
        String password1 = userpassword.getText().toString().trim();
        String confirmpassword1 = userconfirmpassword.getText().toString().trim();

        Log.e("click5", "" + name1 + " " + email1 + " " + password1 + " " + deviceid);

        String response = Validations.getInstance().validateRegisterationFields(name1, email1, password1, confirmpassword1);

        if (!TextUtils.isEmpty(response)) {
            Toast.makeText(MainActivity.this, response
                    , Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "onClick:  params==> " + name1 + " " + email1 + " " + password1 + " " + confirmpassword1 + " " + deviceid);

            hashMap.put("email", email1);
            hashMap.put("password", password1);
            hashMap.put("confirm_password", confirmpassword1);
            hashMap.put("device_id", deviceid);
            hashMap.put("device_type", DEVICE_TYPE);

            APiCalls.getInstance().register(hashMap, MainActivity.this);
        }


    }

    private void googleSignin() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    private void Handleresult(GoogleSignInResult signInResult) {
        if (signInResult.isSuccess()) {
            GoogleSignInAccount account = signInResult.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String id = account.getId();
          //  String deviceid=telephonyManager.getDeviceId();

            hashMap.put("email", email);
            hashMap.put("name",name);
     //       hashMap.put("password", password);
       //     hashMap.put("confirm_password", confirmpassword);
            hashMap.put("device_id", deviceid);
            hashMap.put("device_type", DEVICE_TYPE);
            hashMap.put("socail_id",id);

            Log.e("my", name + " " + email + " " + id+" "+deviceid+" "+DEVICE_TYPE);
            APiCalls.getInstance().register(hashMap,MainActivity.this);

            /*Intent intent = new Intent(MainActivity.this, Main_Screen.class);
            startActivity(intent);*/
        //   userSharedPrefernce.setname(name);
          //  userSharedPrefernce.setemail(email);
            //userSharedPrefernce.setsocialid(id);
            UpdateUI(true);


        } else {
            UpdateUI(false);
        } }

    private void UpdateUI(Boolean islogin) {

    }

    private void logout() {


        Auth.GoogleSignInApi.signOut(apiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                UpdateUI(false);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.facebooklogin_button:
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this,
                        Arrays.asList("email"));
                break;
            case R.id.Text_view3:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.login_button:
                login();
                break;
            case R.id.google_button:
                Log.e("pressed", "nnn");

                googleSignin();
                break;
            case R.id.signout:

                break;
            case R.id.instagram:
                Logininstagramuser();
                Log.e("veiw", "dsg");
                viewContents();

                break;
        }
    }

    private void viewContents() {
        Log.e("contents", "");
        String name = userInfoHashmap.get(InstagramApp.TAG_USERNAME);
        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
        Log.e("name", "" + name);
        username.setText(name);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public void onClickFacebookButton(View view) {
        if (view == fb) {
            facebooklogin.performClick();
        } else if (view == google) {
            googleSignin();
        }
    }


    private void Logininstagramuser() {
        if (mapp.hasAccessToken()) {
            Toast.makeText(this, "connect", Toast.LENGTH_LONG).show();


            final AlertDialog.Builder builder = new AlertDialog.Builder(
                    MainActivity.this);
            builder.setMessage("Disconnect from Instagram?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    mapp.resetAccessToken();
                                    // btnConnect.setVisibility(View.VISIBLE);

                                    instagram.setText("Connect");
                                    // tvSummary.setText("Not connected");
                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            final AlertDialog alert = builder.create();
            alert.show();
        } else {
            Toast.makeText(this, "nconnect", Toast.LENGTH_LONG).show();
            mapp.authorize();


        }
    }


}






