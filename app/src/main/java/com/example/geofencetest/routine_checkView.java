package com.example.geofencetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewKt;
import androidx.core.view.WindowInsetsCompat;

public class routine_checkView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_routine_check_view);

        Button btn_routineFin = (Button) findViewById(R.id.btn_routineFin);
        Button restTimer = (Button) findViewById(R.id.restTimer);


        // 루틴완료 버튼
        btn_routineFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), complete_todayWorkout.class);
                startActivity(intent);
            }
        });

        // 휴식타이머 -> timer.java 여기에 작성하면 됨
        restTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), timer.class);
                startActivity(intent);
            }
        });


    }
}