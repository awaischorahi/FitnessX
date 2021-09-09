package com.applicationdevelopers.fitnessx.Views.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.applicationdevelopers.fitnessx.Adapter.Notification;
import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.Home.Fragments.ProfileFragment;
import com.applicationdevelopers.fitnessx.Views.Tracker.ActivityTracker;

public class HomeActivity extends AppCompatActivity {
    ImageView home, activity, camera, profile, search,notification;
    TextView count_notification,toolbartext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Init();
        addFragment(new HomeFragment(),"Home");
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new HomeFragment(),"Home");
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
            addFragment(new ProfileFragment(),"Profile");
            }
        });
    }

    public void Init() {
        home = findViewById(R.id.homebutt);
        activity = findViewById(R.id.activity);
        profile = findViewById(R.id.profile);
        count_notification = findViewById(R.id.notification_count);
        notification = findViewById(R.id.notification);
        toolbartext = findViewById(R.id.toolbar_Text);

    }

    public void addFragment(Fragment fragment,String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        toolbartext.setText(title);


    }

    public void freeMemory(){
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }
    @Override
    public void onBackPressed() {
     //   super.onBackPressed();
        Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.home_layout);
        addFragment(new HomeFragment(),"Home");
        if (fragment instanceof HomeFragment){
            finish();
            finishAffinity();
        }
    }
}