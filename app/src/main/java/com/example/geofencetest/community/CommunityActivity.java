package com.example.geofencetest.community;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geofencetest.R;

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("커뮤니티");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postList = new ArrayList<>();
        // 예시 데이터 추가
        postList.add(new Post("사용자1", "10분 전", "첫 번째 게시물 내용입니다."));
        postList.add(new Post("사용자2", "20분 전", "두 번째 게시물 내용입니다."));
        postList.add(new Post("사용자3", "30분 전", "세 번째 게시물 내용입니다."));

        postAdapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(postAdapter);
    }
}
