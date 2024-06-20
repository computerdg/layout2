package com.example.geofencetest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class start_Routine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_routine);

        // 버튼 설정
        Button startRoutineButton = findViewById(R.id.btn_startRoutine);
        startRoutineButton.setOnClickListener(v -> {
            Intent intent = new Intent(start_Routine.this, check_currentLocation.class);
            startActivity(intent);
        });
    }
}
