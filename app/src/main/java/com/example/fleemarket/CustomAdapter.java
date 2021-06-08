package com.example.fleemarket;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<Dictionary> mList;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView chatUserName;
        protected TextView chatMessage;
        protected ImageView chatUserImage;
        protected ImageView chatProductImage;


        public CustomViewHolder(View view) {
            super(view);
            this.chatUserName = (TextView) view.findViewById(R.id.chat_user_name);
            this.chatMessage = (TextView) view.findViewById(R.id.chat_message);
            this.chatUserImage = (ImageView) view.findViewById(R.id.chat_user_image);
            this.chatProductImage = (ImageView) view.findViewById(R.id.chat_product_image);
        }
    }


    public CustomAdapter(ArrayList<Dictionary> list) {
        this.mList = list;
    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_chat_item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.chatUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.chatMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);


        viewholder.chatUserName.setGravity(Gravity.CENTER);
        viewholder.chatMessage.setGravity(Gravity.CENTER);



        viewholder.chatUserName.setText(mList.get(position).getuserName());
        viewholder.chatMessage.setText(mList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}