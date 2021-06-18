package com.example.fleemarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chat extends Fragment {

    private View view;
    private CustomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_chat, container, false);
        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView_chat);

        // recyclerView1의  layoutManger 형식을 지정한다. Grid형식도 설정가능하다.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(linearLayoutManager);

        adapter = new CustomAdapter();
        recyclerView1.setAdapter(adapter);

        getData();

        return view;



    } //onCreateView
    private void getData() {
        List<String> titleList = Arrays.asList("tjdydwns123","별양동핵빤치","sia98");
        List<String> contentList = Arrays.asList("안녕하세요","채팅이 시작됨.","네고 될까요~~?");
        List<Integer> rsIdList = Arrays.asList(
                R.drawable.keyboard,R.drawable.louisvuitton, R.drawable.ipad);

        for (int i=0;i<3;i++) {
            ChatData data = new ChatData();
            data.setTitle(titleList.get(i));
            data.setContent(contentList.get(i));
            data.setResId(rsIdList.get(i));

            // adapter에 방금 만든 Data 객체를 추가해 넣는다.
            adapter.addItem(data);
        }

        // adapter 내용의 값이 변경되었음을 알려준다. 이 함수를 쓰지않으면 data가 노출안된다.
        // 다만, recyclerView1.setAdapter() 함수가 data를 추가시켜준 뒤에 호출되었다면 정상적으로  data 노출된다.
        adapter.notifyDataSetChanged();
    }
}