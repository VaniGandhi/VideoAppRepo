package com.example.myapplication.Roomdatabase;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;





@Entity(tableName = "login")
public class User {
   @PrimaryKey(autoGenerate = true)
   @ColumnInfo(name="id")
    public int id;
    @ColumnInfo(name="user_name")
    public  String name;
    @ColumnInfo(name="user_email")
    public String useremail;
    @ColumnInfo(name="user_password")
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String username;

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword(String password) {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





}
