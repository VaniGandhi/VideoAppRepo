package com.example.myapplication.ChatApp.Notifications;

import com.google.gson.annotations.SerializedName;

public class Data_model  {
   @SerializedName("user")
    private  String user;
   @SerializedName("icon")
    private int icon;
   @SerializedName("body")
    private  String body;
   @SerializedName("title")
    private  String title;
   @SerializedName("sent")
    private  String sent;
   @SerializedName("name")
    private String name;




    public Data_model(String user, int icon, String body, String title, String sent) {
        this.user = user;
        this.icon = icon;
        this.body = body;
        this.title = title;
        this.sent = sent;
    }
    public Data_model()
    {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
