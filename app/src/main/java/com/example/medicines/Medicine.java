package com.example.medicines;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;


@Entity
public class Medicine implements Serializable {
    @PrimaryKey(autoGenerate = true)    //TODO jesli chcesz zeby id w bazie denerowalo sie samo, trzeba dodac autoDenerate = true
    public int uid;

    //jeśli z mXxxxx, to raczej nie public; jeśli mXxxx to @ColumnInfo
    @ColumnInfo(name = "name")
    public String mName;

    @ColumnInfo(name = "times")
    public int mTimes;

    @ColumnInfo(name = "quantity")
    public int mQuantity;

    @ColumnInfo(name = "oneDose")
    public int mOneDose;

    @ColumnInfo(name = "image")
    public byte[] mImage;

    public Medicine(@NonNull String name, int times, int quantity, int oneDose, byte[] image) {
        this.mName = name;
        this.mTimes = times;
        this.mQuantity = quantity;
        this.mOneDose = oneDose;
        this.mImage = image;
    }

    public int getUid() {
        return uid;
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

    public int getOneDose() {
        return this.mOneDose;
    }

    public byte[] getImage() {
        return this.mImage;
    }

}

//    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
//    private byte[] image;

