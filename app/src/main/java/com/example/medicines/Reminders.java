//package com.example.medicines;
//
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.databinding.DataBindingUtil;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.app.TaskStackBuilder;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.Switch;
//import android.widget.TextView;
//import android.support.v7.widget.SwitchCompat;
//import android.widget.Toast;
//
//import com.example.medicines.databinding.ActivityEditorBinding;
//
//import java.text.DateFormatSymbols;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//import java.util.TimeZone;
//
//
//import static java.sql.Types.NULL;
//
//
//public class Reminders extends AppCompatActivity {
//    String message;
//    String repeatTime;
//    String stayTime;
//    String drop_item2;
//    String drop_item;
//    String contentText;
//    String contentText2;
//    long timesys;
//    String formattedDate;
//    long timesys2;
//    String formattedDate2;
//    long timestay;
//    int id;
//    NotificationManager mNotificationManager;
//
//    private ActivityEditorBinding binding;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_editor);
//
//        super.onCreate(savedInstanceState);
//
//        Bundle extras = getIntent().getExtras();
//
//        EditText time =(EditText) binding.time;
//        repeatTime= time.getText().toString();
//
//
//        Spinner dropdown = (Spinner) binding.spinner;
//        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
//                .createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
//
//        // Specify the layout to use when the list of choices appears
//        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Apply the adapter to the spinner
//        dropdown.setAdapter(staticAdapter);
//
//        drop_item = dropdown.getSelectedItem().toString();
//
//        EditText time2 =(EditText) binding.stay;;
//        stayTime= time2.getText().toString();
//
//
//
//        Spinner dropdown2 = (Spinner) binding.spinner2;
//        ArrayAdapter<CharSequence> staticAdapter2 = ArrayAdapter
//                .createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
//
//        // Specify the layout to use when the list of choices appears
//        staticAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // Apply the adapter to the spinner
//        dropdown2.setAdapter(staticAdapter2);
//
//        drop_item2= dropdown2.getSelectedItem().toString();
//
//        Button reminderButton = (Button) binding.reminder;
//
//        //todo do przeniesienia do EditorActivity
//        reminderButton.setOnClickListener((v) ->{
//            setReminder();
//        });
//    }
//
//    public void setReminder(){
//
//        EditText time =(EditText) binding.time;
//        repeatTime= time.getText().toString();
//
//        Spinner dropdown =(Spinner) binding.spinner;
//        drop_item = dropdown.getSelectedItem().toString();
//
//        EditText time2 =(EditText) binding.stay;
//        stayTime= time2.getText().toString();
//
//        Spinner dropdown2 = (Spinner) binding.spinner2;
//        drop_item2= dropdown2.getSelectedItem().toString();
//
//
//        Calendar cal= Calendar.getInstance();
//
//
//        if(drop_item2.equals("Hours"))
//        {timestay=(Long.parseLong(stayTime)*60*60*1000);
//            cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(stayTime));}
//        else if(drop_item2.equals("Days"))
//        {timestay=(Long.parseLong(stayTime)*60*60*24*1000);
//            cal.add(Calendar.DAY_OF_MONTH,Integer.parseInt(stayTime));}
//        else if(drop_item2.equals("Weeks"))
//        {timestay=(Long.parseLong(stayTime)*60*60*1000*24*7);
//            cal.add(Calendar.WEEK_OF_MONTH,Integer.parseInt(stayTime));}
//        else if(drop_item2.equals("Months"))
//        {timestay=(Long.parseLong(stayTime)*60*60*24*1000*30);
//            cal.add(Calendar.MONTH,Integer.parseInt(stayTime));}
//        else
//        {timestay=(Long.parseLong(stayTime)*60*60*24*1000*365);
//            cal.add(Calendar.YEAR,Integer.parseInt(stayTime));}
//
//
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy, hh:mm aa");
//
//        timesys=cal.getTimeInMillis();
//        //cal.setTimeInMillis(timesys);
//        //formattedDate=dateFormatter.format(cal.getTime());
//
//        if(repeatTime.length()!=0) {
//            Calendar cal2 = Calendar.getInstance();
//            cal2.setLenient(true);
//
//
//            if (drop_item.equals("Hours"))
//                //timesys=dt.getTime()+(Long.parseLong(stayTime)*60*60*1000);
//                cal2.add(Calendar.HOUR_OF_DAY, Integer.parseInt(repeatTime));
//            else if (drop_item.equals("Days"))
//                //timesys=dt.getTime()+(Long.parseLong(stayTime)*60*60*24*1000);
//                cal2.add(Calendar.DAY_OF_MONTH, Integer.parseInt(repeatTime));
//            else if (drop_item.equals("Weeks"))
//                //timesys=dt.getTime()+(Long.parseLong(stayTime)*60*60*1000*24*7);
//                cal2.add(Calendar.WEEK_OF_MONTH, Integer.parseInt(repeatTime));
//            else if (drop_item.equals("Months"))
//                //timesys=dt.getTime()+(Long.parseLong(stayTime)*60*60*24*1000*30);
//                cal2.add(Calendar.MONTH, Integer.parseInt(repeatTime));
//            else
//                //timesys=dt.getTime()+(Long.parseLong(stayTime)*60*60*24*1000*365);
//                cal2.add(Calendar.YEAR, Integer.parseInt(repeatTime));
//
//
//            SimpleDateFormat dateFormatter2 = new SimpleDateFormat("MMM dd, yyyy, hh:mm aa");
//
//            timesys2 = cal2.getTimeInMillis();
//            cal2.setTimeInMillis(timesys2);
//            formattedDate2 = dateFormatter2.format(cal2.getTime());
//        }
//
//        //todo do obsluzenia za pomoca AlarmManager i BroadcastReceiver
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(getApplicationContext())
//                        .setSmallIcon(R.drawable.ic_menu_send)
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText("It's time for medicine!"))
//                        .setContentTitle("It's time for medicine!")
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText(contentText+" "+contentText2))
//                        .setContentText(contentText+" "+contentText2);
//
//// Creates an explicit intent for an Activity in your app
//        Intent resultIntent = new Intent(this, MainActivity.class);
//// The stack builder object will contain an artificial back stack for the
//// started Activity.
//// This ensures that navigating backward from the Activity leads out of
//// your application to the Home screen.
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//// Adds the back stack for the Intent (but not the Intent itself)
//        stackBuilder.addParentStack(MainActivity.class);
//// Adds the Intent that starts the Activity to the top of the stack
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(
//                        0,PendingIntent.FLAG_UPDATE_CURRENT
//                );
//        mBuilder.setContentIntent(resultPendingIntent);
//        mNotificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//// mId allows you to update the notification later on.
//        id=(int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
//        mNotificationManager.notify(id, mBuilder.build());
//        removeNotification(id);
//        // NotificationView notifi = new NotificationView(message,repeatTime,drop_item,stayTime,drop_item2);
//        Intent intent = new Intent(Reminders.this, MainActivity.class);
//        startActivity(intent);
//        Toast.makeText(Reminders.this, "Reminder Set", Toast.LENGTH_SHORT).show();
//
//
//    }
//
//    private void removeNotification(final int id) {
//        Handler handler = new Handler();
//        long delayInMilliseconds = timestay;
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                mNotificationManager.cancel(id);
//            }
//        }, delayInMilliseconds);
//    }
//
//}
//
//
//
