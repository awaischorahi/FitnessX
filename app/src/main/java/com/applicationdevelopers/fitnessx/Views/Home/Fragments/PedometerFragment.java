package com.applicationdevelopers.fitnessx.Views.Home.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.SharedPreferenceClass.PedometerSharedPreference;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class PedometerFragment extends Fragment implements SensorEventListener {

    SensorManager sensorManager;
    boolean running = false;
    float totalsteps = 0f;
    float previous_totalsteps = 0f;
    Context context;
    CircularProgressBar circularProgressBar;
    TextView textView_steps;
    private Sensor sensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedometer, container, false);
        context = getActivity().getApplicationContext();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        circularProgressBar = view.findViewById(R.id.circular_progressbar_pedometer);
        textView_steps = view.findViewById(R.id.steps_taken);
        resetSteps();


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        running = true;
     sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
    // sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    //    sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);


        if (sensor == null) {
            Toast.makeText(context, "NO sensor detected on this device", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        if (running) {
            if (event.sensor == sensor) {
                totalsteps = event.values[0];
                int currentsteps = (int) (totalsteps - previous_totalsteps);
                textView_steps.setText("" + currentsteps);
                circularProgressBar.setProgressWithAnimation(Float.parseFloat(String.valueOf(currentsteps)));

            }
        }
    }

    public void resetSteps() {
        textView_steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Tap long to reset steps", Toast.LENGTH_SHORT).show();
            }
        });

        textView_steps.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                previous_totalsteps = totalsteps;
                textView_steps.setText("0");
                circularProgressBar.setProgressWithAnimation(0);

                return true;
            }
        });


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}