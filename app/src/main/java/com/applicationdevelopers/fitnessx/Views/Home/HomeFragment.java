package com.applicationdevelopers.fitnessx.Views.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.applicationdevelopers.fitnessx.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    AnyChartView anyChartView;
    String[] BMI={"normal","hard"};
    int[] value={4,10};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        anyChartView = view.findViewById(R.id.piechart1);
        Pie pie= AnyChart.pie();
        List<DataEntry> list=new ArrayList<>();
        for (int i = 0; i < BMI.length; i++) {
            list.add(new ValueDataEntry(BMI[i],value[i]));
        }
        pie.data(list);
        anyChartView.setChart(pie);
        return view;
    }
}