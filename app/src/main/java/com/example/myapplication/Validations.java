package com.example.myapplication;

import android.text.TextUtils;

public class Validations {

    private static Validations instance = null;

    private Validations() {
    }

    public static Validations getInstance() {
        if (instance == null) return new Validations();
        else return instance;
    }


    String validateRegisterationFields(String name,String email,String password,String confirmpassword){
        if (TextUtils.isEmpty(email)){
            return "Please enter email";
        }

        if(TextUtils.isEmpty(name))
        {
            return "Please enter name";
        }
        if (TextUtils.isEmpty(password))
        {
            return "Please enter password";
        }
        if(TextUtils.isEmpty(confirmpassword))
        {
            return "Please confirm the password";
        }


        return null;
    }
    String Validateloginfields(String devicetype)
    { if (TextUtils.isEmpty(devicetype)){
        return "Please enter devicetype";
    }


        return  null;
    }
    String Validateemail(String email)
    {
        if(TextUtils.isEmpty(email))
        {
            return "Please enter email";
        }
        return  null;
    }
}
