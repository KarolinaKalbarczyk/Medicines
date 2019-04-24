package com.example.medicines;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {

    private AlarmManager  alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED") || intent.getAction().equals("com.example.medicine.new_alarm")) {
            alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            //intent = new Intent(context, AlarmReceiver.class);
            //alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

            //todo zastanow sie jaka akcje chcesz odpalac po wlaczeniu sie alarmu - moze jakas nowa aktywnosc?
            alarmIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

            Calendar currentDate = Calendar.getInstance();
            long firstAlarmTimeInMilis = context.getSharedPreferences("Medicine", 0).getLong("firstAlarmTimeInMilis", 0);
            long endAlarmDateInMilis = context.getSharedPreferences("Medicine", 0).getLong("endAlarmDateInMilis", 0);
            long interval = context.getSharedPreferences("Medicine", 0).getLong("interval", 0);

            if (currentDate.getTimeInMillis() < endAlarmDateInMilis) {
                alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, firstAlarmTimeInMilis, interval, alarmIntent); // nie wiem czemu tu timesys2 - trzeba uzyc repeatTime
//                        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                                SystemClock.elapsedRealtime() + timesys, alarmIntent);
                // Set the alarm here.
            }
        }

           /* ComponentName receiver = new ComponentName(context, SampleBootReceiver.class);
            PackageManager pm = context.getPackageManager();
            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);*/

        // If the alarm has been set, cancel it.
            /*if (alarmMgr!= null) {
                alarmMgr.cancel(alarmIntent);
            }*/
    }

    /*@Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        Toast.makeText(context, "OnReceive alarm test", Toast.LENGTH_SHORT).show();
    }*/
}


