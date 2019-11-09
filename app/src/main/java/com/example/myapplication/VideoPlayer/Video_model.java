package com.example.myapplication.VideoPlayer;

import android.graphics.Bitmap;

public class Video_model {

    private String video;
    private Bitmap thumbnail;
    private int id;


    private String name;



    public Video_model(String video, Bitmap thumbnail) {
        this.video = video;
        this.thumbnail = thumbnail;
    }

    public  void VideoModel()

    {

    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
