package com.example.myapplication.ChatApp;

public class UserChatApp {

    String name;
    String email;
    String imgurl;
    String username;
    String uid;

    public UserChatApp() {

    }

    public UserChatApp(String uid, String name, String email,String imgurl) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.imgurl=imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
