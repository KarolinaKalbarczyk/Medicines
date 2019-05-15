package com.example.medicines;

import android.arch.persistence.room.ColumnInfo;
import java.io.Serializable;



public class Reminder implements Serializable {
    @ColumnInfo(name = "repeateTime")
    public Integer mRepeatTime;

    @ColumnInfo(name = "stayTime")
    public Integer mStayTime;

    @ColumnInfo(name = "firstAlarmTimeInMilis")
    public Integer mFirstAlarmTimeInMilis;

    @ColumnInfo(name = "endAlarmTimeInMilis")
    public Integer mEndAlarmTimeInMilis;

    @ColumnInfo(name = "interval")
    public Integer mInterval;

    public Reminder(Integer mRepeatTime, Integer mStayTime, Integer mFirstAlarmTimeInMilis, Integer mEndAlarmTimeInMilis, Integer mInterval) {
        this.mRepeatTime = mRepeatTime;
        this.mStayTime = mStayTime;
        this.mFirstAlarmTimeInMilis = mFirstAlarmTimeInMilis;
        this.mEndAlarmTimeInMilis = mEndAlarmTimeInMilis;
        this.mInterval = mInterval;
    }
}
