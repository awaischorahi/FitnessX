package com.applicationdevelopers.fitnessx.Views.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.Home.Fragments.ProfileFragment;
import com.applicationdevelopers.fitnessx.Views.Tracker.ActivityTracker;

public class HomeActivity extends AppCompatActivity {
    ImageView home, activity, camera, profile, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Init();
        addFragment(new HomeFragment());
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new HomeFragment());
            }
        });
        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityTracker.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            addFragment(new ProfileFragment());
            }
        });
    }

    public void Init() {
        home = findViewById(R.id.homebutt);
        activity = findViewById(R.id.activity);
        profile = findViewById(R.id.profile);
    }

    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}