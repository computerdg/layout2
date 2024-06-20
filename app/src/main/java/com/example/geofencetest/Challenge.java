package com.example.geofencetest;

public class Challenge {
    private String title;
    private String status;

    public Challenge(String title, String status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }
}
