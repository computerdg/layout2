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
                R.layout.screen1,
                R.layout.screen2,
                R.layout.screen3
        };

        pagerAdapter = new MyPagerAdapter(this, layouts);
        viewPager.setAdapter(pagerAdapter);
    }
}
