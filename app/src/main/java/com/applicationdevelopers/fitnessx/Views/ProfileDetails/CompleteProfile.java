package com.applicationdevelopers.fitnessx.Views.ProfileDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.applicationdevelopers.fitnessx.R;

public class CompleteProfile extends AppCompatActivity {

    Button next_complete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    next_complete=findViewById(R.id.next_completeprofile);
    next_complete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),GoalActivity.class));
        }
    });
    }
}