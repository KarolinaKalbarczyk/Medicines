package com.example.medicines;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;


public class AlarmReceiver extends BroadcastReceiver {

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED") || intent.getAction().equals("com.example.medicine.new_alarm")) {
            int medicineId = intent.getIntExtra("id", 0);


            alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            //intent = new Intent(context, AlarmReceiver.class);
            //alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

            // zastanow sie jaka akcje chcesz odpalac po wlaczeniu sie alarmu - moze jakas nowa aktywnosc?
            //TODO jak bym chciała EditorActivity to zmieniam klasę, a jak dodać konkretne id?
            //alarmIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
            Intent medIntent = new Intent("com.example.medicine.medicine_alarm_action");
            medIntent.putExtra("medicineId", medicineId);
            medIntent.addCategory(String.valueOf(medicineId));
            /* read more about this https://commonsware.com/Android/previews/appendix-b-android-80 */
            medIntent.setComponent(new ComponentName(context, NotificationPublisher.class));
            alarmIntent = PendingIntent.getBroadcast(context, 0, medIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Calendar currentDate = Calendar.getInstance();
            long firstAlarmTimeInMilis = context.getSharedPreferences("Medicine", 0).getLong("firstAlarmTimeInMilis", 0);
            long endAlarmDateInMilis = context.getSharedPreferences("Medicine", 0).getLong("endAlarmDateInMilis", 0);
            long interval = context.getSharedPreferences("Medicine", 0).getLong("interval", 0);
            //long id = context.getSharedPreferences("Medicine", 0).getInt("id", 0);

            if (currentDate.getTimeInMillis() < endAlarmDateInMilis) {
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, firstAlarmTimeInMilis, interval, alarmIntent); // nie wiem czemu tu timesys2 - trzeba uzyc repeatTime
                //todo w NotificationPublisher trzeba dodac kod zeby przypomnienia sia wylaczaly po uplywie danego czasu
            } else {
                alarmMgr.cancel(alarmIntent);
                //alarmIntent.cancel();
            }
        }

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//        Notification notification = builder.setContentTitle("Take your medicines!")
//                .setAutoCancel(true)
//                .setContentIntent(alarmIntent).build();
//
//        NotificationManager notificationManager = (NotificationManager)
//                context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify();

        ////NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        ////Notification notification = intent.getParcelableExtra(NOTIFICATION);
        ////int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        ////notificationManager.notify(id, notification);

        ////Trigger the notification
        //alarmMgr.notify();
    }

}



