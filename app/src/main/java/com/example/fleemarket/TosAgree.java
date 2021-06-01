package com.example.fleemarket;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

public class TosAgree extends AppCompatActivity {

    CheckBox agreeall,agree1,agree2;
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

    }
}