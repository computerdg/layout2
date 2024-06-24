package com.example.geofencetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class start_Routine extends Activity {

    private TextView tvRoutineDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_routine);

        tvRoutineDetails = findViewById(R.id.tv_routine_details);

        Intent intent = getIntent();
        ArrayList<AddExerciseActivity.Exercise> routineExercises = intent.getParcelableArrayListExtra("routineExercises");
        if (routineExercises != null) {
            displayRoutine(routineExercises);
        }
    }

    private void displayRoutine(ArrayList<AddExerciseActivity.Exercise> exercises) {
        StringBuilder details = new StringBuilder();
        for (AddExerciseActivity.Exercise exercise : exercises) {
            details.append(exercise.getName())
                    .append(" - ")
                    .append(exercise.getWeight())
                    .append("kg x ")
                    .append(exercise.getReps())
                    .append("회\n");
        }
        tvRoutineDetails.setText(details.toString());
    }
}
