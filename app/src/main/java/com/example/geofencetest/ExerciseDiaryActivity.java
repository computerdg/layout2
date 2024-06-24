package com.example.geofencetest;
// ExerciseJournalActivity.java
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

public class ExerciseDiaryActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private ScrollView scrollView;
    private EditText etJournalEntry;
    private AppCompatButton btnSave;
    private TextView tvJournalDisplay;
    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);

        calendarView = findViewById(R.id.calendarView);
        scrollView = findViewById(R.id.scrollView);
        etJournalEntry = findViewById(R.id.et_journal_entry);
        btnSave = findViewById(R.id.btn_save);
        tvJournalDisplay = findViewById(R.id.tv_journal_display);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Show the form when a date is selected
                selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                scrollView.setVisibility(View.VISIBLE);
                etJournalEntry.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.VISIBLE);
                tvJournalDisplay.setVisibility(View.GONE);
                etJournalEntry.setText("");
            }
        });

        btnSave.setOnClickListener(v -> {
            String journalEntry = etJournalEntry.getText().toString();

            if (journalEntry.isEmpty()) {
                Toast.makeText(ExerciseDiaryActivity.this, "운동 일지를 입력하세요.", Toast.LENGTH_SHORT).show();
            } else {
                // Save the journal entry (this could be to a database, shared preferences, etc.)
                tvJournalDisplay.setText(selectedDate + "\n" + journalEntry);
                tvJournalDisplay.setVisibility(View.VISIBLE);
                etJournalEntry.setVisibility(View.GONE);
                btnSave.setVisibility(View.GONE);
                Toast.makeText(ExerciseDiaryActivity.this, "운동일지가 저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
