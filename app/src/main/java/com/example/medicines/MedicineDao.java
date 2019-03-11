package com.example.medicines;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

//ta klasa jest niepotrzebna
//public class MedicineDAO {

    @Dao
    public interface MedicineDao {

        @Query("SELECT * FROM Medicine")
        List<Medicine> getAll();

        @Query("SELECT * FROM Medicine ORDER BY mName")
        List<Medicine> loadAllByName();

        @Insert
        void insertAll(Medicine... medicines);

        @Delete
        void delete(Medicine medicines);
    }


//}
