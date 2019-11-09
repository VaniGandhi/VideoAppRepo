package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSharedPrefernce {

    private String NAME = "name";
    private String EMAIL = "email";
    private String DEVICEID = "device_id";
    private String PASSWORD = "password";
    private String DEVICETYPE = "device_type";
    private String SOCIALID = "socail_id";
    private String LOGINTYPE = "login_type";


    private  String TOKEN="token";
    SharedPreferences sharedPreferences;
    static UserSharedPrefernce userSharedPrefernce = null;
    Context context;

    public UserSharedPrefernce() {

    }

    public UserSharedPrefernce(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

    }

    public static UserSharedPrefernce

    getInstance() {
        if (userSharedPrefernce == null) {
            userSharedPrefernce = new UserSharedPrefernce(BaseApplication.getContext());
        }
        return userSharedPrefernce;
    }

    public void setname(String name) {
        sharedPreferences.edit().putString(NAME, name).commit();
    }

    public String getname() {
        return sharedPreferences.getString(NAME, "");
    }
    public void setTOKEN(String token) {
        sharedPreferences.edit().putString(TOKEN,token).commit();
    }

    public String getTOKEN() {
        return sharedPreferences.getString(TOKEN, "");
    }

    public void setemail(String email) {
        sharedPreferences.edit().putString(EMAIL, email).commit();
    }

    public String getemail() {
        return sharedPreferences.getString(EMAIL, "");
    }

    public void setpassword(String password) {
        sharedPreferences.edit().putString(PASSWORD, password).commit();
    }

    public String getpassword() {
        return sharedPreferences.getString(PASSWORD, "");
    }

    public void setdeviceid(String deviceid) {
        sharedPreferences.edit().putString(DEVICEID, deviceid).commit();
    }

    public String getdeviceid() {
        return sharedPreferences.getString(DEVICEID, "");
    }

    public void setdevicetype(String devicetype) {
        sharedPreferences.edit().putString(DEVICETYPE, devicetype).commit();
    }

    public String getdevicetype() {
        return sharedPreferences.getString(DEVICETYPE, "");
    }

    public void setsocialid(String socialid) {
        sharedPreferences.edit().putString(SOCIALID, socialid).commit();
    }

    public String getsocialid() {
        return sharedPreferences.getString(SOCIALID, "");
    }

    public void logintype(String logintype) {
        sharedPreferences.edit().putString(LOGINTYPE, logintype).commit();
    }

    public String getlogintype() {
        return sharedPreferences.getString(LOGINTYPE, "");
    }




}
