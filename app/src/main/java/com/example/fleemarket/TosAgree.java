package com.example.fleemarket;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TosAgree extends AppCompatActivity {

    CheckBox agreeall,agree1,agree2;
    TextView tosagree1,tosagree2;
    ImageButton next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tos_agree);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        agreeall = (CheckBox)findViewById(R.id.agreeall);
        agree1 = (CheckBox)findViewById(R.id.agree1);
        agree2 = (CheckBox)findViewById(R.id.agree2);

        next = (ImageButton)findViewById(R.id.next);

        tosagree1 = (TextView)findViewById(R.id.tosagree1);
        tosagree2 = (TextView)findViewById(R.id.tosagree2);

        tosagree1.setMovementMethod(new ScrollingMovementMethod());
        tosagree2.setMovementMethod(new ScrollingMovementMethod());

        agreeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agreeall.isChecked()){
                    agree1.setChecked(true);
                    agree2.setChecked(true);
                }
                else{
                    agree1.setChecked(false);
                    agree2.setChecked(false);
                }

            }
        });

        agree1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agreeall.isChecked()){
                    agreeall.setChecked(false);
                }
                else if(agree1.isChecked()&&agree2.isChecked()){
                    agreeall.setChecked(true);
                }
            }
        });

        agree2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agreeall.isChecked()){
                    agreeall.setChecked(false);
                }
                else if(agree1.isChecked()&&agree2.isChecked()){
                    agreeall.setChecked(true);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agreeall.isChecked()){
                    Intent intent = new Intent(getApplicationContext(),SignUp.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "전체약관에 동의해 주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}