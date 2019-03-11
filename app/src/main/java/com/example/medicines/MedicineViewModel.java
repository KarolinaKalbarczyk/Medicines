package com.example.medicines;

import androidx.databinding.ObservableInt;

//moze raczej MedicineViewModel
public class MedicineViewModel {

    public String name;

    public ObservableInt times = new ObservableInt();

    public int quantity;

    public int one_dose;

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

    public int getOne_dose() {
        return one_dose;
    }
    public void setOne_dose(int one_dose) {
        this.one_dose = one_dose;
    }

}
