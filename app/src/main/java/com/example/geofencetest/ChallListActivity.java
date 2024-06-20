package com.example.geofencetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChallListActivity extends AppCompatActivity {

    private ListView challListView;
    private ArrayList<Challenge> challList;
    private ChallengeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chall_list);

        challListView = findViewById(R.id.chall_list_view);
        Button createChallButton = findViewById(R.id.btn_create_chall);

        // 샘플 데이터
        challList = new ArrayList<>();
        challList.add(new Challenge("챌린지 제목 1", "진행중"));
        challList.add(new Challenge("챌린지 제목 2", "진행 완료"));

        adapter = new ChallengeAdapter(this, challList);
        challListView.setAdapter(adapter);

        createChallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 챌린지 생성 액티비티로 이동
                Intent intent = new Intent(getApplicationContext(), create_chall.class);
                startActivity(intent);
            }
        });

        challListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 리스트 아이템 클릭 처리
                Challenge selectedChall = challList.get(position);
                Toast.makeText(ChallListActivity.this, selectedChall.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
