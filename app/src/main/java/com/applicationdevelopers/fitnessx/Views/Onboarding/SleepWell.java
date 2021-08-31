package com.applicationdevelopers.fitnessx.Views.Onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.SignUp.SignUp;

public class SleepWell extends Fragment {

    ImageButton next;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sleep_well, container, false);
        next=view.findViewById(R.id.btn_next_screen);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SignUp.class);
                startActivity(intent);
            }
        });
        return view;
    }
}