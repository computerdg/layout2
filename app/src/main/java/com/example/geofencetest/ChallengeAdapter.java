package com.example.geofencetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ChallengeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Challenge> challList;

    public ChallengeAdapter(Context context, ArrayList<Challenge> challList) {
        this.context = context;
        this.challList = challList;
    }

    @Override
    public int getCount() {
        return challList.size();
    }

    @Override
    public Object getItem(int position) {
        return challList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_chall_list_item, parent, false);
        }

        TextView titleView = convertView.findViewById(R.id.chall_title);
        TextView statusView = convertView.findViewById(R.id.chall_status);
        ImageButton editButton = convertView.findViewById(R.id.edit_button);
        ImageButton deleteButton = convertView.findViewById(R.id.delete_button);

        Challenge challenge = challList.get(position);
        titleView.setText(challenge.getTitle());
        statusView.setText(challenge.getStatus());

        editButton.setOnClickListener(v -> {
            // 수정 버튼 클릭 처리
        });

        deleteButton.setOnClickListener(v -> {
            // 삭제 버튼 클릭 처리
        });

        return convertView;
    }
}
