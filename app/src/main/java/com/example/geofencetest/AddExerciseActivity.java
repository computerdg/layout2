package com.example.geofencetest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AddExerciseActivity extends Activity {

    private ListView categoryListView, exerciseListView;
    private LinearLayout selectedItemsLayout;
    private Button completeButton, closeButton;
    private ArrayList<Exercise> selectedExercises;
    private String selectedExerciseName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        categoryListView = findViewById(R.id.categoryListView);
        exerciseListView = findViewById(R.id.exerciseListView);
        selectedItemsLayout = findViewById(R.id.selectedItemsLayout);
        completeButton = findViewById(R.id.completeButton);
        closeButton = findViewById(R.id.closeButton);
        selectedExercises = new ArrayList<>();

        // 예시 데이터
        String[] categories = {"가슴", "등", "다리"};
        String[] exercises = {"벤치프레스", "랫풀다운", "스쿼트"};

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        categoryListView.setAdapter(categoryAdapter);

        ArrayAdapter<String> exerciseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercises);
        exerciseListView.setAdapter(exerciseAdapter);

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedExerciseName = (String) parent.getItemAtPosition(position);
                showInputDialog();
            }
        });

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("selectedExercises", selectedExercises);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(selectedExerciseName + " 설정");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText weightInput = new EditText(this);
        weightInput.setHint("반복 횟수");
        weightInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(weightInput);

        final EditText repsInput = new EditText(this);
        repsInput.setHint("SET 수");
        repsInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        layout.addView(repsInput);

        builder.setView(layout);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String weight = weightInput.getText().toString();
                String reps = repsInput.getText().toString();
                if (!weight.isEmpty() && !reps.isEmpty()) {
                    Exercise exercise = new Exercise(selectedExerciseName, weight, reps);
                    selectedExercises.add(exercise);
                    displaySelectedExercise(exercise);
                }
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void displaySelectedExercise(Exercise exercise) {
        TextView textView = new TextView(this);
        textView.setText(exercise.getName() + " - " + exercise.getWeight() + "회 x " + exercise.getReps() + "세트");
        selectedItemsLayout.addView(textView);
    }

    public static class Exercise implements Parcelable {
        private String name;
        private String weight;
        private String reps;

        public Exercise(String name, String weight, String reps) {
            this.name = name;
            this.weight = weight;
            this.reps = reps;
        }

        protected Exercise(Parcel in) {
            name = in.readString();
            weight = in.readString();
            reps = in.readString();
        }

        public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
            @Override
            public Exercise createFromParcel(Parcel in) {
                return new Exercise(in);
            }

            @Override
            public Exercise[] newArray(int size) {
                return new Exercise[size];
            }
        };

        public String getName() {
            return name;
        }

        public String getWeight() {
            return weight;
        }

        public String getReps() {
            return reps;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(weight);
            dest.writeString(reps);
        }
    }
}
