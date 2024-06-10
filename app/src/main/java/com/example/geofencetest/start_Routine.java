package com.example.geofencetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class start_Routine extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private double targetLatitude, targetLongitude;
    private boolean checkInSuccess;
    private final float RADIUS_IN_METERS = 1000;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_routine);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Intent로부터 위도와 경도 및 출석 인증 성공 여부 받기
        Intent intent = getIntent();
        targetLatitude = intent.getDoubleExtra("latitude", 0);
        targetLongitude = intent.getDoubleExtra("longitude", 0);
        checkInSuccess = intent.getBooleanExtra("checkInSuccess", false);

        // 버튼 설정
        Button startRoutineButton = findViewById(R.id.btn_startRoutine);
        startRoutineButton.setOnClickListener(v -> {
            if (checkInSuccess) {
                checkLocation();
            } else {
                Toast.makeText(start_Routine.this, "출석 인증에 실패하여 운동을 시작할 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Task<Location> locationTask = fusedLocationClient.getLastLocation();
            locationTask.addOnSuccessListener(this, location -> {
                if (location != null) {
                    float[] distance = new float[2];
                    Location.distanceBetween(location.getLatitude(), location.getLongitude(),
                            targetLatitude, targetLongitude, distance);
                    if (distance[0] <= RADIUS_IN_METERS) {
                        Toast.makeText(start_Routine.this, "운동을 시작합니다.", Toast.LENGTH_SHORT).show();
                        // 운동 정상 시작되면 오늘 루틴 페이지로 이동
                        Intent intent = new Intent(getApplicationContext(), complete_todayWorkout.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(start_Routine.this, "운동 시작 실패: 지정된 위치의 반경 내에 있지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(start_Routine.this, "현재 위치를 확인할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocation();
            } else {
                Toast.makeText(this, "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
