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


public class EditorActivity extends BaseActivity {

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

        binding.saveButton.setOnClickListener(view -> saveMedicine());
    }

    private void saveMedicine() {

        if(medicineViewModel.saveData()) {  //TODO saveData() moze zamiast boolean zwracac np enum z konkretnym bledem
            setResult(666); //ustawienie przykladowego kodu wyniku, który będzie porównywany
            finish();
        } else
            Toast.makeText(this, "Error ocurred", Toast.LENGTH_LONG).show();    // TODO jesli mamy enum z konkretnym bledem, mozemy wyswietlac rozny tekst w Toast
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