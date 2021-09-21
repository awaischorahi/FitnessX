package com.applicationdevelopers.fitnessx.Views.Tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.applicationdevelopers.fitnessx.Adapter.FitnessGIFAdapter;
import com.applicationdevelopers.fitnessx.Adapter.Notification;
import com.applicationdevelopers.fitnessx.Model.FitnessGIFModel;
import com.applicationdevelopers.fitnessx.Model.NotificationModel;
import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.Tracker.Workouts.BackWorkout;

import java.util.ArrayList;
import java.util.List;

public class ActivityTracker extends AppCompatActivity {
    /*
        RecyclerView recyclerView;
    */
    RelativeLayout back_relativelayout, shoulder_relativelayout, triceps_relativelayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        back_relativelayout = findViewById(R.id.back);
        shoulder_relativelayout = findViewById(R.id.shoulder);
        triceps_relativelayout = findViewById(R.id.triceps);

        back_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(), BackWorkout.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK));
                Intent intent=new Intent(getApplicationContext(),BackWorkout.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("Name","BackExercise");
                startActivity(intent);
            }
        });

        shoulder_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(), BackWorkout.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK));
                Intent intent=new Intent(getApplicationContext(),BackWorkout.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("Name","ShoulderExericse");
                startActivity(intent);
            }
        });

        triceps_relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(), BackWorkout.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK));
                Intent intent=new Intent(getApplicationContext(),BackWorkout.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("Name","TricepExercise");
                startActivity(intent);
            }
        });

        /*recyclerView = findViewById(R.id.latest_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<String> list = new ArrayList<>();
        List<Drawable> mlist = new ArrayList<>();
        list.add("Triceps");
        list.add("Shoulder");
        list.add("Back");
        mlist.add(getResources().getDrawable(R.drawable.triceps));
        mlist.add(getResources().getDrawable(R.drawable.shoulder));
        mlist.add(getResources().getDrawable(R.drawable.back));

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new FitnessGIFAdapter(this, list,mlist));*/
    }
}