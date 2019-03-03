package com.example.medicines;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public class MedicineDAO {

    @Dao
    public interface MedicineDbDao {

        @Query("SELECT * FROM Medicine")
        List<Medicine> getAll();

        @Query("SELECT * FROM Medicine WHERE name")
        List<Medicine> loadAllByName();

        @Insert
        void insertAll(Medicine... medicines);

        @Delete
        void delete(Medicine medicines);
    }


}
