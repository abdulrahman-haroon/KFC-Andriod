package com.example.kfc;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class MyForegroundServiceClass extends Service {
    private Looper serviceLooper;
//    private ServiceHandler serviceHandler;
    private static final String TAG = "MyService";
    private Notification notification;
    private PendingIntent pendingIntent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        Log.d(TAG,"onCreate(): Service created");
        super.onCreate();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand(): Service starting");

        //when user click on the foreground notifcation it goes to MainActivity
        Intent notificationIntent = new Intent(this, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                0);
        updateForegroundNotification("App is running.");

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateForegroundNotification(String string){
        //For creating a channel as without it the channel wasn't being recognized.
        String NOTIFICATION_CHANNEL_ID = "foregroundService";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        //for foreground notification.
        notification=new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
                .setContentTitle("KFC")
                .setContentText(string)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentIntent(pendingIntent)
                .build();

        // notificationId is a unique int for each notification that you must define
        startForeground(1234, notification);
    }

    /**
     * for stopping the service.
     */
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy(): Service stopped");
        super.onDestroy();
    }
}
