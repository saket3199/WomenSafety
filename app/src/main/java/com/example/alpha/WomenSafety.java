package com.example.alpha;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class WomenSafety extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("This is Channel 1");
            NotificationChannel channe2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channe2.setDescription("This is Channel 2");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            manager.createNotificationChannel(channe2);
        }
    }
}
