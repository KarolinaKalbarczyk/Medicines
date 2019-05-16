package com.example.medicines;

import android.text.TextUtils;

public class MedicineViewModel {

    private MedicineService service;

    public int id;
    public String name = "";

    public int times;

    public int quantity;

    public int oneDose;

    public byte[] image = new byte[0];

    public int repeatTime;

    public int stayTime;

    public int firstAlarmTimeInMilis;

    public int endAlarmTimeInMilis;

    public int interval;

    public MedicineViewModel(MedicineService service) {
        this.service = service;
    }

    public MedicineViewModel(MedicineService service, Medicine medicine) {
        this.service = service;

        id = medicine.getUid();
        name = medicine.getName();
        times = medicine.getTimes();
        quantity = medicine.getQuantity();
        oneDose = medicine.getOneDose();
        repeatTime = medicine.getRepeatTime();
        stayTime = medicine.getStayTime();
        firstAlarmTimeInMilis = medicine.getFirstAlarmTimeInMilis();
        endAlarmTimeInMilis = medicine.getEndAlarmTimeInMilis();
        interval = medicine.getInterval();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOneDose() {
        return oneDose;
    }

    public void setOneDose(int oneDose) {
        this.oneDose = oneDose;
    }

    //TODO czemu nie używane poniższe?

    public int getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(int repeatTime) {
        this.repeatTime = repeatTime;
    }

    public int getStayTime() {
        return stayTime;
    }

    public void setStayTime(int stayTime) {
        this.stayTime = stayTime;
    }

    public int getFirstAlarmTimeInMilis() {
        return firstAlarmTimeInMilis;
    }

    public void setFirstAlarmTimeInMilis(int firstAlarmTimeInMilis) {
        this.firstAlarmTimeInMilis = firstAlarmTimeInMilis;
    }

    public int getEndAlarmTimeInMilis() {
        return endAlarmTimeInMilis;
    }

    public void setEndAlarmTimeInMilis(int endAlarmTimeInMilis) {
        this.endAlarmTimeInMilis = endAlarmTimeInMilis;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    //zapisywanie danych o leku

    public boolean saveData() {
        if (TextUtils.isEmpty(name)) {
            return false;
        }

        if (quantity <= 0) {
            return false;
        }

        if (id != 0) {
            service.updateMedicine(new Medicine(id, name, times, quantity, oneDose, image,
                    repeatTime, stayTime, firstAlarmTimeInMilis, endAlarmTimeInMilis, interval));
        } else {
            service.saveMedicine(new Medicine(name, times, quantity, oneDose, image,
                    repeatTime, stayTime, firstAlarmTimeInMilis, endAlarmTimeInMilis, interval));
        }
        return true;

    }

    public boolean deleteData() {
        if (id == 0) {
            return false;
        } else {
            service.deleteMedicine(new Medicine(id, name, times, quantity, oneDose, image,
                    repeatTime, stayTime, firstAlarmTimeInMilis, endAlarmTimeInMilis, interval));
        }
        return true;
    }
}
