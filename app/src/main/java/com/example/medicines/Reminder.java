package com.example.medicines;

import android.arch.persistence.room.ColumnInfo;

public class Reminder {

    @ColumnInfo(name = "repeateTime")
    public int mRepeatTime;

    @ColumnInfo(name = "stayTime")
    public int mStayTime;

    @ColumnInfo(name = "firstAlarmTimeInMilis")
    public int mFirstAlarmTimeInMilis;

    @ColumnInfo(name = "endAlarmTimeInMilis")
    public int mEndAlarmTimeInMilis;

    @ColumnInfo(name = "interval")
    public int mInterval;

}
