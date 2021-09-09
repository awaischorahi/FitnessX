package com.applicationdevelopers.fitnessx.Views.Home.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.SignUp.Login;
import com.applicationdevelopers.fitnessx.Views.Splash.SplashScreen;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class ProfileFragment extends Fragment {
    Button button;
    TextView nameofuser, ageofuser, weight, height;
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String userid;


    ShimmerFrameLayout shimmerFrameLayout;
    NestedScrollView nestedScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Init(view);

        return view;
    }

    public void Init(View view) {
        button = view.findViewById(R.id.edit_btn);
        nameofuser = view.findViewById(R.id.name_title);
        ageofuser = view.findViewById(R.id.age_show);
        weight = view.findViewById(R.id.weight_show);
        height = view.findViewById(R.id.height_Show);
        nestedScrollView = view.findViewById(R.id.nested_scroll_view_profile);
        shimmerFrameLayout = view.findViewById(R.id.shimer_profile);
        nestedScrollView.setVisibility(View.GONE);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        userid = firebaseAuth.getCurrentUser().getUid();

        shimmerFrameLayout.startShimmer();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.hideShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);
            }
        },8000);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                startActivity(new Intent(getContext(), Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDataFromFirebaseDatabase();

            }
        },1000);
    }

    public void getDataFromFirebaseDatabase() {
        databaseReference.child(userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HashMap hashMap = (HashMap) snapshot.getValue();
                    String first = "", last = "";
                    if (hashMap.get("firstname") != null) {
                        first = hashMap.get("firstname").toString();
                    }
                    if (hashMap.get("lastname") != null) {
                        last = hashMap.get("lastname").toString();
                    }
                    if (first != null && last != null) {
                        nameofuser.setText(first + " " + last);
                    }
                    if (hashMap.get("height") != null) {
                        height.setText(hashMap.get("height").toString());
                    }
                    if (hashMap.get("weight") != null) {
                        weight.setText(hashMap.get("weight").toString());
                    }
                    if (hashMap.get("birthday") != null) {
                        String age = hashMap.get("birthday").toString();


                        int month = Integer.parseInt(age.split("/")[1]);
                        int day = Integer.parseInt(age.split("/")[0]);
                        int year = Integer.parseInt(age.split("/")[2]);

                        ageofuser.setText("" + getAge(year, month, day));
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public int getAge(int year, int month, int day) {
        int age1 = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            age1 = Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears();
        }
        return age1;
    }
}