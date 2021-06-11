package com.example.fleemarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductInformation extends AppCompatActivity {
    private Intent intent;
    private int number;
    private String title;
    private String content;
    private ImageView imageView;
    private TextView textView, textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);

        intent = getIntent();
        number = intent.getIntExtra("number", -1);
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");


        imageView = findViewById(R.id.product_info_image);
        textView = findViewById(R.id.product_info_name);
        textView1 = findViewById(R.id.product_info_price);

        switch (number){
            case 0:
                imageView.setImageResource(R.drawable.keyboard);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                break;

            case 1:
                imageView.setImageResource(R.drawable.louisvuitton);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                break;

            case 2:
                imageView.setImageResource(R.drawable.ipad);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                break;

            case 3:
                imageView.setImageResource(R.drawable.gifticon);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                break;

            case 4:
                imageView.setImageResource(R.drawable.uniform);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                break;

            case 5:
                imageView.setImageResource(R.drawable.budspro);
                textView.setText("제품명 : "+title);
                textView1.setText("가격 : "+content);
                break;
        }
    }
}