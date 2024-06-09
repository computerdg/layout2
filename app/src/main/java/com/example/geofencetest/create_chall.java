package com.example.geofencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class create_chall extends AppCompatActivity {

    private static final int REQUEST_CODE_SET_LOCATION = 1;
    private EditText etChallengeTitle;
    private Spinner spinnerGoal;
    private EditText etChallengeContent;
    private Spinner spinnerStartYear, spinnerStartMonth, spinnerStartDay;
    private Spinner spinnerEndYear, spinnerEndMonth, spinnerEndDay;
    private Button btnComplete;
    private ImageButton set_location;
    private TextView gym_addr;
    private double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chall);

        etChallengeTitle = findViewById(R.id.challName);
        spinnerGoal = findViewById(R.id.set_goal);
        etChallengeContent = findViewById(R.id.challStory);
        spinnerStartYear = findViewById(R.id.spinner_start_year);
        spinnerStartMonth = findViewById(R.id.spinner_start_month);
        spinnerStartDay = findViewById(R.id.spinner_start_day);
        spinnerEndYear = findViewById(R.id.spinner_end_year);
        spinnerEndMonth = findViewById(R.id.spinner_end_month);
        spinnerEndDay = findViewById(R.id.spinner_end_day);
        btnComplete = findViewById(R.id.btn_createChall_complete);
        set_location = findViewById(R.id.set_location);
        gym_addr = findViewById(R.id.gym_addr);

        // 운동 목표 스피너 설정
        ArrayAdapter<CharSequence> goalAdapter = ArrayAdapter.createFromResource(this,
                R.array.goal_array, android.R.layout.simple_spinner_item);
        goalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGoal.setAdapter(goalAdapter);

        // 헬스장 위치 설정
        set_location.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), set_location.class);
            startActivityForResult(intent, REQUEST_CODE_SET_LOCATION);
        });

        // 년, 월, 일 스피너 설정
        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generateYearArray());
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStartYear.setAdapter(yearAdapter);
        spinnerEndYear.setAdapter(yearAdapter);

        ArrayAdapter<Integer> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generateMonthArray());
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStartMonth.setAdapter(monthAdapter);
        spinnerEndMonth.setAdapter(monthAdapter);

        ArrayAdapter<Integer> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generateDayArray());
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStartDay.setAdapter(dayAdapter);
        spinnerEndDay.setAdapter(dayAdapter);

        // 생성 완료 버튼 클릭 리스너 설정
        btnComplete.setOnClickListener(view -> {
            String title = etChallengeTitle.getText().toString();
            String content = etChallengeContent.getText().toString();
            String goal = spinnerGoal.getSelectedItem().toString();
            String startDate = spinnerStartYear.getSelectedItem().toString() + "-" +
                    spinnerStartMonth.getSelectedItem().toString() + "-" +
                    spinnerStartDay.getSelectedItem().toString();
            String endDate = spinnerEndYear.getSelectedItem().toString() + "-" +
                    spinnerEndMonth.getSelectedItem().toString() + "-" +
                    spinnerEndDay.getSelectedItem().toString();

            Toast.makeText(this, "챌린지 생성 완료\n" +
                    "제목: " + title + "\n" +
                    "목표: " + goal + "\n" +
                    "내용: " + content + "\n" +
                    "시작 날짜: " + startDate + "\n" +
                    "종료 날짜: " + endDate, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(create_chall.this, start_Routine.class);
            startActivity(intent);
            finish();
        });
    }

    private Integer[] generateYearArray() {
        int startYear = 2024;
        int endYear = 2100;
        Integer[] years = new Integer[endYear - startYear + 1];
        for (int i = 0; i <= endYear - startYear; i++) {
            years[i] = startYear + i;
        }
        return years;
    }

    private Integer[] generateMonthArray() {
        Integer[] months = new Integer[12];
        for (int i = 0; i < 12; i++) {
            months[i] = i + 1;
        }
        return months;
    }

    private Integer[] generateDayArray() {
        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = i + 1;
        }
        return days;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SET_LOCATION && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("latitude") && data.hasExtra("longitude")) {
                latitude = data.getDoubleExtra("latitude", 0);
                longitude = data.getDoubleExtra("longitude", 0);
                String locationText = "위도: " + latitude + ", 경도: " + longitude;
                gym_addr.setText(locationText);
                Toast.makeText(this, "위치가 설정되었습니다: " + locationText, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
