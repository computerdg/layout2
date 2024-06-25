package com.example.geofencetest.ExerciseDiary;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.geofencetest.R;

public class ExerciseDiaryActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private ScrollView scrollView;
    private EditText etJournalEntry;
    private AppCompatButton btnSave;
    private AppCompatButton btnDelete;
    private TextView tvJournalDisplay;
    private String selectedDate = "";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);

        calendarView = findViewById(R.id.calendarView);
        scrollView = findViewById(R.id.scrollView);
        etJournalEntry = findViewById(R.id.et_journal_entry);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);
        tvJournalDisplay = findViewById(R.id.tv_journal_display);

        sharedPreferences = getSharedPreferences("ExerciseJournal", MODE_PRIVATE);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                scrollView.setVisibility(View.VISIBLE);
                displayJournalEntry();
            }
        });

        btnSave.setOnClickListener(v -> {
            String journalEntry = etJournalEntry.getText().toString();

            if (journalEntry.isEmpty()) {
                Toast.makeText(ExerciseDiaryActivity.this, "운동 일지를 입력하세요.", Toast.LENGTH_SHORT).show();
            } else {
                saveJournalEntry(journalEntry);
                displayJournalEntry();
                Toast.makeText(ExerciseDiaryActivity.this, "운동일지가 저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(v -> {
            deleteJournalEntry();
            displayJournalEntry();
            Toast.makeText(ExerciseDiaryActivity.this, "운동일지가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        });
    }

    private void saveJournalEntry(String journalEntry) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(selectedDate, journalEntry);
        editor.apply();
    }

    private void deleteJournalEntry() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(selectedDate);
        editor.apply();
    }

    private void displayJournalEntry() {
        String journalEntry = sharedPreferences.getString(selectedDate, "");
        if (!journalEntry.isEmpty()) {
            tvJournalDisplay.setText(selectedDate + "\n" + journalEntry);
            tvJournalDisplay.setVisibility(View.VISIBLE);
            etJournalEntry.setVisibility(View.GONE);
            btnSave.setVisibility(View.GONE);
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            tvJournalDisplay.setVisibility(View.GONE);
            etJournalEntry.setVisibility(View.VISIBLE);
            btnSave.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.GONE);
            etJournalEntry.setText("");
        }
    }
}
