package com.applicationdevelopers.fitnessx.Views.Tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.applicationdevelopers.fitnessx.Adapter.Notification;
import com.applicationdevelopers.fitnessx.Model.NotificationModel;
import com.applicationdevelopers.fitnessx.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityTracker extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        recyclerView = findViewById(R.id.latest_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<NotificationModel> list = new ArrayList<>();
        list.add(new NotificationModel("Hey, its time for lunch", "About 1 min ago"));
        list.add(new NotificationModel("Hey, its time for workout", "About 30 mins ago"));
        list.add(new NotificationModel("Hey, its time for sleep", "About 1 hour ago"));

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new Notification(this, list));
    }
}