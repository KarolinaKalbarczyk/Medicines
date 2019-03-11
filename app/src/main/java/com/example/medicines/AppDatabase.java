package com.example.medicines;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Medicine.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract MedicineDao medicineDAO();

        private static volatile AppDatabase INSTANCE;


        static AppDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (AppDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class, "medicine_database")
                                .allowMainThreadQueries()   //TODO UWAGA!! operacje na bazie danych nie powinny byc wykonywane w glownym watku aplikacji, ale w tym momencie (tymczasowo) możemy na to pozwolic
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
    }

