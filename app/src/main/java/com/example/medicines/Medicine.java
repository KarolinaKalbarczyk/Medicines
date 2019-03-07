package com.example.medicines;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;




@Entity
public class Medicine {
    @PrimaryKey(autoGenerate = true)    //TODO jesli chcesz zeby id w bazie denerowalo sie samo, trzeba dodac autoDenerate = true
    public int uid;

    //jeśli z mXxxxx, to raczej nie public; jeśli mXxxx to @ColumnInfo
    public String mName;
    public int mTimes;
    public int mQuantity;
    public int mOne_dose;
    public byte[] mImage;

    public Medicine(@NonNull String name, int times, int quantity, int one_dose, byte[] image) {
        this.mName = name;
        this.mTimes = times;
        this.mQuantity = quantity;
        this.mOne_dose = one_dose;
        this.mImage = image;
    }

    public String getName() {
        return this.mName;
    }

    public int getTimes() {
        return this.mTimes;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    public int getOne_dose() {
        return this.mOne_dose;
    }

    public byte[] getImage() {
        return this.mImage;
    }

}

//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    private byte[] image;

