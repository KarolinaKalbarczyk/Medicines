package com.example.medicines;

public class MedicineService {

    private AppDatabase           medicineDb;

    public MedicineService(AppDatabase db){
        medicineDb = db;
    }

    public void saveMedicine(Medicine medicine){
        medicineDb.medicineDAO().insertAll(medicine);
    }
}
