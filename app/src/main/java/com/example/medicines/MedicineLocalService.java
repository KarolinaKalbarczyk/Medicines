package com.example.medicines;

import java.util.List;

public class MedicineLocalService implements MedicineService {

    private AppDatabase           medicineDb;

    public MedicineLocalService(AppDatabase db){
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

    //ma byc tak smao jak w DAO, update musi miec jakis argument!
    public void updateMedicine(Medicine medicine) {
        medicineDb.medicineDAO().update(medicine);
    }

    //aby móc usunąć rekord
    public void deleteMedicine(Medicine medicine){
        medicineDb.medicineDAO().delete(medicine);
    }
}

