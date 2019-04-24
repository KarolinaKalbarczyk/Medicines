package com.example.medicines;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

// po co nam Serializable?
@Entity
public class Medicine implements Serializable {
    @PrimaryKey(autoGenerate = true)    //TODO jesli chcesz zeby id w bazie generowalo sie samo, trzeba dodac autoGenerate = true
    public int uid;

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

    //tworzymy dwa aby jeden był dla nowego rekordu a drugi dla istniejacego, do edycji, posiadajacy uid <-- to ten nizej!
    public Medicine(@NonNull String name, int times, int quantity, int oneDose, byte[] image) {
        this.mName = name;
        this.mTimes = times;
        this.mQuantity = quantity;
        this.mOneDose = oneDose;
        this.mImage = image;
    }

    @Ignore
    public Medicine(int id, @NonNull String name, int times, int quantity, int oneDose, byte[] image) {
        this.uid = id;
        this.mName = name;
        this.mTimes = times;
        this.mQuantity = quantity;
        this.mOneDose = oneDose;
        this.mImage = image;
    }

    @Ignore
    public Medicine(int id, @NonNull String name, int times, int quantity, int oneDose, byte[] image) {
        this.uid = id;
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

