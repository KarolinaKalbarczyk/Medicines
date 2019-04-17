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

//    public void updateMedicine(Medicine medicine){
//        medicineDb.medicineDAO().updateMedicine(medicine);
//    }

    public List<Medicine> getAllMedicine(){
        return medicineDb.medicineDAO().getAll();
    }

    //ma byc tak samo jak w DAO, update musi miec jakis argument!
    public void updateMedicine(Medicine medicine) {
        medicineDb.medicineDAO().update(medicine);
    }
}

