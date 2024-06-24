package com.example.geofencetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class set_routine extends Activity {

    private LinearLayout mondayExerciseLayout, wednesdayExerciseLayout, fridayExerciseLayout;
    private Button btnComplete;
    private ArrayList<AddExerciseActivity.Exercise> routineExercises;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_routine);

        mondayExerciseLayout = findViewById(R.id.mondayExerciseLayout);
        wednesdayExerciseLayout = findViewById(R.id.wednesdayExerciseLayout);
        fridayExerciseLayout = findViewById(R.id.fridayExerciseLayout);
        btnComplete = findViewById(R.id.btn_complete);

        routineExercises = new ArrayList<>();

        findViewById(R.id.addMondayExercise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExerciseForDay(1);
            }
        });

        findViewById(R.id.addWednesdayExercise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExerciseForDay(3);
            }
        });

        findViewById(R.id.addFridayExercise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExerciseForDay(5);
            }
        });

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(set_routine.this, start_chall.class);
                intent.putParcelableArrayListExtra("routineExercises", routineExercises);
                startActivity(intent);
            }
        });
    }

    private void addExerciseForDay(int day) {
        Intent intent = new Intent(this, AddExerciseActivity.class);
        startActivityForResult(intent, day);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            ArrayList<AddExerciseActivity.Exercise> exercises = data.getParcelableArrayListExtra("selectedExercises");
            if (exercises != null) {
                routineExercises.addAll(exercises);
                displayExercises(exercises, requestCode);
            }
        }
    }

    private void displayExercises(ArrayList<AddExerciseActivity.Exercise> exercises, int day) {
        LinearLayout layout = null;
        switch (day) {
            case 1:
                layout = mondayExerciseLayout;
                break;
            case 3:
                layout = wednesdayExerciseLayout;
                break;
            case 5:
                layout = fridayExerciseLayout;
                break;
        }
        if (layout != null) {
            for (AddExerciseActivity.Exercise exercise : exercises) {
                TextView textView = new TextView(this);
                textView.setText(exercise.getName() + " - " + exercise.getWeight() + "kg x " + exercise.getReps() + "회");
                layout.addView(textView);
            }
        }
    }
}
