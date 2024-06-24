package com.example.geofencetest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.geofencetest.community.CommunityActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_workout = (Button) findViewById(R.id.btn_workout);
        Button btn_ranking = (Button) findViewById(R.id.btn_ranking);
        Button btn_analysis = (Button) findViewById(R.id.btn_analysis);
        Button bnt_community = (Button) findViewById(R.id.btn_community);

        Button btn_workout_log  = (Button) findViewById(R.id.btn_workout_log);
        // 운동
        btn_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), challList_proto.class);
                startActivity(intent);
            }
        });

        // 랭킹
        btn_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ranking.class);
                startActivity(intent);
            }
        });


        //분석
        btn_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), proto_analysisList.class);
                startActivity(intent);
            }
        });

        btn_workout_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExerciseDiaryActivity.class);
                startActivity(intent);
            }
        });

        bnt_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommunityActivity.class);
                startActivity(intent);
            }
        });
    }
}