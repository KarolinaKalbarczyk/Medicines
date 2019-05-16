package com.example.medicines;

import java.util.List;

public interface MedicineService {

    void saveMedicine(Medicine medicine);

    List<Medicine> getAllMedicine();

    //ma byc tak smao jak w DAO, update musi miec jakis argument!
    void updateMedicine(Medicine medicine);

    //aby móc usunąć rekord
    void deleteMedicine(Medicine medicine);
}
