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

import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.medicines.databinding.ActivityEditorBinding;


public class EditorActivity extends AppCompatActivity {

    private MedicineViewModel medicineViewModel;

    private static final int EXISTING_MEDICINE_LOADER = 0;

    private Uri mCurrentMedicineUri;

    private boolean mMedicineHasChanged = false;

    private ActivityEditorBinding binding;

    public static final String MEDICINE_DATA = "data_medicine";

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


        //TU SPRAWDZAMY CZY NOWY LEK CZY EDYCJA?
        if (med == null) {
            setTitle(getString(R.string.editor_activity_title_new_medicine));
//            medicineViewModel = new MedicineViewModel(new MedicineService(AppDatabase.getDatabase(this)));
            medicineViewModel = new MedicineViewModel(getMedicineApp().getMedicineService());
            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.editor_activity_title_edit_medicine));
//            medicineViewModel = new MedicineViewModel(new MedicineService(AppDatabase.getDatabase(this)), med);
            medicineViewModel = new MedicineViewModel(getMedicineApp().getMedicineService(), med);
        }
        binding.setMedicineViewModel(medicineViewModel);

        // Próba zamiany saveButton na dole na action_save w menu
        //Nie działa bo nie umie zapisac do bazy w lini 95, dlatego wyrzuca linię 97 ?

//        Button saveButton = findViewById(R.id.action_save);
//        saveButton.setOnClickListener(view -> saveMedicine());

        binding.saveButton.setOnClickListener(view -> saveMedicine());
    }

    private void saveMedicine() {

        if(medicineViewModel.saveData())    //TODO saveData() moze zamiast boolean zwracac np enum z konkretnym bledem
            finish();
        else
            Toast.makeText(this, "Error ocurred", Toast.LENGTH_LONG).show();    // TODO jesli mamy enum z konkretnym bledem, mozemy wyswietlac rozny tekst w Toast

        //medicine.medicineDAO().insertAll(new Medicine(nameString, times, quantity, oneDose, new byte[0]));
        // dla sprawdzenia List<Medicine> all = medicine.medicineDAO().getAll();
    }


    // menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (medicineViewModel.id != 0)
            getMenuInflater().inflate(R.menu.menu_editor, menu);
        else
            getMenuInflater().inflate(R.menu.menu_new, menu);    //TODO stworzyc menu z samym Save
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveMedicine();
                //finish();
                return true;
            case R.id.action_delete:
                //showDeleteConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @BindingAdapter("myTimes")
//    public static void setImage(View view, MedicineViewModel medicine) {
//        if (medicine.getTimes() == 0)
//            view.setVisibility(View.GONE);
//    }

    private MedicineApp getMedicineApp(){
        return (MedicineApp) getApplication();
    }
}