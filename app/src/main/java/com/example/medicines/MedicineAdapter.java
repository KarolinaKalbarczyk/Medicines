package com.example.medicines;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder> {

    private List<Medicine> medicines;
    private RecyclerViewClickListener mListener; // dodaje listenera

    MedicineAdapter(List<Medicine> medicines, RecyclerViewClickListener listener) {
        mListener = listener;
        this.medicines = medicines;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_medicine, viewGroup, false);
        return new MedicineViewHolder(v, mListener);
        }


    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder medicineViewHolder, int index) {

        Medicine current = medicines.get(index);
        medicineViewHolder.medicineNameView.setText(current.getName());
        medicineViewHolder.medicineQuantityView.setText(String.valueOf(current.getQuantity()));
        medicineViewHolder.medicineOneDoseView.setText(String.valueOf(current.getOneDose()));
        medicineViewHolder.medicineTimesView.setText(String.valueOf(current.getTimes()));
    }


    @Override
    public int getItemCount() {
        return medicines.size();
    }

    public void updateList(List<Medicine> newMedicines){
        medicines = newMedicines;
        notifyDataSetChanged();
    }

    public Medicine getMedicineByPosition(int position){
        return medicines.get(position);
    }

    class MedicineViewHolder extends ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;

        TextView medicineNameView;
        TextView medicineQuantityView;
        TextView medicineTimesView;
        TextView medicineOneDoseView;

        MedicineViewHolder(@NonNull View v, RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);

            medicineNameView = itemView.findViewById(R.id.medicineName);
            medicineQuantityView = itemView.findViewById(R.id.medicineQuantity);
            medicineTimesView = itemView.findViewById(R.id.medicineTimes);
            medicineOneDoseView = itemView.findViewById(R.id.medicineOneDose);

        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }
}
