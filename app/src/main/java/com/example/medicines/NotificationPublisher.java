package com.example.medicines;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;

import static com.example.medicines.EditorActivity.MEDICINE_DATA;

public class NotificationPublisher extends BroadcastReceiver {

    private String CHANNEL_ID = "my_channel_id";

    public void onReceive(Context context, Intent intent) {
        createNotificationChannel(context);

        int medicineId = intent.getIntExtra("medicineId", 0);

        //((MedicineApp)context.getApplicationContext()).getMedicineService().getAllMedicine()

        //TODO jak bym chciała EditorActivity to zmieniam klasę, a jak dodać konkretne id?
        Intent medIntent = new Intent(context, EditorActivity.class);
        // docelowo tu powinien byc przekazywany obiekt medicine
        intent.putExtra(MEDICINE_DATA, (Parcelable)null);
        PendingIntent alarmIntent = PendingIntent.getActivity(context, 0, medIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("title")
                .setContentText("content")
                .setContentIntent(alarmIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Notification notification = builder.build();
        int id = 0;

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);

    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "channel_description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}