package com.applicationdevelopers.fitnessx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.applicationdevelopers.fitnessx.Adapter.OnboardingAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tabLayout=findViewById(R.id.tablayout_onboard);
        viewPager=findViewById(R.id.viewPager_onboard);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new OnboardingAdapter(getSupportFragmentManager(),4));
        viewPager.setCurrentItem(0);
    }
}