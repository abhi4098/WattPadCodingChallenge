package com.example.wattpadcodingchallenge.db;

import androidx.room.Entity;

import androidx.room.PrimaryKey;


@Entity
public class StoryModel {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private String title;
    private String  userName;
    private String  userAvatar;
    private String cover;


    public StoryModel( String title, String  userName,String  userAvatar, String cover) {

        this.title = title;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
