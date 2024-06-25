package com.example.geofencetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.Set;

public class start_chall extends Activity {

    private TextView tvRoutineDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_chall);

        tvRoutineDetails = findViewById(R.id.tv_routine_details);

        loadRoutineExercisesFromPreferences();

        Button btn_startRoutine = findViewById(R.id.btn_startRoutine);

        // 운동 시작하기 버튼
        btn_startRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), check_currentLocation.class);
                startActivity(intent);
            }
        });
    }

    private void loadRoutineExercisesFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("RoutinePreferences", Context.MODE_PRIVATE);
        Set<String> exerciseSet = sharedPreferences.getStringSet("routineExercises", null);
        if (exerciseSet != null) {
            displayRoutine(exerciseSet);
        }
    }

    private void displayRoutine(Set<String> exercises) {
        StringBuilder details = new StringBuilder();
        for (String exercise : exercises) {
            String[] parts = exercise.split(",");
            if (parts.length == 3) {
                details.append(parts[0])
                        .append(" - ")
                        .append(parts[1])
                        .append("kg x ")
                        .append(parts[2])
                        .append("회\n");
            }
        }
        tvRoutineDetails.setText(details.toString());
    }
}
