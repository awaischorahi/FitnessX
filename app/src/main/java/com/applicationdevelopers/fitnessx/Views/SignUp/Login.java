package com.applicationdevelopers.fitnessx.Views.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.ProfileDetails.Welcome;

public class Login extends AppCompatActivity {
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    btnlogin=findViewById(R.id.login_btn);
    btnlogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), Welcome.class));
        }
    });
    }
}