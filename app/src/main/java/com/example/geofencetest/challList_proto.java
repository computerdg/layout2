package com.example.geofencetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class challList_proto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chall_list_proto);


        Button btn_makeChall = (Button) findViewById(R.id.btn_makeChall);
        Button btn_challInfo = (Button) findViewById(R.id.btn_challInfo);


        // 챌린지 생성 버튼
        btn_makeChall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), create_chall.class);
                startActivity(intent);
            }
        });


        // 다이어트 챌린지 -> 챌린지 정보 볼수있게함
        btn_challInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 챌린지 생성에서 지정한 챌린지 제목이 textview에 뜨도록 해야됨
                Intent intent = new Intent(getApplicationContext(), FlipperActivity.class); // 여기에 챌린지 정보로 넘겨줌
                startActivity(intent);
            }
        });

    }
}