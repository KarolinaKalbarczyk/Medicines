package com.example.medicines;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.Serializable;

// po co nam Serializable?
@Entity
public class Medicine implements Serializable {
    @PrimaryKey(autoGenerate = true)
    //TODO jesli chcesz zeby id w bazie generowalo sie samo, trzeba dodac autoGenerate = true
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

    @Embedded
    public Reminder reminder;

    public Medicine(@NonNull String name, int times, int quantity, int oneDose, byte[] image, @Nullable Reminder reminder){
        this.mName = name;
        this.mTimes = times;
        this.mQuantity = quantity;
        this.mOneDose = oneDose;
        this.mImage = image;
        this.reminder = reminder;
    }

    //tworzymy dwa aby jeden był dla nowego rekordu a drugi dla istniejacego, do edycji, posiadajacy uid <-- to ten nizej!
    @Ignore
    public Medicine(@NonNull String name, int times, int quantity, int oneDose, byte[] image, int repeatTime,
                    int stayTime, int firstAlarmTimeInMilis, int endAlarmTimeInMilis, int interval) {
        this.mName = name;
        this.mTimes = times;
        this.mQuantity = quantity;
        this.mOneDose = oneDose;
        this.mImage = image;
        this.reminder = new Reminder();
        this.reminder.mRepeatTime = repeatTime;
        this.reminder.mStayTime = stayTime;
        this.reminder.mFirstAlarmTimeInMilis = firstAlarmTimeInMilis;
        this.reminder.mEndAlarmTimeInMilis = endAlarmTimeInMilis;
        this.reminder.mInterval = interval;
    }

    @Ignore
    public Medicine(int id, @NonNull String name, int times, int quantity, int oneDose, byte[] image,
                    int repeatTime, int stayTime, int firstAlarmTimeInMilis, int endAlarmTimeInMilis, int interval) {
        this.uid = id;
        this.mName = name;
        this.mTimes = times;
        this.mQuantity = quantity;
        this.mOneDose = oneDose;
        this.mImage = image;
        this.reminder = new Reminder();
        this.reminder.mRepeatTime = repeatTime;
        this.reminder.mStayTime = stayTime;
        this.reminder.mFirstAlarmTimeInMilis = firstAlarmTimeInMilis;
        this.reminder.mEndAlarmTimeInMilis = endAlarmTimeInMilis;
        this.reminder.mInterval = interval;
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

    public int getRepeatTime() {
        return this.reminder.mRepeatTime;
    }

    public int getStayTime() {
        return this.reminder.mStayTime;
    }

    public int getFirstAlarmTimeInMilis() {
        return this.reminder.mFirstAlarmTimeInMilis;
    }

    public int getEndAlarmTimeInMilis() {
        return this.reminder.mEndAlarmTimeInMilis;
    }

    public int getInterval() {
        return this.reminder.mInterval;
    }

}

