package com.applicationdevelopers.fitnessx.Services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.applicationdevelopers.fitnessx.DbHelper.DBHelperClass;
import com.applicationdevelopers.fitnessx.Model.TimeModel;
import com.applicationdevelopers.fitnessx.R;
import com.applicationdevelopers.fitnessx.Views.Home.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class NotificationBackgroundServices extends Service {
    Context mcontext;
    private String uuid;
    private DBHelperClass dbHelperClass;
    private NotificationManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mcontext = getApplicationContext();
        dbHelperClass = new DBHelperClass(mcontext, null, null, DBHelperClass.DatabaseVersion);


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

            //    showWaterDrinkAlert("600ml", "6am - 9am -> 600ml?");
            WaterDrinkAlertNotification("600ml");

        }
        boolean seeifaddedToday500ml = dbHelperClass.CheckifAddedAlready(date, "500ml");

        if (current_hour >= 9 && current_hour <= 10 && !seeifaddedToday500ml) {

            //  showWaterDrinkAlert("500ml", "9am - 11am -> 500ml?");
            WaterDrinkAlertNotification("500ml");

        }
        boolean seeifaddedToday1000ml = dbHelperClass.CheckifAddedAlready(date, "1000ml");

        if (current_hour >= 11 && current_hour <= 13 && !seeifaddedToday1000ml) {

            //showWaterDrinkAlert("1000ml", "11am - 2pm -> 1000ml?");
            WaterDrinkAlertNotification("1000ml");

        }
        boolean seeifaddedToday700ml = dbHelperClass.CheckifAddedAlready(date, "700ml");

        if (current_hour >= 14 && current_hour <= 15 && !seeifaddedToday700ml) {

            //showWaterDrinkAlert("700ml", "2pm - 5pm -> 700ml?");
            WaterDrinkAlertNotification("700ml");

        } else {
            //   Toast.makeText(mcontext, "Drink two Glass of water before Sleep", Toast.LENGTH_SHORT).show();
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        return START_STICKY;

    }


    public void WaterDrinkAlertNotification(String message) {
        manager = (NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(mcontext, HomeActivity.class);
        Intent intent1 = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        mcontext.sendBroadcast(intent1);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("waterDrinkId", "WaterDrinkAlert", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            manager.createNotificationChannel(notificationChannel);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(mcontext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mcontext, "waterDrinkId")
                .setSmallIcon(R.drawable.glass)
                .setOngoing(true) //use not to cancel or swipe notification
                .setContentText("Have you drink water? " + message)
                .setContentTitle("Drink water alert");
        builder.setContentIntent(pendingIntent)
                .build();
        manager.notify(0, builder.build());
       /* Notification notification=builder.build();
        startForeground(0, notification);*/
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        //if app is killed or services is stop it rerun service
/*
        Intent intent = new Intent("com.android.ServiceStopped");
        sendBroadcast(intent);

*/

        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());

        PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 0, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 1000,
                restartServicePendingIntent);

        super.onTaskRemoved(rootIntent);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
