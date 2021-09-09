package com.applicationdevelopers.fitnessx.Views.NotificationActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;


import com.applicationdevelopers.fitnessx.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    DatabaseReference databaseReference;
    String userid;
    FirebaseAuth firebaseAuth;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PushNotification");
        firebaseAuth = FirebaseAuth.getInstance();
        userid = firebaseAuth.getCurrentUser().getUid();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null) {
            //
        } else {
            NotificatinCompatMethod(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }

    public void NotificatinCompatMethod(String title, String desc) {

        Intent intent = new Intent(this, NotificationActivity.class);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationid = new Random().nextInt(3000);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("mychannel", "NotificationFitnessX", NotificationManager.IMPORTANCE_HIGH);
            channel.setLightColor(Color.RED);
            channel.enableLights(true);
            channel.enableVibration(true);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
      /*  Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.profile);
        Uri notificationSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
*/

        Bitmap bitmaplarge = BitmapFactory.decodeResource(getResources(), R.drawable.profile);
        Uri sounduri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "mychannel")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.profile)
                .setLargeIcon(bitmaplarge)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true)
                .setSound(sounduri)
                .setContentText(desc);
        // NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationid, builder.build());
    }


}
