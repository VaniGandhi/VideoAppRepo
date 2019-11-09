package com.example.myapplication.ChatApp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.example.myapplication.BaseApplication;

public class ChatAppUserSharedPrefrences {
    private String NAME = "name";
    private String EMAIL = "email";
    private  String ProfileUrl="profileUrl";
    private String Uid="Uid";
    SharedPreferences sharedPreferences;
    static ChatAppUserSharedPrefrences chatAppUserSharedPrefrences=null;
    Context context;

    public ChatAppUserSharedPrefrences() {

    }

    public ChatAppUserSharedPrefrences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

    }

    public static ChatAppUserSharedPrefrences getInstance() {
        if (chatAppUserSharedPrefrences == null) {
            chatAppUserSharedPrefrences = new ChatAppUserSharedPrefrences(BaseApplication.getContext());
        }
        return chatAppUserSharedPrefrences;
    }

    public void setname(String name) {
        sharedPreferences.edit().putString(NAME, name).commit();
    }

    public String getname() {
        return sharedPreferences.getString(NAME, "");
    }
    public void setemail(String email) {
        sharedPreferences.edit().putString(EMAIL, email).commit();
    }

    public String getemail() {
        return sharedPreferences.getString(EMAIL, "");
    }
    public void setuid(String uid) {
        sharedPreferences.edit().putString(Uid, uid).commit();
    }

    public String getuid() {
        return sharedPreferences.getString(Uid, "");
    }
    public void setProfileUrl(String profileUrl) {
        sharedPreferences.edit().putString(ProfileUrl, profileUrl).commit();
    }

    public String getProfileUrl() {
        return sharedPreferences.getString(ProfileUrl, "");
    }


}
