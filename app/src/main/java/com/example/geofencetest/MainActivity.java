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
        Button btn_analysis = (Button) findViewById(R.id.btn_analysis);

        btn_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), challList_proto.class);
                startActivity(intent);
            }


        });

        btn_analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), chall_list.class);
                startActivity(intent);
            }


        });
    }
}