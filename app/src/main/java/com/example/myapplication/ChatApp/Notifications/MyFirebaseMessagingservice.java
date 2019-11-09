package com.example.myapplication.ChatApp.Notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.myapplication.ChatApp.Message_Activity;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingservice extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        System.out.println("from=--------------->" + remoteMessage.getFrom());
        System.out.println("notification-------------->" + remoteMessage.getData().get("body"));





        //       System.out.println("data=---------->"+remoteMessage.getData().get());

        String sent = remoteMessage.getFrom();
       // FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            sendNotification(remoteMessage);



    }

    private void sendNotification(RemoteMessage remoteMessage) {

       // String user = remoteMessage.getFrom();
       // String icon = remoteMessage.getNotification().getIcon();
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");

        String user=remoteMessage.getData().get("sent");

        RemoteMessage.Notification notification = remoteMessage.getNotification();


        Intent intent = new Intent(this, Message_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putString("userid", user);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultsound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            String CHANNEL_ID = "my_channel_01";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {



                CharSequence name = "my_channel";
                String Description = "This is my channel";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                mChannel.setDescription(Description);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mChannel.setShowBadge(false);

                notificationManager.createNotificationChannel(mChannel);
            }


            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID).
                    setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title).setContentText(body).setAutoCancel(true)
                    .setSound(defaultsound).setContentIntent(pendingIntent);

            notificationManager.notify(0, builder.build());
        }

    }
}
