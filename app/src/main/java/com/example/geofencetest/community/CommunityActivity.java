package com.example.geofencetest.community;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geofencetest.R;
import com.example.geofencetest.community.Post;
import com.example.geofencetest.community.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;
    private EditText etPostContent;
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("커뮤니티");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));

        etPostContent = findViewById(R.id.etPostContent);
        btnPost = findViewById(R.id.btnPost);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postList = new ArrayList<>();
        // 예시 데이터 추가
        postList.add(new Post("사용자1", "10분 전", "첫 번째 게시물 내용입니다."));
        postList.add(new Post("사용자2", "20분 전", "두 번째 게시물 내용입니다."));
        postList.add(new Post("사용자3", "30분 전", "세 번째 게시물 내용입니다."));

        postAdapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(postAdapter);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etPostContent.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    // 새로운 게시물 추가
                    postList.add(0, new Post("새 사용자", "방금 전", content));
                    postAdapter.notifyItemInserted(0);
                    recyclerView.scrollToPosition(0);
                    etPostContent.setText(""); // 입력 필드 초기화
                }
            }
        });
    }
}
