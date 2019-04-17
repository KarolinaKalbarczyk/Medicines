package com.example.medicines;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


    @Dao
    public interface MedicineDao {

        @Query("SELECT * FROM Medicine")
        List<Medicine> getAll();

        @Query("SELECT * FROM Medicine ORDER BY name")
        List<Medicine> loadAllByName();

        @Insert
        void insertAll(Medicine... medicines);

        @Delete
        void delete(Medicine medicines);

        @Update
        void update(Medicine medicines);
    }

