package com.example.fleemarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFrame extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Home home;
    private Interest interest;
    private Chat chat;
    private MyAccount myAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        setFreg(0);
                        break;
                    case R.id.interest:
                        setFreg(1);
                        break;
                    case R.id.chat:
                        setFreg(2);
                        break;
                    case R.id.my_account:
                        setFreg(3);
                        break;
                }
                return true;
            }
        });

        home = new Home();
        interest = new Interest();
        chat = new Chat();
        myAccount = new MyAccount();

        setFreg(0); //첫화면 설정
    }

    // 프레그먼트 교체가 일어나는 메서드
    private void setFreg(int n){

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        switch (n){
            case 0:
            transaction.replace(R.id.main_frame, home);
            transaction.commit();
            break;
            case 1:
                transaction.replace(R.id.main_frame, interest);
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.main_frame, chat);
                transaction.commit();
                break;
            case 3:
                transaction.replace(R.id.main_frame, myAccount);
                transaction.commit();
                break;
        }
    }
}