package com.example.myapplication.ChatApp;

public class InboxModel {

    private String chatid;
    private String lastmessage;
    private ReceiverDetails reciever;
    private String userid;

    public InboxModel() {

    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLastmessage() {
        return lastmessage;
    }

    public void setLastmessage(String lastmessage) {
        this.lastmessage = lastmessage;
    }


    public ReceiverDetails getReciever() {
        return reciever;
    }

    public void setReciever(ReceiverDetails reciever) {
        this.reciever = reciever;
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }


    public static class ReceiverDetails {

        String email;
        String imgurl;
        String uid;
        String name;

        public ReceiverDetails() {

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


        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }


}
