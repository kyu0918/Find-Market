package com.example.fleemarket;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ProductRegister extends AppCompatActivity {

    private  final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    private ImageButton product_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_register);

        imageview = (ImageView)findViewById(R.id.product_image);
        product_register = (ImageButton)findViewById(R.id.product_register);

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,GET_GALLERY_IMAGE);
            }
        });

        product_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "상품등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MainFrame.class);
                startActivity(intent);
            }
        });

        ActionBar ab = getSupportActionBar();
        ab.hide();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){

            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
        }
    }
}