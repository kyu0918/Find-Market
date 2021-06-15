package com.example.fleemarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class SellList extends AppCompatActivity {

    private SellListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_list);

        RecyclerView recyclerView_sell = this.findViewById(R.id.recyclerView_sell);

        // recyclerView1의  layoutManger 형식을 지정한다. Grid형식도 설정가능하다.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_sell.setLayoutManager(linearLayoutManager);

        adapter = new SellListAdapter();
        recyclerView_sell.setAdapter(adapter);

        getData();
    }

    private void getData() {
        List<String> titleList = Arrays.asList("바밀로 저소음적축","루이비통 가방","아이패드 11인치","뿌링클 기프티콘","첼시 유니폼","버즈 프로");
        List<String> contentList = Arrays.asList("230 XRP.","540 XRP.","785 XRP.","15 XRP","35 XRP","240 XRP");
        List<Integer> rsIdList = Arrays.asList(
                R.drawable.keyboard,R.drawable.louisvuitton,
                R.drawable.ipad,R.drawable.gifticon,R.drawable.uniform,R.drawable.budspro
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