package com.softsquared.todaysout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.softsquared.todaysout.src.main.MainActivity;

import java.util.Map;
import java.util.Random;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //메세지 수신 시 실행되는 메소드

        if(remoteMessage.getNotification() != null){
            Log.d("FCM","알림메세지:"+ remoteMessage.getNotification().getBody());
            String messageBody = remoteMessage.getNotification().getBody();
            String messageTitle = remoteMessage.getNotification().getTitle();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
            String channelId = "Channel ID";
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder =
                    new NotificationCompat.Builder(this,channelId)
                        .setSmallIcon(R.mipmap.app_icon)
                        .setContentTitle(messageTitle)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                String channelName = "Channel Name";
                NotificationChannel channel = new NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
            }
            notificationManager.notify(0,notificationBuilder.build());
        }


    }

    @Override
    public void onNewToken(String token) {

        sendRegistrationToServer(token);
    }



    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
}
