package com.example.medicines;

import android.arch.persistence.room.ColumnInfo;

public class Reminder {
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

}
