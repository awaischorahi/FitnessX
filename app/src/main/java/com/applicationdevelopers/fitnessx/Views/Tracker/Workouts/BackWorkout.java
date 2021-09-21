package com.applicationdevelopers.fitnessx.Views.Tracker.Workouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.applicationdevelopers.fitnessx.Adapter.FitnessGIFAdapter;
import com.applicationdevelopers.fitnessx.Interfaces.RecyclerviewDataPass;
import com.applicationdevelopers.fitnessx.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BackWorkout extends AppCompatActivity {
    ImageView imageView;
    RecyclerView back_Recyclerview,shoulder_Recyclerview,triceps_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_workout);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        imageView = findViewById(R.id.gif_view_imageview);
        back_Recyclerview = findViewById(R.id.back_recyclerview);
        shoulder_Recyclerview = findViewById(R.id.shoulder_recyclerview);
        triceps_recyclerview = findViewById(R.id.triceps_recyclerview);
        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("Name");

        if (name!=null && name.equals("BackExercise")){
            Glide.with(getApplicationContext()).load(R.drawable.back).into(imageView);
            back_Recyclerview.setVisibility(View.VISIBLE);
            shoulder_Recyclerview.setVisibility(View.GONE);
            triceps_recyclerview.setVisibility(View.GONE);
            getBackExercise();
            return;

        }
        if (name!=null && name.equals("ShoulderExericse")){
            Glide.with(getApplicationContext()).load(R.drawable.shoulder).into(imageView);
            back_Recyclerview.setVisibility(View.GONE);
            shoulder_Recyclerview.setVisibility(View.VISIBLE);
            triceps_recyclerview.setVisibility(View.GONE);
            getShoulder();
            return;

        }
        if (name!=null && name.equals("TricepExercise")){
            Glide.with(getApplicationContext()).load(R.drawable.triceps).into(imageView);
            back_Recyclerview.setVisibility(View.GONE);
            shoulder_Recyclerview.setVisibility(View.GONE);
            triceps_recyclerview.setVisibility(View.VISIBLE);
            getTriceps();
            return;

        }


    }
public  void getBackExercise(){

    back_Recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    List<Drawable> list = new ArrayList<>();
    list.add(getResources().getDrawable(R.drawable.back));
    list.add(getResources().getDrawable(R.drawable.shoulder));
    list.add(getResources().getDrawable(R.drawable.triceps));
    RecyclerviewDataPass recyclerviewDataPass = new RecyclerviewDataPass() {
        @Override
        public void pass(int position) {
            switch (position) {
                case 0:
                    Glide.with(getApplicationContext()).load(R.drawable.back).into(imageView);
                    break;
                case 1:
                    Glide.with(getApplicationContext()).load(R.drawable.shoulder).into(imageView);
                    break;
                case 2:
                    Glide.with(getApplicationContext()).load(R.drawable.triceps).into(imageView);
                    break;
                default:
                    break;
            }
        }
    };
    back_Recyclerview.setAdapter(new FitnessGIFAdapter(this, list, recyclerviewDataPass));
}
public void getTriceps(){

    triceps_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    List<Drawable> list = new ArrayList<>();
    list.add(getResources().getDrawable(R.drawable.back));
    list.add(getResources().getDrawable(R.drawable.shoulder));
    list.add(getResources().getDrawable(R.drawable.triceps));
    RecyclerviewDataPass recyclerviewDataPass = new RecyclerviewDataPass() {
        @Override
        public void pass(int position) {
            switch (position) {
                case 0:
                    Glide.with(getApplicationContext()).load(R.drawable.back).into(imageView);
                    break;
                case 1:
                    Glide.with(getApplicationContext()).load(R.drawable.shoulder).into(imageView);
                    break;
                case 2:
                    Glide.with(getApplicationContext()).load(R.drawable.triceps).into(imageView);
                    break;
                default:
                    break;
            }
        }
    };
    triceps_recyclerview.setAdapter(new FitnessGIFAdapter(this, list, recyclerviewDataPass));
}
public void getShoulder(){

    shoulder_Recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    List<Drawable> list = new ArrayList<>();
    list.add(getResources().getDrawable(R.drawable.back));
    list.add(getResources().getDrawable(R.drawable.shoulder));
    list.add(getResources().getDrawable(R.drawable.triceps));
    RecyclerviewDataPass recyclerviewDataPass = new RecyclerviewDataPass() {
        @Override
        public void pass(int position) {
            switch (position) {
                case 0:
                    Glide.with(getApplicationContext()).load(R.drawable.back).into(imageView);
                    break;
                case 1:
                    Glide.with(getApplicationContext()).load(R.drawable.shoulder).into(imageView);
                    break;
                case 2:
                    Glide.with(getApplicationContext()).load(R.drawable.triceps).into(imageView);
                    break;
                default:
                    break;
            }
        }
    };
    shoulder_Recyclerview.setAdapter(new FitnessGIFAdapter(this, list, recyclerviewDataPass));
}
}