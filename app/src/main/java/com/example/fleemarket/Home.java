package com.example.fleemarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.List;

public class Home extends Fragment {

    private View view;
    private HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.activity_home, container, false);

        ImageButton productRegister = (ImageButton) view.findViewById(R.id.productRegister);
        productRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ProductRegister.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView1);

        // recyclerView1의  layoutManger 형식을 지정한다. Grid형식도 설정가능하다.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView1.setLayoutManager(linearLayoutManager);

        adapter = new HomeAdapter();
        recyclerView1.setAdapter(adapter);

        getData();

        return view;



    } //onCreateView
    private void getData() {
        List<String> titleList = Arrays.asList("바밀로 저소음적축","루이비통 가방","아이패드 11인치","뿌링클 기프티콘","첼시 유니폼","버즈 프로");
        List<String> contentList = Arrays.asList("230 XRP.","540 XRP.","785 XRP.","15 XRP","35 XRP","240 XRP");
        List<Integer> rsIdList = Arrays.asList(
                R.drawable.keyboard,R.drawable.louisvuitton,
                R.drawable.ipad,R.drawable.gifticon,R.drawable.uniform,R.drawable.sellbtn
        );

        for (int i=0;i<6;i++) {
            HomeData data = new HomeData();
            data.setTitle(titleList.get(i));
            data.setContent(contentList.get(i));
            data.setResId(rsIdList.get(i));

            // adapter에 방금 만든 Data 객체를 추가해 넣는다.
            adapter.addItem(data);
        }

        // adapter 내용의 값이 변경되었음을 알려준다. 이 함수를 쓰지않으면 data가 노출안된다.
        // 다만, recyclerView1.setAdapter() 함수가 data를 추가시켜준 뒤에 호출되었다면 정상적으로  data 노출된다.
        adapter.notifyDataSetChanged();
    }
}


