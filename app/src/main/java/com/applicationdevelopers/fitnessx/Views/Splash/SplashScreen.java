package com.applicationdevelopers.fitnessx.Views.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.TextView;

import com.applicationdevelopers.fitnessx.MainActivity;
import com.applicationdevelopers.fitnessx.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    timer=new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            Intent intent=new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    },3000);
    }
}