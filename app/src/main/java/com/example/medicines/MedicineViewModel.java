package com.example.medicines;

import android.text.TextUtils;

public class MedicineViewModel {
    private MedicineService service;

    public int id;
    public String name = "";

    public int   times;

    public int quantity;

    public int oneDose;

    public byte[] image = new byte[0];

    /*public FloatingActionButton fab;

    public FloatingActionButton getFab() {
        return fab;
    }

    public void setFab(FloatingActionButton fab) {
        this.fab = fab;
    }*/

    public MedicineViewModel(MedicineService service){
        this.service = service;
    }

    public MedicineViewModel(MedicineService service, Medicine medicine){
        this.service = service;

        id = medicine.getUid();
        name = medicine.getName();
        times = medicine.getTimes();
        quantity = medicine.getQuantity();
        oneDose = medicine.getOneDose();
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

    public boolean saveData(){
        if (TextUtils.isEmpty(name)) {
            return false;
        }

        if (quantity <= 0) {
            return false;
        }

        service.saveMedicine(new Medicine(name, times, quantity, oneDose, image));
        return true;
    }

}
