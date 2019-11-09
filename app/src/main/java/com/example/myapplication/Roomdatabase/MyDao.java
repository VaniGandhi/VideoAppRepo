package com.example.myapplication.Roomdatabase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public  void adduser(User user);

    @Query("select * from login")
    public List<User> getUsers();

    @Delete
    void deleteuser(User user);








}
