package com.example.medicines;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private List<Medicine> medicines;

    public MedicineAdapter(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MedicineViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_row, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder medicineViewHolder, int index) {
        Medicine current = medicines.get(index);
        medicineViewHolder.medicineTextView.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }

    public void updateList(List<Medicine> newMedicines){
        medicines = newMedicines;
        notifyDataSetChanged();
    }


    class MedicineViewHolder extends RecyclerView.ViewHolder {

        TextView medicineTextView;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);

            medicineTextView = itemView.findViewById(R.id.medicineName);
        }
    }
}
