package com.example.medicines;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private List<Medicine> medicines;

    public MedicineAdapter(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MedicineViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_medicine, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder medicineViewHolder, int index) {
        Medicine current = medicines.get(index);
        medicineViewHolder.medicineNameView.setText(current.getName());
        //medicineViewHolder.medicineTimesView.setText(current.getTimes());
        medicineViewHolder.medicineQuantityView.setText(Integer.toString(current.getQuantity()));
        medicineViewHolder.medicineOneDoseView.setText(Integer.toString(current.getOneDose()));
        medicineViewHolder.medicineTimesView.setText(Integer.toString(current.getTimes()));
    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }


    class MedicineViewHolder extends RecyclerView.ViewHolder {

        TextView medicineNameView;
        TextView medicineQuantityView;
        TextView medicineTimesView;
        TextView medicineOneDoseView;


        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);

            medicineNameView = itemView.findViewById(R.id.medicineName);
            medicineQuantityView = itemView.findViewById(R.id.medicineQuantity);
            medicineTimesView = itemView.findViewById(R.id.medicineTimes);
            medicineOneDoseView = itemView.findViewById(R.id.medicineOneDose);
        }
    }
}
