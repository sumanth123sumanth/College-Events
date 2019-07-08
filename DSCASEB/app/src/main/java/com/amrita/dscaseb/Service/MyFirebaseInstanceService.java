package com.amrita.dscaseb.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.amrita.dscaseb.After_intro;
import com.amrita.dscaseb.R;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.net.URLDecoder;

public class MyFirebaseInstanceService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String title=remoteMessage.getNotification().getTitle();
        String message=remoteMessage.getNotification().getBody();
        String click_action=remoteMessage.getNotification().getClickAction();
        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(click_action);
// Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
// Get the PendingIntent containing the entire back stack
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.final_dsc_logo);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"DSC");
        builder.setContentIntent(notifyPendingIntent);
        builder.setLargeIcon(icon);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }
}
