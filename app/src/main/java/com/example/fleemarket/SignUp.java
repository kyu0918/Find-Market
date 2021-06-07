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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private  final int GET_GALLERY_IMAGE = 200;
    private ImageView imageview,signIn;

    private EditText jPw, jPw1;
    private TextView pwdText;

    private FirebaseAuth mAuth;
    private static final String TAG = "SingUp";

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");
    // 이메일 정규식
    public static final Pattern EMAIL_ADDRESS = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    // 전화번호 정규식
    public static final Pattern PHONE_PATTERN = Pattern.compile("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", Pattern.CASE_INSENSITIVE);

    // 파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    // 파이어스토어 인증 객체 생성
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    // 회원정보에 저장할 값 객체 생성
    private String email = "";
    private String password  = "";
    private String passwordCheck  = "";
    private String name = "";
    private String phone  = "";
    private String ID = "";

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
        email =((EditText)findViewById(R.id.emailaddress)).getText().toString();
        password =((EditText)findViewById(R.id.jPw)).getText().toString();
        passwordCheck = ((EditText)findViewById(R.id.jPw1)).getText().toString();
        name = ((EditText)findViewById(R.id.userName)).getText().toString();
        phone = ((EditText)findViewById(R.id.phoneNumber)).getText().toString();
        ID = ((EditText)findViewById(R.id.jId)).getText().toString();



        // 유효성 검사 후 회원가입 실행
        if (isValidEmail() && isValidPasswd() && isValidPasswdcheck() && isValidName() && isValidPhone() && isvalidID()) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser User = firebaseAuth.getCurrentUser();
                                String uid = User.getUid();

                                // 입력한 email, password, name, phone 값을 파이어스토어에 저장
                                Map<String, Object> user = new HashMap<>();
                                user.put("email", email);
                                user.put("password", password);
                                user.put("name", name);
                                user.put("phone", phone);
                                user.put("ID", ID);

                                firebaseFirestore.collection("users").document(uid)
                                        .set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // 정보 저장이 성공적으로 이루어지면 이메일 인증 메일 발송
                                                startToast("회원가입이 완료되었습니다..");
                                                LoginActivity();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // 회원가입에 실패하면 "회원가입 실패" 토스트를 보여줌
                                                startToast("이미 존재하는 E-mail입니다. 다시 입력하여주세요");
                                            }
                                        });

                            } else {
                                startToast("모든 정보를 입력해 주세요.");
                            }


                        }
                    });
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

    // 이메일 유효성 검사
    private boolean isValidEmail() {
        if (email.isEmpty()) {
            // 이메일 칸이 공백이면 false
            startToast("E-maild을 입력해 주세요.");
            return false;
        } else if (!EMAIL_ADDRESS.matcher(email).matches()) {
            // 이메일 형식이 불일치하면 false
            startToast("잘못된 형식입니다. E-mail을 다시 입력해주세요.");
            return false;
        } else {
            return true;
        }
    }


    // 비밀번호 유효성 검사
    private boolean isValidPasswd() {
        if (password.isEmpty()) {
            // 비밀번호 칸이 공백이면 false
            startToast("비밀번호를 입력해 주세요.");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            // 비밀번호 형식이 불일치하면 false
            startToast("비밀번호 형식이 잘못되었습니다. 다시 입력해 주세요.");
            return false;
        } else {
            return true;
        }
    }

    // 비밀번호 체크 유효성 검사
    private boolean isValidPasswdcheck() {
        if (passwordCheck.isEmpty()) {
            // 비밀번호 칸이 공백이면 false
            startToast("비밀번호 확인을 입력해 주세요.");
            return false;
        } else if (!password.equals(passwordCheck)) {
            // 비밀번호와 비밀번호 확인에 입력한 값이 불일치하면 false
            startToast("잘못된 형식입니다. 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
            return false;
        } else {
            return true;
        }
    }

    // 이름 유효성 검사
    private boolean isValidName() {
        if (name.isEmpty()) {
            // 이름 칸이 공백이면 false
            startToast("이름을 입력해주세요.");
            return false;
        } else {
            return true;
        }
    }

    // 전화번호 유효성 검사
    private boolean isValidPhone() {
        if (phone.isEmpty()) {
            // 전화번호 칸이 공백이면 false
            startToast("전화번호를 입력해주세요.");
            return false;
        } else if (!PHONE_PATTERN.matcher(phone).matches()) {
            startToast("잘못된 형식입니다. 전화번호를 다시 입력해주세요.");
            return false;
        } else {
            return true;
        }
    }

    // 주소 유효성 검사
    private boolean isvalidID() {
        if (ID.isEmpty()) {
            // 이름 칸이 공백이면 false
            startToast("닉네임을 입력해주세요.");
            return false;
        } else {
            return true;
        }
    }

}