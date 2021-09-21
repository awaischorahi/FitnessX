package com.applicationdevelopers.fitnessx.Views.Home;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.core.ui.Center;
import com.applicationdevelopers.fitnessx.Adapter.SparkAdapter;
import com.applicationdevelopers.fitnessx.DbHelper.DBHelperClass;
import com.applicationdevelopers.fitnessx.Model.TimeModel;
import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Services.NotificationBackgroundServices;
import com.applicationdevelopers.fitnessx.Views.NotificationActivity.NotificationActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.robinhood.spark.SparkView;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

public class HomeFragment extends Fragment implements SensorEventListener {
    AnyChartView anyChartView;
    String[] BMI = {"normal", "hard"};
    int[] value = {4, 10};
    SparkView sparkView;
    ShimmerFrameLayout shimmerFrameLayout;
    TextView spark_textview;
    Context mcontext;
    private String uuid;
    private DBHelperClass dbHelperClass;
    private NotificationManager manager;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
          mcontext = getActivity().getApplicationContext();

//        dbHelperClass = new DBHelperClass(mcontext, null, null, DBHelperClass.DatabaseVersion);

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
         uuid = UUID.randomUUID().toString();
        dbHelperClass = new DBHelperClass(mcontext, null, null, DBHelperClass.DatabaseVersion);


        NestedScrollView nestedScrollView = view.findViewById(R.id.nested_scroll_view);
        nestedScrollView.setVisibility(View.GONE);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmerFrameLayout.startShimmer();
                shimmerFrameLayout.hideShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);
                sensorHeartRate();

                Calendar calendar = Calendar.getInstance();
                String timeZoneId = TimeZone.getDefault().getID();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
                String time = simpleDateFormat.format(calendar.getTime());

                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM,dd,yyyy");
                simpleDateFormat1.setTimeZone(TimeZone.getTimeZone(timeZoneId));
                String date = simpleDateFormat1.format(calendar.getTime());


                int current_hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);//current hour
                // boolean compaire = current_hour >= 6 && current_hour<=8;
                boolean seeifaddedToday600ml = dbHelperClass.CheckifAddedAlready(date, "600ml");

                if (current_hour >= 6 && current_hour <= 8 && !seeifaddedToday600ml) {

                    showWaterDrinkAlert("600ml", "6am - 9am -> 600ml?");


                    return;

                }
                boolean seeifaddedToday500ml = dbHelperClass.CheckifAddedAlready(date, "500ml");

                if (current_hour >= 9 && current_hour <= 10 && !seeifaddedToday500ml) {

                    showWaterDrinkAlert("500ml", "9am - 11am -> 500ml?");

                    return;
                }
                boolean seeifaddedToday1000ml = dbHelperClass.CheckifAddedAlready(date, "1000ml");

                if (current_hour >= 11 && current_hour <= 13 && !seeifaddedToday1000ml) {

                    showWaterDrinkAlert("1000ml", "11am - 2pm -> 1000ml?");

                    return;
                }
                boolean seeifaddedToday700ml = dbHelperClass.CheckifAddedAlready(date, "700ml");

                if (current_hour >= 14 && current_hour <= 15 && !seeifaddedToday700ml) {

                    showWaterDrinkAlert("700ml", "2pm - 4pm -> 700ml?");
                    return;
                } else {
                    //   Toast.makeText(mcontext, "Drink two Glass of water before Sleep", Toast.LENGTH_SHORT).show();
                }
            }
        }, 8000);

    }

    public void getTime() {
        Calendar calendar = Calendar.getInstance();
        String timeZoneID = TimeZone.getDefault().getID();
        Log.wtf("Time zone is -> ", timeZoneID);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneID));
        String localtime = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(mcontext, "Current time is " + localtime, Toast.LENGTH_SHORT).show();
    }

    public void showWaterDrinkAlert(String amountofWater, String time) {
        //   WindowManager windowManager= (WindowManager) mcontext.getSystemService(Context.WINDOW_SERVICE);
      /*  LayoutInflater layoutInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.drink_water_inflate, null, false);
/*



        final Dialog fbDialogue = new Dialog(mcontext, android.R.style.Theme_Black_NoTitleBar);
        fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
        fbDialogue.setContentView(R.layout.drink_water_inflate);
        fbDialogue.setCancelable(true);
        fbDialogue.show();*/
        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.drink_water_inflate, null);

        Button btn = popupView.findViewById(R.id.btn_check_drink);
        TextView show_Textview_water = popupView.findViewById(R.id.water_show_Textview);

        DisplayMetrics displayMetrics = mcontext.getApplicationContext().getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setOutsideTouchable(true);

        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        popupWindow.getContentView().setFocusableInTouchMode(true);
        show_Textview_water.setText("Have you Drink water at " + time);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manager = (NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);


                addDrinkWaterinDatabase("Yes", amountofWater);
                manager.cancel(0);
                popupWindow.dismiss();
            }
        });

      /*
      another method
        WindowManager.LayoutParams params=new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity= Gravity.CENTER|Gravity.CENTER;
        params.x=0;
        params.y=0;
        windowManager.addView(view, params); }*/
    }

    public void addDrinkWaterinDatabase(String isDrink, String amountofWAter) {

        Calendar calendar = Calendar.getInstance();
        String timeZoneId = TimeZone.getDefault().getID();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM,dd,yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        simpleDateFormat1.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        String time = simpleDateFormat.format(calendar.getTime());
        String date = simpleDateFormat1.format(calendar.getTime());
        // addDrinkWaterinDatabase(time, date, "Yes", amountofWater);

        dbHelperClass.addTimeTable(new TimeModel(uuid, time, date, isDrink, amountofWAter));
        Toast.makeText(mcontext, "Current time is-> " + time, Toast.LENGTH_SHORT).show();
    }

public void sensorHeartRate(){
    sensorManager = (SensorManager) mcontext.getSystemService(Context.SENSOR_SERVICE);
    sensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
    if (sensor ==null){
        Toast.makeText(mcontext, "No heart rate sensor found", Toast.LENGTH_SHORT).show();
    }else {
        sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_UI);
    }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor==sensor){
            Toast.makeText(mcontext, ""+event.sensor.getType(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}