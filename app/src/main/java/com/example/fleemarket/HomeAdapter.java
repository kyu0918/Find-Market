package com.example.fleemarket;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ItemViewHolder> {

    ArrayList<HomeData> dataList = new ArrayList<HomeData>();


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // item.xml 을 parent ViewGroup 위에 Inflate 시켜 새로운 View를 하나 만든다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_item_list,parent,false);

        // 그리고, 이 view를 바탕으로 ItemViewHolder 객체 생성
        return new ItemViewHolder(view);
    }

    // ViewHolder 에 각각의 항목들을 바인딩시킨다.
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {
        itemViewHolder.onBind(dataList.get(position));
//        itemViewHolder.imageView1.setImageResource(dataList.get(position).getResId());
//        itemViewHolder.textView1.setText(dataList.get(position).getTitle());



        itemViewHolder.item_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductInformation.class);
                intent.putExtra("number", position);
                intent.putExtra("title", dataList.get(position).getTitle());
                intent.putExtra("content", dataList.get(position).getContent());
                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "클릭 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // Data 객체(아이템) 을 하나씩 추가시킨다.
    public void addItem(HomeData data) {
        dataList.add(data);
    }


    // RecyclerView 의 ViewHolder 만든다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;
        private TextView textView2;
        private ImageView imageView1;
        private LinearLayout item_list;
        private ImageButton like_btn;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            item_list = itemView.findViewById(R.id.item_list);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView1 = itemView.findViewById(R.id.imageView1);
            like_btn = itemView.findViewById(R.id.like_btn);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(v.getContext(), ProductInformation.class);
//                    v.getContext().startActivity(intent);
//                }
//            });
            like_btn.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        like_btn.setBackgroundColor(Color.RED);
                    }
                    else if (event.getAction() == MotionEvent.ACTION_UP) {
                        like_btn.setBackgroundColor(Color.RED);
                    }
                    return false;
                }
            });

        }

        // 실제 데이터들을 1:1 대응하여 각각의 내부뷰에 바인딩시킨다.
        void onBind(HomeData data) {
            textView1.setText(data.getTitle());
            textView2.setText(data.getContent());
            imageView1.setImageResource(data.getResId());

        }
    }
}
