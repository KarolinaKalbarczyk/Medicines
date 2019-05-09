
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
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medicines.databinding.ActivityEditorBinding;

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

    private AlarmReceiver receiver = new AlarmReceiver();

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

    //musimu zarejestrowac i odrejestrowac receiver!
    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(receiver, new IntentFilter("com.example.medicine.new_alarm"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (receiver != null)
            unregisterReceiver(receiver);
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
        reminderButton.setOnClickListener((v) -> {
            setReminder();
        });
    }

    public void setReminder() {

        String repeatTime;
        String stayTime;
        String drop_item2;
        String drop_item;
        long firstAlarmTimeInMilis;
        long interval = 0;
        long endAlarmDateInMilis;

        repeatTime = binding.time.getText().toString();
        drop_item = binding.spinner.getSelectedItem().toString();
        stayTime = binding.stay.getText().toString();
        drop_item2 = binding.spinner2.getSelectedItem().toString();

        Calendar firstAlarmDate = Calendar.getInstance();

        if (repeatTime.length() != 0) {

            if (drop_item.equals("Minutes")) {
                interval = (Long.parseLong(repeatTime) * 60 * 1000);
                firstAlarmDate.add(Calendar.MINUTE, Integer.parseInt(repeatTime));
            } else if (drop_item.equals("Hours")) {
                interval = (Long.parseLong(repeatTime) * 60 * 60 * 1000);
                firstAlarmDate.add(Calendar.HOUR_OF_DAY, Integer.parseInt(repeatTime));
            } else if (drop_item.equals("Days")) {
                interval = (Long.parseLong(repeatTime) * 60 * 60 * 24 * 1000);
                firstAlarmDate.add(Calendar.DAY_OF_MONTH, Integer.parseInt(repeatTime));
            } else if (drop_item.equals("Weeks")) {
                interval = (Long.parseLong(repeatTime) * 60 * 60 * 1000 * 24 * 7);
                firstAlarmDate.add(Calendar.WEEK_OF_MONTH, Integer.parseInt(repeatTime));
            } else if (drop_item.equals("Months")) {
                interval = (Long.parseLong(repeatTime) * 60 * 60 * 24 * 1000 * 30);
                firstAlarmDate.add(Calendar.MONTH, Integer.parseInt(repeatTime));
            } else {
                interval = (Long.parseLong(repeatTime) * 60 * 60 * 24 * 1000 * 365);
                firstAlarmDate.add(Calendar.YEAR, Integer.parseInt(repeatTime));
            }
        }

        firstAlarmTimeInMilis = firstAlarmDate.getTimeInMillis();


        Calendar endAlarmDate = Calendar.getInstance();

        if (drop_item2.equals("Minutes")) {
            endAlarmDate.add(Calendar.MINUTE, Integer.parseInt(stayTime));
        } else if (drop_item2.equals("Hours")) {
            endAlarmDate.add(Calendar.HOUR_OF_DAY, Integer.parseInt(stayTime));
        } else if (drop_item2.equals("Days")) {
            endAlarmDate.add(Calendar.DAY_OF_MONTH, Integer.parseInt(stayTime));
        } else if (drop_item2.equals("Weeks")) {
            endAlarmDate.add(Calendar.WEEK_OF_MONTH, Integer.parseInt(stayTime));
        } else if (drop_item2.equals("Months")) {
            endAlarmDate.add(Calendar.MONTH, Integer.parseInt(stayTime));
        } else {
            endAlarmDate.add(Calendar.YEAR, Integer.parseInt(stayTime));
        }

        endAlarmDateInMilis = endAlarmDate.getTimeInMillis();

        getSharedPreferences("Medicine", 0)
                .edit()
                .putLong("firstAlarmTimeInMilis", firstAlarmTimeInMilis)
                .putLong("endAlarmDateInMilis", endAlarmDateInMilis)
                .putLong("interval", interval)
                .apply();

        sendBroadcast(new Intent("com.example.medicine.new_alarm"));

    }

    private void saveMedicine() {
        if (medicineViewModel.saveData()) {  //TODO saveData() moze zamiast boolean zwracac np enum z konkretnym bledem
            setResult(666); //ustawienie przykladowego kodu wyniku, który będzie porównywany
            finish();
        } else
            Toast.makeText(this, "Error ocurred", Toast.LENGTH_LONG).show();    // TODO jesli mamy enum z konkretnym bledem, mozemy wyswietlac rozny tekst w Toast
    }

    private void deleteMedicine() {
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
                deleteMedicine(); // czemu nie widac od razu tylko trzeba odswiezyc recznie?
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
