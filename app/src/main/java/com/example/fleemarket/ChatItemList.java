package com.example.fleemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ChatItemList extends AppCompatActivity {

    String nickname;
    String lastChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_item_list);
    }
}