package com.example.geofencetest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class challList_proto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chall_list_proto);

        Button cl1 = (Button) findViewById(R.id.cl1);
        Button btn_makeChall = (Button) findViewById(R.id.btn_makeChall);

        cl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChallengeViewActivity.class);
                startActivity(intent);
            }
        });

        btn_makeChall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), create_chall.class);
                startActivity(intent);
            }
        });
    }
}