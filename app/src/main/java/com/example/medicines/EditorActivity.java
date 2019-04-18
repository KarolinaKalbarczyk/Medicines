
/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.medicines;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicines.databinding.ActivityEditorBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditorActivity extends BaseActivity {


    public static final String MEDICINE_DATA = "data_medicine";
    private static final int EXISTING_MEDICINE_LOADER = 0;
    private MedicineViewModel medicineViewModel;
    private Uri mCurrentMedicineUri;
    private boolean mMedicineHasChanged = false;
    private ActivityEditorBinding binding;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mMedicineHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_editor);
        Intent intent = getIntent();
        Medicine med = (Medicine) intent.getSerializableExtra(MEDICINE_DATA);
        //TU SPRAWDZAMY CZY NOWY LEK CZY EDYCJA
        if (med == null) {
            setTitle(getString(R.string.editor_activity_title_new_medicine));
            medicineViewModel = new MedicineViewModel(getMedicineApp().getMedicineService());
            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.editor_activity_title_edit_medicine));
            medicineViewModel = new MedicineViewModel(getMedicineApp().getMedicineService(), med);
        }
        binding.setMedicineViewModel(medicineViewModel);
        //binding.saveButton.setOnClickListener(view -> saveMedicine());
        //
        setupSpinners();
    }

    private void setupSpinners() {
        Spinner dropdown = binding.spinner;
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dropdown.setAdapter(staticAdapter);
        //todo dokonczyc setup spinnerow
        Spinner dropdown2 = binding.spinner2;
        ArrayAdapter<CharSequence> staticAdapter2 = ArrayAdapter
                .createFromResource(this, R.array.times, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        staticAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dropdown2.setAdapter(staticAdapter);
        Button reminderButton = (Button) binding.reminder;
        //todo do przeniesienia do EditorActivity
        reminderButton.setOnClickListener((v) -> {
            setReminder();
        });
    }

    public void setReminder() {
        String repeatTime;
        String stayTime;
        String drop_item2;
        String drop_item;
        long timesys;
        String formattedDate;
        long timesys2;
        String formattedDate2;
        long timestay;
        //EditText time =(EditText) binding.time;
        //repeatTime= time.getText().toString();
        repeatTime = binding.time.getText().toString();
//        Spinner dropdown =(Spinner) binding.spinner;
//        drop_item = dropdown.getSelectedItem().toString();
        drop_item = binding.spinner.getSelectedItem().toString();
//        EditText time2 =(EditText) binding.stay;
//        stayTime= time2.getText().toString();
        stayTime = binding.stay.getText().toString();
//        Spinner dropdown2 = (Spinner) binding.spinner2;
//        drop_item2= dropdown2.getSelectedItem().toString();
        drop_item2 = binding.spinner2.getSelectedItem().toString();

        Calendar cal = Calendar.getInstance();
        //cal.setLenient(true);

        //SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy, hh:mm aa");
        timesys = cal.getTimeInMillis();
        //cal.setTimeInMillis(timesys);
        //formattedDate = dateFormatter.format(cal.getTime());


        if (repeatTime.length() != 0) {

            if (drop_item.equals("Hours"))
                //timesys=dt.getTime()+(Long.parseLong(stayTime)*60*60*1000);
                cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(repeatTime));
            else if (drop_item.equals("Days"))
                //timesys=dt.getTime()+(Long.parseLong(stayTime)*60*60*24*1000);
                cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(repeatTime));
            else if (drop_item.equals("Weeks"))
                //timesys=dt.getTime()+(Long.parseLong(stayTime)*60*60*1000*24*7);
                cal.add(Calendar.WEEK_OF_MONTH, Integer.parseInt(repeatTime));
            else if (drop_item.equals("Months"))
                //timesys=dt.getTime()+(Long.parseLong(stayTime)*60*60*24*1000*30);
                cal.add(Calendar.MONTH, Integer.parseInt(repeatTime));
            else
                //timesys=dt.getTime()+(Long.parseLong(stayTime)*60*60*24*1000*365);
                cal.add(Calendar.YEAR, Integer.parseInt(repeatTime));
        }


        Calendar cal2 = Calendar.getInstance();
        //SimpleDateFormat dateFormatter2 = new SimpleDateFormat("MMM dd, yyyy, hh:mm aa");
        timesys2 = cal2.getTimeInMillis();
        //cal2.setTimeInMillis(timesys2);
        //formattedDate2 = dateFormatter2.format(cal2.getTime());

        if (drop_item2.equals("Hours")) {
            timestay = (Long.parseLong(stayTime) * 60 * 60 * 1000);
            cal2.add(Calendar.HOUR_OF_DAY, Integer.parseInt(stayTime));
        } else if (drop_item2.equals("Days")) {
            timestay = (Long.parseLong(stayTime) * 60 * 60 * 24 * 1000);
            cal2.add(Calendar.DAY_OF_MONTH, Integer.parseInt(stayTime));
        } else if (drop_item2.equals("Weeks")) {
            timestay = (Long.parseLong(stayTime) * 60 * 60 * 1000 * 24 * 7);
            cal2.add(Calendar.WEEK_OF_MONTH, Integer.parseInt(stayTime));
        } else if (drop_item2.equals("Months")) {
            timestay = (Long.parseLong(stayTime) * 60 * 60 * 24 * 1000 * 30);
            cal2.add(Calendar.MONTH, Integer.parseInt(stayTime));
        } else {
            timestay = (Long.parseLong(stayTime) * 60 * 60 * 24 * 1000 * 365);
            cal2.add(Calendar.YEAR, Integer.parseInt(stayTime));
        }


        //todo Notyfikacja za pomoca AlarmManager i BroadcastReceiver!
        class SampleBootReceiver extends BroadcastReceiver {
            private AlarmManager alarmMgr;
            private PendingIntent alarmIntent;

            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                    alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    intent = new Intent(context, AlarmReceiver.class);
                    alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

                    if (timestay < timesys2) {
                        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, timesys, timesys2, alarmIntent); // nie wiem czemu tu timesys2
//                        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                                SystemClock.elapsedRealtime() + timesys, alarmIntent);
                        // Set the alarm here.
                    }
                }

                ComponentName receiver = new ComponentName(context, SampleBootReceiver.class);
                PackageManager pm = context.getPackageManager();
                pm.setComponentEnabledSetting(receiver,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);

                // If the alarm has been set, cancel it.
                if (alarmMgr!= null) {
                    alarmMgr.cancel(alarmIntent);
                }
            }
        }
    }

    private void saveMedicine() {
        if (medicineViewModel.saveData()) {  //TODO saveData() moze zamiast boolean zwracac np enum z konkretnym bledem
            setResult(666); //ustawienie przykladowego kodu wyniku, który będzie porównywany
            finish();
        } else
            Toast.makeText(this, "Error ocurred", Toast.LENGTH_LONG).show();    // TODO jesli mamy enum z konkretnym bledem, mozemy wyswietlac rozny tekst w Toast
    }

    private void deleteBook() {
        if (medicineViewModel.deleteData()) {
            setResult(777);
            finish();
        } else
            Toast.makeText(this, "Error ocurred", Toast.LENGTH_LONG).show();
    }

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (medicineViewModel.id != 0)
            getMenuInflater().inflate(R.menu.menu_editor, menu);
        else
            getMenuInflater().inflate(R.menu.menu_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveMedicine();
                return true;
            case R.id.action_delete:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
