package com.example.fleemarket;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductRegister extends AppCompatActivity {



    private  final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview;
    private ImageButton product_register;

    private FirebaseAuth mAuth;

    FirebaseAuth auth= FirebaseAuth.getInstance();

    FirebaseUser user=auth.getCurrentUser();

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private String pName = "";
    private String pPrice  = "";

    private ArrayList<String> pathList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_register);

        mAuth=FirebaseAuth.getInstance();

        imageview = (ImageView)findViewById(R.id.product_image);
        product_register = (ImageButton)findViewById(R.id.product_register);

        //이미지
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                startActivityForResult(intent,GET_GALLERY_IMAGE);
            }
        });

        ActionBar ab = getSupportActionBar();
        ab.hide();

        product_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.product_register:
                }
                productRegister();
                Intent intent = new Intent(getApplicationContext(),MainFrame.class);
            }
        });


    }//onCreate


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){

            Uri selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri);
        }
    }


    private void productRegister(){

        String pName =((EditText)findViewById(R.id.product_name)).getText().toString();
        String pPrice =((EditText)findViewById(R.id.product_price)).getText().toString();

        if(pName.length()>0 && pPrice.length()>0){
            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            ProductInfo productInfo = new ProductInfo(pName, pPrice);

            if(user!=null) {
                db.collection("product").document(pName).set(productInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startToast("상품이 등록되었습니다.");
                        Intent intent = new Intent(getApplicationContext(),MainFrame.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        startToast("상품 등록에 실패하였습니다. 빈 칸을 확인해주세요.");
                    }
                });


            }
        }


        final FirebaseFirestore db= FirebaseFirestore.getInstance();

        Map<String, Object> product=new HashMap<>();

        if(isValidPName() && isValidPPrice()){
        db.collection("product").add(product).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                product.put("Name",pName);
                product.put("Price",pPrice);

                startToast("상품 등록이 완료되었습니다.");
            }
        });
        }
    }


    private boolean isValidPName() {
        if (pName.isEmpty()) {
            startToast("상품의 이름을 입력해주세요.");
            return false;
        }  else {
            return true;
        }
    }

    private boolean isValidPPrice() {
        if (pPrice.isEmpty()) {
            startToast("상품의 가격을 입력해주세요.");
            return false;
        }  else {
            return true;
        }
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}//public class ProductRegister extends AppCompatActivity


