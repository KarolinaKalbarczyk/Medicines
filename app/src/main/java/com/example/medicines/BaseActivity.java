package com.example.medicines;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    protected MedicineApp getMedicineApp(){
        return (MedicineApp) getApplication();
    }
}
