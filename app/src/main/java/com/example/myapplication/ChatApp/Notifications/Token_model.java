package com.example.myapplication.ChatApp.Notifications;

import com.google.gson.annotations.SerializedName;

public class Token_model {

    @SerializedName("Token")
    private String Token;

    public Token_model(String token)
    {
        this.Token=token;
    }
    public Token_model()
    {

    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
