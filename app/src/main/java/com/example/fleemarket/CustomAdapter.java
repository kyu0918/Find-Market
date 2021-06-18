package com.example.fleemarket;

import android.content.Intent;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    ArrayList<ChatData> dataList = new ArrayList<>();

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // item.xml 을 parent ViewGroup 위에 Inflate 시켜 새로운 View를 하나 만든다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_item_list,parent,false);

        // 그리고, 이 view를 바탕으로 ItemViewHolder 객체 생성
        return new CustomViewHolder(view);
    }

    // ViewHolder 에 각각의 항목들을 바인딩시킨다.
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int position) {
        customViewHolder.onBind(dataList.get(position));
//        itemViewHolder.imageView1.setImageResource(dataList.get(position).getResId());
//        itemViewHolder.textView1.setText(dataList.get(position).getTitle());

        customViewHolder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UserChat.class);
                intent.putExtra("number", position);
                intent.putExtra("title", dataList.get(position).getTitle());
                intent.putExtra("content", dataList.get(position).getContent());
                intent.putExtra("userImg", position);
                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "채팅방에 접속하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // Data 객체(아이템) 을 하나씩 추가시킨다.
    public void addItem(ChatData data) {
        dataList.add(data);
    }


    // RecyclerView 의 ViewHolder 만든다.
    class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private ImageView imageView1;
        private ImageView imageView2;

        CustomViewHolder(@NonNull View customView) {
            super(customView);

            textView1 = customView.findViewById(R.id.chat_user_name);
            textView2 = customView.findViewById(R.id.chat_message);
            imageView1 = customView.findViewById(R.id.chat_user_image);
            imageView2 = customView.findViewById(R.id.chat_product_image);

        }

        // 실제 데이터들을 1:1 대응하여 각각의 내부뷰에 바인딩시킨다.
        void onBind(ChatData data) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            imageView1.setImageResource(data.getUserImg());
            imageView2.setImageResource(data.getResId());

        }
    }
}
