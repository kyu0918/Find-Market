package com.example.fleemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChatData {

    private String title;
    private String content;
    private int resId;
    private int userImg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getUserImg() {return userImg;}

    public void setUserImg(int userImg) {this.userImg = userImg;}
}