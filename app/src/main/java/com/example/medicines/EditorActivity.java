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
import android.widget.Toast;

import com.example.medicines.databinding.ActivityEditorBinding;



public class EditorActivity extends AppCompatActivity {

    private MedicineView medicineView;

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
        //setContentView(R.layout.activity_editor);

        //AppDatabase medicine = databaseBuilder(getApplicationContext(), AppDatabase.class, "Medicine").build();
        medicine = AppDatabase.getDatabase(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_editor);

        medicineView = new MedicineView();
        medicineView.setName("Vit. C");
        medicineView.setTimes(1);
        medicineView.setOne_dose(1);
        medicineView.setQuantity(20);
        binding.setMedicineView(medicineView);


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

        //ContentValues values = new ContentValues();

        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, getString(R.string.name_error),
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //values.put(getName(), nameString);

        if (TextUtils.isEmpty(quantityString)) {
            Toast.makeText(this, getString(R.string.quantity_error),
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityString);
            //values.put(MedicineContract.MedicineEntry.COLUMN_QUANTITY, quantityString);
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
            //values.put(MedicineContract.MedicineEntry.COLUMN_ONE_DOSE, oneDoseString);
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
            //values.put(MedicineContract.MedicineEntry.COLUMN_TIMES, timesString);
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
                return true;
            case android.R.id.home:
                if (!mMedicineHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

//                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @BindingAdapter("myTimes")
    public static void setImage(View view, MedicineView medicine) {
        if (medicine.getTimes() == 0)
            view.setVisibility(View.GONE);
    }
}