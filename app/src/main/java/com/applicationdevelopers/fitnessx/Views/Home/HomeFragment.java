package com.applicationdevelopers.fitnessx.Views.Home;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.applicationdevelopers.fitnessx.Adapter.SparkAdapter;
import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.NotificationActivity.NotificationActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.robinhood.spark.SparkView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    AnyChartView anyChartView;
    String[] BMI = {"normal", "hard"};
    int[] value = {4, 10};
    SparkView sparkView;
    ShimmerFrameLayout shimmerFrameLayout;
    TextView spark_textview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Init(view);
        //pie chart
        Pie pie = AnyChart.pie();
        List<DataEntry> list = new ArrayList<>();
        for (int i = 0; i < BMI.length; i++) {
            list.add(new ValueDataEntry(BMI[i], value[i]));
        }
        pie.data(list);
        anyChartView.setChart(pie);

        //spark heartbeat chart
        float[] data = {(float) 0.2, (float) 0.4, (float) 0.1, (float) 0.3, (float) 0.5, (float) 0.4, (float) 0.6, (float) 0.4, (float) 0.1, (float) 0.4};
        sparkView.setAdapter(new SparkAdapter(getContext(), data));
        anyChartView = view.findViewById(R.id.piechart1);
        sparkView.setScrubEnabled(true);
        sparkView.setScrubListener(new SparkView.OnScrubListener() {
            @Override
            public void onScrubbed(Object value) {
            //    spark_textview.setText(getString(R.string.app_name), (TextView.BufferType) value);
                //Toast.makeText(getContext(), ""+value, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void Init(View view) {
        anyChartView = view.findViewById(R.id.piechart1);
        sparkView = view.findViewById(R.id.sparkview);
        shimmerFrameLayout = view.findViewById(R.id.shimmerlayout);
        shimmerFrameLayout.startShimmer();
        NestedScrollView nestedScrollView=view.findViewById(R.id.nested_scroll_view);
        nestedScrollView.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmerFrameLayout.startShimmer();
                shimmerFrameLayout.hideShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);
            }
        },8000);

    }
}