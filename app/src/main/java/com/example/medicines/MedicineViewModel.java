package com.example.medicines;

import android.databinding.ObservableInt;
import android.support.design.widget.FloatingActionButton;

public class MedicineViewModel {

    public String name;

    public ObservableInt   times = new ObservableInt();

    public int quantity;

    public int oneDose;

    public FloatingActionButton fab;

    public FloatingActionButton getFab() {
        return fab;
    }

    public void setFab(FloatingActionButton fab) {
        this.fab = fab;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times.get();
    }

    public void setTimes(int times) {
        this.times.set(times);
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

}
