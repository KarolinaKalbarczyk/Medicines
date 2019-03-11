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

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.core.app.NavUtils;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.example.medicines.databinding.ActivityEditorBinding;


public class EditorActivity extends AppCompatActivity {

    private MedicineViewModel medicineViewModel;

    private static final int EXISTING_MEDICINE_LOADER = 0;

    private Uri mCurrentMedicineUri;

    private boolean mMedicineHasChanged = false;

    private AppDatabase           medicine;
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

        medicine = AppDatabase.getDatabase(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_editor);

        medicineViewModel = new MedicineViewModel();
        medicineViewModel.setName("Vit. C");
        medicineViewModel.setTimes(1);
        medicineViewModel.setOneDose(1);
        medicineViewModel.setQuantity(20);
        binding.setMedicineViewModel(medicineViewModel);

        Intent intent = getIntent();
        mCurrentMedicineUri = intent.getData();

        if (mCurrentMedicineUri == null) {
            setTitle(getString(R.string.editor_activity_title_new_medicine));

            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.editor_activity_title_edit_medicine));

            getLoaderManager().initLoader(EXISTING_MEDICINE_LOADER, null, (LoaderManager.LoaderCallbacks<Object>) this);
        }

        binding.saveButton.setOnClickListener(view -> saveMedicine());

    }

    private void saveMedicine() {
        String nameString = binding.name.getText().toString().trim();
        String quantityString = binding.quantity.getText().toString().trim();
        String oneDoseString = binding.oneDose.getText().toString().trim();
        String timesString = binding.times.getText().toString().trim();

        if (mCurrentMedicineUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(quantityString)
                && TextUtils.isEmpty(timesString) && TextUtils.isEmpty(oneDoseString)) {
            return;
        }


        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, getString(R.string.name_error),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(quantityString)) {
            Toast.makeText(this, getString(R.string.quantity_error),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "You must input a valid quantity", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(oneDoseString)) {
            Toast.makeText(this, getString(R.string.one_dose_error),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int oneDose;
        try {
            oneDose = Integer.parseInt(oneDoseString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "You must input a valid dose", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(timesString)) {
            Toast.makeText(this, getString(R.string.times_error),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int times;
        try {
            times = Integer.parseInt(timesString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "You must input a valid number", Toast.LENGTH_SHORT).show();
            return;
        }

        medicine.medicineDAO().insertAll(new Medicine(nameString, times, quantity, oneDose, new byte[0]));
        // dla sprawdzenia List<Medicine> all = medicine.medicineDAO().getAll();
    }

    // menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (mCurrentMedicineUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveMedicine();
                finish();
                return true;
            case R.id.action_delete:
                //showDeleteConfirmationDialog();
                //deleteMedicine();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void showDeleteConfirmationDialog() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage(R.string.delete_dialog_msg);
//        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//
//                deleteMedicine();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
//
//
//    private void deleteMedicine() {
//
//        if (mCurrentMedicineUri != null) {
//
//            int rowsDeleted = getContentResolver().delete(mCurrentMedicineUri, null, null);
//
//            if (rowsDeleted == 0) {
//                Toast.makeText(this, getString(R.string.editor_delete_medicine_failed),
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, getString(R.string.editor_delete_medicine_successful),
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        finish();
//    }

    @BindingAdapter("myTimes")
    public static void setImage(View view, MedicineViewModel medicine) {
        if (medicine.getTimes() == 0)
            view.setVisibility(View.GONE);
    }
}