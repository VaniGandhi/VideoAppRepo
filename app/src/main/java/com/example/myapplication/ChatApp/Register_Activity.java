package com.example.myapplication.ChatApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {


    FirebaseAuth auth;
    SignInButton google_login;
 Button google,logout,login;
    public static  final  int GOOGLE_SIGNIN=2;

    GoogleSignInClient googleSignInClient;
    ChatAppUserSharedPrefrences chatAppUserSharedPrefrences;
    public static  final String MYPREFERENCES="MyPrefs";

    SharedPreferences sharedPreferences;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        auth=FirebaseAuth.getInstance();
        chatAppUserSharedPrefrences=ChatAppUserSharedPrefrences.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        sharedPreferences=getApplicationContext().getSharedPreferences(MYPREFERENCES,MODE_PRIVATE);

        findIDS();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient=GoogleSignIn.getClient(this,gso);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.google2:
                signIn();
            case R.id.google2_logout:
                logout();

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGNIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

                Toast.makeText(this, "sucess", Toast.LENGTH_SHORT).show();



            } catch (ApiException e) {

                Toast.makeText(Register_Activity.this, "Auth went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void findIDS()
    {
       google_login=findViewById(R.id.google_button2);
       google =findViewById(R.id.google2);
       google_login=findViewById(R.id.google_button2);
        logout=findViewById(R.id.google2_logout);

    }
    private  void writenewuser(String uid,String name, String email,String imgurl)
    {
        UserChatApp userChatApp=new UserChatApp(uid,name,email,imgurl);
        databaseReference.child("users").child(uid).setValue(userChatApp);
    }




    private void signIn() {
       Intent signIntent=googleSignInClient.getSignInIntent();
       startActivityForResult(signIntent,GOOGLE_SIGNIN);
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount account)
    {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("vani", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            String name=user.getDisplayName();
                            String uid=user.getUid();
                            String email=user.getEmail();
                            String image= String.valueOf(user.getPhotoUrl());
                            Log.e("details",""+name+" "+uid+" "+email+" "+image);
                            chatAppUserSharedPrefrences.setname(name);
                            chatAppUserSharedPrefrences.setemail(email);
                            chatAppUserSharedPrefrences.setuid(uid);
                            chatAppUserSharedPrefrences.setProfileUrl(image);
                            Intent intent=new Intent(Register_Activity.this,All_users_with_chats.class);
                            startActivity(intent);
                            writenewuser(uid,name,email,image);
                            sharedPreferences.edit().putBoolean("islogin",true).commit();



                        } else {


                            Toast.makeText(Register_Activity.this,"failed",Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });

    }
    private void getDetails(FirebaseUser user)
    {
        String name=user.getDisplayName();
        String uid=user.getUid();
        String email=user.getEmail();
        String image= String.valueOf(user.getPhotoUrl());
        Log.e("details",""+name+" "+uid+" "+email+" "+image);
        chatAppUserSharedPrefrences.setname(name);
        chatAppUserSharedPrefrences.setemail(email);
        chatAppUserSharedPrefrences.setuid(uid);
        chatAppUserSharedPrefrences.setProfileUrl(image);
        Log.e("name",""+chatAppUserSharedPrefrences.getname());

    }



    public void GoogleSign(View view) {

        if(view==google)
        {
            signIn();
        }
        if(view==logout)

        {
            logout();
        }
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Register_Activity.this,"logged out",Toast.LENGTH_LONG).show();

            }
        });

    }

}

