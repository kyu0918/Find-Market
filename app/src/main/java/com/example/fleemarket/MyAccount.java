package com.example.fleemarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyAccount extends Fragment {

    private View view;
    private TextView jId;
    private Context context;
    private ImageView jImage;

    private static final String TAG = "MyAccount";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_my_account, container, false);
        jId = (TextView) view.findViewById(R.id.jId);
        jImage = (ImageView) view.findViewById(R.id.jImage);

        context = container.getContext();


        ImageButton buylistBtn = (ImageButton) view.findViewById(R.id.buy_list_btn);
        ImageButton selllistBtn = (ImageButton) view.findViewById(R.id.sell_list_btn);
        buylistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BuyList.class);
                startActivity(intent);
            }
        });

        selllistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SellList.class);
                startActivity(intent);
            }
        });

        FirebaseUser user = mAuth.getInstance().getCurrentUser();

        mDatabase = FirebaseDatabase.getInstance().getReference("users");
        String uid = user.getUid();

        if (user == null) {
            startMain();
        } else{
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener((task) -> {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document != null){
                        if(document.exists()){
                            jId.setText(document.getString("ID"));
                            Log.d(TAG, "document 스냅샷 정보" + document.getData());
                        } else
                            Log.d(TAG, "document 정보가 없습니다.");
                    }
                } else{
                    Log.d(TAG, "실패하였습니다.", task.getException() );
                }
            });


            Log.d("회원정보 성공", uid);

        }


        return view;
    }

    private void startToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    private  void startMain() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}