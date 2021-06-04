package com.example.fleemarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    private  final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview,signIn;

    private EditText jPw, jPw1;
    private TextView pwdText;

    private FirebaseAuth mAuth;
    private static final String TAG = "SingUp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        jPw = (EditText)findViewById(R.id.jPw);
        jPw1 = (EditText)findViewById(R.id.jPw1);
        pwdText = (TextView)findViewById(R.id.pwdText);

        imageview=(ImageView)findViewById(R.id.user_image);
        signIn = (ImageButton)findViewById(R.id.sign_in);

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


        // 비밀번호 확인

        jPw1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {

                if (jPw1.getText().toString().equals(jPw.getText().toString())){
                    pwdText.setText("일치합니다.");
                    pwdText.setTextColor(0xAA1e6de0);
                } else{
                    pwdText.setTextColor(0xAAef484a);
                    pwdText.setText("일치하지 않습니다.");

                }
            }
        });



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    } // onCreate()

    private  void signUp(){
        String email = ((EditText)findViewById(R.id.emailaddress)).getText().toString();
        String password = ((EditText)findViewById(R.id.jPw)).getText().toString();

        if(email.length() > 0 && password.length() > 0 ) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //UI
                                startToast("회원가입이 완료되었습니다..");
                                LoginActivity();
                                finish();
                            } else {
                                if(task.getException() != null){
                                    startToast(task.getException().toString());
                                }
                            }
                        }
                    });
        }else {
            startToast("이메일 또는 비밀번호를 입력해 주세요.");
        }
    }
    // 토스트 메시지
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    // 회원가입 완료후 메인페이지로 이동
    private  void LoginActivity(){
        Intent intent = new Intent(getApplicationContext(),Login.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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