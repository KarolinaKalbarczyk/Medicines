package com.example.medicines;

import android.app.Application;

public class MedicineApp extends Application {

    private MedicineService medicineService;

    @Override
    public void onCreate() {
        super.onCreate();

        medicineService = new MedicineService(AppDatabase.getDatabase(this));
    }

    public MedicineService getMedicineService(){
        return medicineService;
    }
}
