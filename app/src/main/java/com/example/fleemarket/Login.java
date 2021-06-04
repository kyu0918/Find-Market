package com.example.fleemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    ImageButton login;

    private FirebaseAuth mAuth;
    private static final String TAG = "Loding";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        mAuth = FirebaseAuth.getInstance();

        login = (ImageButton)findViewById(R.id.login1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    } //onCreat()

    private void Login() {
        String email = ((EditText) findViewById(R.id.edtId)).getText().toString();
        String password = ((EditText) findViewById(R.id.edtPw)).getText().toString();

        if (email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인 되었습니다..");
                                startCourierPage();
                            } else {
                                if(task.getException() != null){
                                    startToast("잘못입력하셨습니다 다시 입력하여주세요");
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

    private  void startCourierPage(){        Intent intent = new Intent(this, MainFrame.class);
        startActivity(intent);
        finish();
    }
}