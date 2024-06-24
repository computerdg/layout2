package com.example.geofencetest;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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
}
