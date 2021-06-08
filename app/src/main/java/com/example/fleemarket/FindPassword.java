package com.example.fleemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FindPassword extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.send_reset_mail).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = (v) -> {
        switch(v.getId()){
            case  R.id.send_reset_mail :
                reset();
                break;
        }
    };

    private void reset(){
        String email = ((EditText)findViewById(R.id.resetMail)).getText().toString();


        if(email.length() > 0){
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startToast("이메일을 보냈습니다.");
                                LoginActivity();
                            }
                        }
                    });
        }else{
            startToast("이메일을 입력해주세요");
        }
    }
    private  void LoginActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}