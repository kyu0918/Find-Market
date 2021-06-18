package com.example.fleemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.auth.User;

public class ProductInformation extends AppCompatActivity {
    private Intent intent;
    private int number;
    private String title;
    private String content;
    private ImageView imageView;
    private ImageButton sendMsg;
    private TextView textView, textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);

        sendMsg = (ImageButton)findViewById(R.id.send_msg_btn);


        intent = getIntent();
        number = intent.getIntExtra("number", -1);
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");


        imageView = findViewById(R.id.product_info_image);
        textView = findViewById(R.id.product_info_name);
        textView1 = findViewById(R.id.product_info_price);
        textView2 = findViewById(R.id.product_info_address);

        switch (number){
            case 0:
                imageView.setImageResource(R.drawable.keyboard);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                textView2.setText("주소 : 서울특별시 강남구 테헤란로 101(역삼동)");

                break;

            case 1:
                imageView.setImageResource(R.drawable.louisvuitton);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                textView2.setText("주소 : 경기도 과천시 별양로 11(원문동, 과천 위버필드)");
                break;

            case 2:
                imageView.setImageResource(R.drawable.ipad);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                textView2.setText("주소 : 경기도 수원시 권선구 매탄로 11(권선동, 현대아파트)");
                break;

            case 3:
                imageView.setImageResource(R.drawable.gifticon);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                textView2.setText("주소 : 서울특별시 성동구 자동차시장1길 70(용답동)");
                break;

            case 4:
                imageView.setImageResource(R.drawable.uniform);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                textView2.setText("주소 : 경기도 안양시 동안구 인덕원로 1(관양동)");
                break;

            case 5:
                imageView.setImageResource(R.drawable.budspro);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                textView2.setText("주소 : 인천광역시 미추홀구 미추홀대로 708(주안동)");
                break;
        }

        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserChat.class);
                startActivity(intent);
            }
        });
    } //onCreate
}