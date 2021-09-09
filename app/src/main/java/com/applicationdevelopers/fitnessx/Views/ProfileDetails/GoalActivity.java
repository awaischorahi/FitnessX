package com.applicationdevelopers.fitnessx.Views.ProfileDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.applicationdevelopers.fitnessx.Adapter.GoalAdapter;
import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.SignUp.Login;

import java.util.ArrayList;
import java.util.List;

public class GoalActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button confirm;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Init();

    }

    public void Init() {
        recyclerView = findViewById(R.id.recyclerview_confirm_goal);
        confirm = findViewById(R.id.confirm_goal);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        List<Drawable> list = new ArrayList<>();
        list.add(getResources().getDrawable(R.drawable.fat));
        list.add(getResources().getDrawable(R.drawable.tone));
        list.add(getResources().getDrawable(R.drawable.goalshape));

        recyclerView.setAdapter(new GoalAdapter(this, list));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setPadding(50, 40, 50, 40);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GoalAdapter.ViewHolderClass viewHolderClass = (GoalAdapter.ViewHolderClass) recyclerView.findViewHolderForAdapterPosition(0);
                RelativeLayout relativeLayout = viewHolderClass.itemView.findViewById(R.id.item_card_view_layout);
                relativeLayout.animate().setDuration(350).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
            }
        }, 100);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                View view = snapHelper.findSnapView(layoutManager);
                int pos = layoutManager.getPosition(view);
                GoalAdapter.ViewHolderClass viewHolderClass = (GoalAdapter.ViewHolderClass) recyclerView.findViewHolderForAdapterPosition(pos);
                RelativeLayout relativeLayout = viewHolderClass.itemView.findViewById(R.id.item_card_view_layout);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    relativeLayout.animate().setDuration(350).scaleX(1).scaleY(1).setInterpolator(new AccelerateInterpolator()).start();
                } else {
                    relativeLayout.animate().setDuration(350).scaleX(0.75f).scaleY(0.75f).setInterpolator(new AccelerateInterpolator()).start();

                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Welcome.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

    }
}