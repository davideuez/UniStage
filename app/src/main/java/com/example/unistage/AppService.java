package com.example.unistage;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AppService extends Service {

    Handler handler = new Handler();
    Runnable runnable;
    int delay = 2000;
    String valore = "";
    static private int id = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MYTAG","onstartCommand");
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, delay);
                checkDataChanges();
            }
        }, delay);

        return START_STICKY;
    }

    private void sendNotification(String change) {
        NotificationCompat.Builder notificationBuilder;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String chanel_id = "3000";
            CharSequence name = "Channel Name";
            String description = "Chanel Description";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(chanel_id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            notificationManager.createNotificationChannel(mChannel);
            notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), chanel_id);
        } else {
            notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
        }

        notificationBuilder.setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Ti sei candidato a: ")
                .setContentText(change)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(getApplicationContext(), Notifiche.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        notificationBuilder.setContentIntent(pendingIntent);

        notificationManager.notify(id, notificationBuilder.build());
        id++;
    }

    private void checkDataChanges() {
        sendNotification(LoginActivity.u_loggato.tirocinio_in_corso.titolo);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}