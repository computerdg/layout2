package com.example.geofencetest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import java.util.Set;

public class FlipperActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challflip);

        viewPager = findViewById(R.id.viewPager);

        int[] layouts = {
                R.layout.activity_start_chall,
                R.layout.activity_chall_info,
                R.layout.activity_routine_info
        };

        pagerAdapter = new MyPagerAdapter(this, layouts);
        viewPager.setAdapter(pagerAdapter);
    }

    public void setupStartChall(View view) {
        TextView tvRoutineDetails = view.findViewById(R.id.tv_routine_details);
        Button btnStartRoutine = view.findViewById(R.id.btn_startRoutine);

        SharedPreferences sharedPreferences = getSharedPreferences("RoutinePreferences", Context.MODE_PRIVATE);
        Set<String> exerciseSet = sharedPreferences.getStringSet("routineExercises", null);
        if (exerciseSet != null) {
            StringBuilder details = new StringBuilder();
            for (String exercise : exerciseSet) {
                String[] parts = exercise.split(",");
                if (parts.length == 3) {
                    details.append(parts[0])
                            .append(" - ")
                            .append(parts[1])
                            .append("회 x ")
                            .append(parts[2])
                            .append("세트\n");
                }
            }
            tvRoutineDetails.setText(details.toString());
        }

        btnStartRoutine.setOnClickListener(v -> {
            Intent intent = new Intent(FlipperActivity.this, check_currentLocation.class);
            startActivity(intent);
        });
    }
}
