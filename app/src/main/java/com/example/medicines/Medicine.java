package com.example.medicines;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;




@Entity
public class Medicine {
    @PrimaryKey
    public int uid;

    public String Name;

    public Integer times;
    public Integer quantity;
    public Integer one_dose;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;



}


