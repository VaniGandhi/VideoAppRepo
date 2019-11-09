package com.example.myapplication.VideoPlayer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.Roomdatabase.User;

import java.util.List;

@Dao
public interface VideoDao {
    @Insert
    public  void addvideo(VideoModel videoModel);

    @Query("select * from video")
    public List<VideoModel> getVideos();


    @Query("DELETE FROM video")
    void delete();

    @Query("DELETE FROM video WHERE name=:videoname")
    void delete(String videoname );


}
