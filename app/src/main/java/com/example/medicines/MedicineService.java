package com.example.medicines;

import java.util.List;

public class MedicineService {

    private AppDatabase           medicineDb;

    public MedicineService(AppDatabase db){
        medicineDb = db;
    }

    public void saveMedicine(Medicine medicine){
        medicineDb.medicineDAO().insertAll(medicine);
    }

    public List<Medicine> getAllMedicine(){
        return medicineDb.medicineDAO().getAll();
    }
}
