package com.example.geofencetest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_workout = (Button) findViewById(R.id.btn_workout);
        Button btn_ranking = (Button) findViewById(R.id.btn_ranking);
        Button btn_analysis = (Button) findViewById(R.id.btn_analysis);

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

        btn_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), proto_analysis.class);
                startActivity(intent);
            }
        });
    }
}