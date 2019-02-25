package com.example.medicines;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedicineDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "medicine.db";

    private static final int DATABASE_VERSION = 1;

    public MedicineDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_MEDICINE_TABLE = "CREATE TABLE " + MedicineContract.MedicineEntry.TABLE_NAME + " ("
                + MedicineContract.MedicineEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MedicineContract.MedicineEntry.COLUMN_NAME + " TEXT NOT NULL, "
//                + MedicineContract.MedicineEntry.COLUMN_IMAGE + " IMAGE, "
                + MedicineContract.MedicineEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                + MedicineContract.MedicineEntry.COLUMN_TIMES + " INTEGER NOT NULL, "
                + MedicineContract.MedicineEntry.COLUMN_ONE_DOSE + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_MEDICINE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}